/*******************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License v. 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0.
 *
 *   This Source Code may also be made available under the following Secondary
 *   Licenses when the conditions for such availability set forth in the Eclipse
 *   Public License v. 2.0 are satisfied: GNU General Public License, version 2
 *   with the GNU Classpath Exception which is available at
 *   https://www.gnu.org/software/classpath/license.html.
 *
 *   SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 *******************************************************************************/
package com.eclipsesource.modelserver.emf.launch;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;

public class CLIParser {
	private static CLIParser INSTANCE;
	private CommandLine cmd;
	private Options options;

	private CLIParser(String args[], Options options) throws ParseException {
		this.cmd = new DefaultParser().parse(options, args);
		this.options = options;
	}

	public static void create(String args[], Options options) throws ParseException {
		INSTANCE = new CLIParser(args, options);
	}

	public static CLIParser getInstance() {
		return INSTANCE;
	}

	public static boolean initialized() {
		return INSTANCE != null;
	}

	public boolean optionExists(String identifier) {
		return cmd.hasOption(identifier);
	}

	/**
	 * Parses and validates the port argument
	 *
	 * @return the parsed port argument if present, default port otherwise
	 * @throws ParseException is thrown if the parsed argument is not a valid port
	 */
	public Integer parsePort() throws ParseException {
		String portArg = cmd.getOptionValue("p");
		int port = ModelServerLauncher.DEFAULT_JAVALIN_PORT;
		if (portArg != null) {
			try {
				port = Integer.parseInt(portArg);
				if (ServerConfiguration.isValidPort(port)) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				throw new ParseException(String.format("'%s' is not a valid port! The default port '%s' is used",
						portArg, ModelServerLauncher.DEFAULT_JAVALIN_PORT));
			}
		}

		return port;
	}

	protected Optional<String> parseWorkspaceRoot() throws ParseException {
		String rootArg = cmd.getOptionValue("r");
		if (rootArg != null) {
			if (!ServerConfiguration.isValidWorkspaceRoot(rootArg)) {
				throw new ParseException(String.format("Could not set workspace! The path '%s' is invalid.", rootArg));
			} else {
				return Optional.of(rootArg);
			}
		}
		return Optional.empty();
	}

	public void printHelp(String processName) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("processName", options);
	}

	public static Options getDefaultCLIOptions() {
		Options options = new Options();
		options.addOption("p", "port", true, "Set server port");
		options.addOption("r", "root", true, "Set workspace root");
		options.addOption("e", "errorsOnly", false, "Only log errors");
		return options;
	}
}
