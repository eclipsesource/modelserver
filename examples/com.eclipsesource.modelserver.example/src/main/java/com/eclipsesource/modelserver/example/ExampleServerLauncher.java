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

import com.eclipsesource.modelserver.emf.launch.ModelServerLauncher;
import com.eclipsesource.modelserver.example.util.ResourceUtil;
import com.google.common.collect.Lists;

public class ExampleServerLauncher {
	private static String TEMP_DIR = ".temp";
	private static String WORKSPACE_ROOT = "workspace";
	private static String ECORE_TEST_FILE = "Coffee.ecore";
	private static String COFFEE_TEST_FILE = "SuperBrewer3000.coffee";
	private static String JSON_TEST_FILE = "SuperBrewer3000.json";

	private static Logger LOG = Logger.getLogger(ExampleServerLauncher.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();

		final File workspaceRoot = new File(TEMP_DIR + "/" + WORKSPACE_ROOT);
		if (!setupTempTestWorkspace(workspaceRoot)) {
			LOG.error("Could not setup test workspace");
			System.exit(0);
		}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> cleanupTempTestWorkspace(workspaceRoot)));

		final ModelServerLauncher launcher = new ModelServerLauncher(args);
		launcher.setWorkspaceRoot(workspaceRoot.getAbsolutePath());
		launcher.addEPackageConfigurations(Lists.newArrayList(CoffeePackageConfiguration.class));
		launcher.start();
	}

	private static boolean setupTempTestWorkspace(File workspaceRoot) {
		boolean result = workspaceRoot.mkdirs();
		result &= ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + ECORE_TEST_FILE,
				new File(workspaceRoot, ECORE_TEST_FILE));
		result &= result && ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + COFFEE_TEST_FILE,
				new File(workspaceRoot, COFFEE_TEST_FILE));
		result &= result && ResourceUtil.copyFromResource(WORKSPACE_ROOT + "/" + JSON_TEST_FILE,
				new File(workspaceRoot, JSON_TEST_FILE));
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
