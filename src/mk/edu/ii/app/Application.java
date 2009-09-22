package mk.edu.ii.app;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class Application {

	static final Logger logger = Logger.getLogger(Application.class);
	
	public Application() {
		init();
	}

	public void init() {
//		apply file configuration to logging system
		PropertyConfigurator.configure("log4j.configuration");
		logger.info("Configuration for logging system loaded");
	}
}
