package librarysystem.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SimpleLogger {

    private static final Logger logger = LogManager.getLogger();

    private SimpleLogger() {
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logDebug(Object object) {
        logger.debug(object);
    }

    public static void logError(Exception e) {
        logger.error(e);
    }
}
