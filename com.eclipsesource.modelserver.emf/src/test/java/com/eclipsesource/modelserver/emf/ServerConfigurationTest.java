package com.eclipsesource.modelserver.emf;

import com.eclipsesource.modelserver.emf.configuration.ServerConfiguration;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServerConfigurationTest {

    @Test
    public void normalizeWorkspaceRoot() {
        final ServerConfiguration serverConfiguration = new ServerConfiguration();
        serverConfiguration.setWorkspaceRoot("foo");
        assertThat(serverConfiguration.getWorkspaceRoot(), equalTo("foo/"));
    }

    @Test
    public void normalizeWorkspaceRoot_SlashAlreadyPresent() {
        final ServerConfiguration serverConfiguration = new ServerConfiguration();
        serverConfiguration.setWorkspaceRoot("foo/");
        assertThat(serverConfiguration.getWorkspaceRoot(), equalTo("foo/"));
    }
}
