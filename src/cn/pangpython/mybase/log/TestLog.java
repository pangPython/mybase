package cn.pangpython.mybase.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        logger.info("TestLog version:{} buildtime:{}","v0.1","2018年3月1日 22:50:10");
        logger.info("TestLog memeory:{} cpu:{}","3.08G","10%");
    }
}
