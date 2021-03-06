/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
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

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeThat;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

public class ServerConfigurationTest {

   private final ServerConfiguration serverConfiguration = new ServerConfiguration();

   @Test
   public void normalizeWorkspaceRoot() {
      serverConfiguration.setWorkspaceRoot("foo");
      assertThat(serverConfiguration.getWorkspaceRoot(), endsWith("foo/"));
   }

   @Test
   public void normalizeWorkspaceRootSlashAlreadyPresent() {
      serverConfiguration.setWorkspaceRoot("foo/");
      assertThat(serverConfiguration.getWorkspaceRoot(), endsWith("foo/"));
   }

   @Test
   public void setWorkspaceRootURI() {
      File cwd = getCWD();

      serverConfiguration.setWorkspaceRootURI(URI.createFileURI(cwd.getAbsolutePath()));
      assertThat(serverConfiguration.getWorkspaceRoot(), is(cwd.getAbsolutePath()));
   }

   @Test
   public void setWorkspaceRoot() {
      File cwd = getCWD();

      serverConfiguration.setWorkspaceRoot(".");
      URI expected = URI.createFileURI(cwd.getAbsolutePath()).appendSegment(""); // trailing slash
      assertThat(serverConfiguration.getWorkspaceRootURI(), is(expected));
      assertThat(serverConfiguration.getWorkspaceRoot(), is(cwd.getAbsolutePath() + "/"));
   }

   @Test
   public void isValidWorkspaceRoot() {
      assertThat(ServerConfiguration.isValidWorkspaceRoot(getCWD().getAbsolutePath()), is(true));
      assertThat("file URI deemed invalid",
         ServerConfiguration.isValidWorkspaceRoot(URI.createFileURI(getCWD().getAbsolutePath()).toString()),
         is(true));
      assertThat("relative path deemed invalid", ServerConfiguration.isValidWorkspaceRoot("."), is(true));
      String bogusPath = new File(getCWD(), "$this$/cannot/likely/exist").getAbsolutePath();
      assertThat("non-existent path deemed valid", ServerConfiguration.isValidWorkspaceRoot(bogusPath), is(false));
   }

   @Test
   public void getWorkspaceEntries() {
      assumeThat(getCWD().getName(), is("com.eclipsesource.modelserver.emf"));
      serverConfiguration.setWorkspaceRoot(".");
      assertThat(serverConfiguration.getWorkspaceEntries(), hasItem(endsWith("/ServerConfigurationTest.class")));
   }

   //
   // Test framework
   //

   static File getCWD() { return new File(System.getProperty("user.dir")); }

}
