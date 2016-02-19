/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: ExceptionTest.java, v 0.1 2015年12月25日 下午4:45:01 song.li@witontek.com Exp $
 */
public class ExceptionTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    public static void main(String[] args) {
        int parties = 2;
        ExecutorService exe = Executors.newFixedThreadPool(parties);

        logger.info("start");
        for (int i = 0; i < parties; i++) {
            final int index = i;
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    throw new NullPointerException("aaaaa" + index);
                }
            });
        }

        logger.info("end");
    }
}
