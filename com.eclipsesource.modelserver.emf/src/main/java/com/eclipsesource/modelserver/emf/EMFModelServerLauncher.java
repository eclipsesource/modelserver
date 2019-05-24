package com.eclipsesource.modelserver.emf;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class EMFModelServerLauncher {
	private static Logger LOG = Logger.getLogger(EMFModelServerLauncher.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOG.info("Dummy EMFModelServer has been started");
	}

}
