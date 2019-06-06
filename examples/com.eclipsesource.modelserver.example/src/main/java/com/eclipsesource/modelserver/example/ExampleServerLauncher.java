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
package com.eclipsesource.modelserver.example;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.eclipsesource.modelserver.emf.ModelServer;
import com.eclipsesource.modelserver.emf.launch.ModelServerLauncher;
import com.eclipsesource.modelserver.example.util.ResourceUtil;
import com.google.common.collect.Lists;

public class ExampleServerLauncher {
	private static String TEMP_DIR = ".temp";
	private static String WORKSPACE_ROOT = "workspace";
	private static String ECORE_TEST_FILE = "Coffe.ecore";
	private static String COFFEE_TEST_FILE = "SuperBrewer3000";
	private static String COFFEE_XMI_TEST_FILE = COFFEE_TEST_FILE + ".xmi";
	private static String COFFEE_JSON_TEST_FILE = COFFEE_TEST_FILE + ".json";

	private static Logger LOG = Logger.getLogger(ExampleServerLauncher.class);

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();

		final ModelServerLauncher launcher = new ModelServerLauncher(args);
		final File workspaceRoot = new File(TEMP_DIR + "/" + WORKSPACE_ROOT);
		if (!setupTempTestWorkspace(workspaceRoot)) {
			LOG.error("Could not setup test workspace");
			System.exit(0);
		}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> cleanupTempTestWorkspace(workspaceRoot)));
		launcher.setWorkspaceRoot(workspaceRoot.getAbsolutePath());
		launcher.addModules(Lists.newArrayList(new ExampleModelServerModule()));
		launcher.start();

		final ModelServer server = launcher.getInjector().getInstance(ModelServer.class);
		server.initialize();
		server.loadModel(ECORE_TEST_FILE);

	}

	private static boolean setupTempTestWorkspace(File workspaceRoot) throws IOException {
		boolean result = workspaceRoot.mkdirs();
		result = ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + ECORE_TEST_FILE,
				new File(workspaceRoot, ECORE_TEST_FILE));
		result = result && ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + COFFEE_XMI_TEST_FILE,
				new File(workspaceRoot, COFFEE_XMI_TEST_FILE));
		result = result && ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + COFFEE_JSON_TEST_FILE,
				new File(workspaceRoot, COFFEE_JSON_TEST_FILE));
		return result;
	}

	private static void cleanupTempTestWorkspace(File workspaceRoot) {
		if (workspaceRoot.exists()) {
			try {
				FileUtils.deleteDirectory(workspaceRoot);
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

}
