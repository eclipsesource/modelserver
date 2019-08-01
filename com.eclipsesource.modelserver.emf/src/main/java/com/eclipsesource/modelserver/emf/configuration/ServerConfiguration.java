/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package com.eclipsesource.modelserver.emf.configuration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.eclipsesource.modelserver.emf.launch.ModelServerLauncher;

/**
 * Singleton class that holds configurations parameters for a model server
 * instance
 *
 */
public class ServerConfiguration {
	private String workspaceRoot;
	private static Logger LOG = Logger.getLogger(ServerConfiguration.class);
	private int serverPort = ModelServerLauncher.DEFAULT_JAVALIN_PORT;

	public String getWorkspaceRoot() {
		return workspaceRoot;
	}

	public void setWorkspaceRoot(String workspaceRoot) {
		toFilePath(workspaceRoot).ifPresent(path -> this.workspaceRoot = path);
	}

	public Set<String> getWorkspaceEntries() {
		Set<String> filePaths = new HashSet<>();
		
		if (!this.workspaceRoot.isEmpty()) {
			try (Stream<Path> paths = Files.walk(Paths.get(this.workspaceRoot))) {
				paths
					.filter(Files::isRegularFile)
					.forEach(file -> filePaths.add(file.toString()));
			} catch (InvalidPathException i) {
				LOG.error(String.format("Could not get workspace path! ’%s’ cannot be converted to a valid Path", this.workspaceRoot));
			} catch (IOException io) {
				LOG.error(String.format("IOException occured while reading files in workspace ’%s’: " + io.getMessage(), this.workspaceRoot));
			} catch (SecurityException s) {
				LOG.error("Security manager denies access to files under workspace " + this.workspaceRoot);
			}
		}
		return filePaths;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public static boolean isValidWorkspaceRoot(String fileUrl) {
		return toFilePath(fileUrl).map(url -> new File(url).exists()).orElse(false);
	}

	public static boolean isValidPort(Integer port) {
		return port >= 0 && port <= 65535;
	}

	private static Optional<String> toFilePath(String fileUrl) {
		try {
			URI uri = URI.create(fileUrl);
			return Optional.of(uri.getPath());
		} catch (NullPointerException | IllegalArgumentException e) {
			LOG.warn(String.format("Could not convert to filePath! ’%s’ is not a valid URL", fileUrl));
			return Optional.empty();
		}
	}

}
