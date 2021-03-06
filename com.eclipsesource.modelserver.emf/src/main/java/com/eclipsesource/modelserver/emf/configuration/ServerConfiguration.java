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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

import com.eclipsesource.modelserver.emf.launch.ModelServerLauncher;

/**
 * Singleton class that holds configurations parameters for a model server
 * instance.
 *
 */
public class ServerConfiguration {
   private URI workspaceRootURI = URI.createURI("");
   private static Logger LOG = Logger.getLogger(ServerConfiguration.class);
   private int serverPort = ModelServerLauncher.DEFAULT_JAVALIN_PORT;

   public URI getWorkspaceRootURI() { return workspaceRootURI; }

   public void setWorkspaceRootURI(final URI uri) { this.workspaceRootURI = uri; }

   public String getWorkspaceRoot() { return workspaceRootURI.toFileString(); }

   public void setWorkspaceRoot(final String workspaceRoot) {
      toFilePath(workspaceRoot)
         .map(ServerConfiguration::ensureDirectory)
         .ifPresent(uri -> this.workspaceRootURI = uri);
   }

   public Set<String> getWorkspaceEntries() {
      Set<String> filePaths = new HashSet<>();

      URI normalized = URIConverter.INSTANCE.normalize(workspaceRootURI);
      if (normalized.isFile()) {
         try (Stream<Path> paths = Files.walk(Paths.get(normalized.toFileString()))) {
            paths
               .filter(Files::isRegularFile)
               .forEach(file -> filePaths.add(file.toString()));
         } catch (InvalidPathException i) {
            LOG.error(String.format("Could not get workspace path! ’%s’ cannot be converted to a valid Path",
               this.workspaceRootURI));
         } catch (IOException io) {
            LOG.error(String.format("IOException occured while reading files in workspace ’%s’: " + io.getMessage(),
               this.workspaceRootURI));
         } catch (SecurityException s) {
            LOG.error("Security manager denies access to files under workspace " + this.workspaceRootURI);
         }
      }
      return filePaths;
   }

   public int getServerPort() { return serverPort; }

   public void setServerPort(final int serverPort) { this.serverPort = serverPort; }

   public static boolean isValidWorkspaceRoot(final String fileUrl) {
      return toFilePath(fileUrl).map(uri -> new File(uri.toFileString()).exists()).orElse(false);
   }

   public static boolean isValidPort(final Integer port) {
      return port >= 0 && port <= 65535;
   }

   private static Optional<URI> toFilePath(final String fileUrl) {
      try {
         URI uri = URI.createURI(fileUrl, true);
         if (uri.scheme() == null) {
            uri = URI.createFileURI(fileUrl);
         }
         if (uri.isRelative()) {
            URI cwd = URI.createFileURI(System.getProperty("user.dir"));
            uri = uri.resolve(ensureDirectory(cwd));
         }
         return Optional.ofNullable(uri).filter(URI::isFile);
      } catch (NullPointerException | IllegalArgumentException e) {
         LOG.warn(String.format("Could not convert to filePath! ’%s’ is not a valid URL", fileUrl));
         return Optional.empty();
      }
   }

   private static URI ensureDirectory(final URI uri) {
      return uri.hasTrailingPathSeparator()
         ? uri
         : uri.appendSegment("");
   }
}
