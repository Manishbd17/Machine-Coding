package com.systemdesign.loggingframework;

import com.systemdesign.loggingframework.logger.Logger;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.info("this is info log");
        logger.error("this is error log");
        logger.debug("this is debug log");
    }
}
