package UtilityManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Logger {

    public static final org.apache.logging.log4j.Logger logger
            = org.apache.logging.log4j.LogManager.getLogger(org.apache.logging.log4j.core.Logger.class.getName());
    public static StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    public static StackTraceElement thread = stackTraceElements[4];

    public static void info(String message) {
        logger.info("{} - {}", thread.getMethodName(), message);
    }
    public static void error(String message) {
        logger.error("{} - {}", thread.getMethodName(), message);
    }
    public static void warn(String message) {
        logger.warn("{} - {}", thread.getMethodName(), message);
    }
    public static void debug(String message) {
        logger.debug("{} - {}", thread.getMethodName(), message);
    }
}
