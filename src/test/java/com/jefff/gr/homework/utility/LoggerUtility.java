package com.jefff.gr.homework.utility;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerUtility
{
    static public void initRootLogger()
    {
        Logger logger = Logger.getRootLogger();
        PatternLayout layout = new PatternLayout("%d %-5p %t %m%n");
        ConsoleAppender appender = new ConsoleAppender(layout);
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);
    }
}
