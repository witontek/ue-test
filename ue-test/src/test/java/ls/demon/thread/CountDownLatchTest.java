/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: CountDownLatchTest.java, v 0.1 2015年10月23日 下午2:10:51 song.li@witontek.com Exp $
 */
public class CountDownLatchTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) {
        int parties = 2;
        ExecutorService exe = Executors.newFixedThreadPool(4);

        final CountDownLatch cdl = new CountDownLatch(parties);

        for (int i = 0; i < parties; i++) {
            exe.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        logger.info("[END] {}", Thread.currentThread().getName());
                        cdl.countDown();
                        // Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }

                }
            });
        }

        try {
            cdl.await();
            logger.info("all over");
        } catch (InterruptedException e) {
            logger.error("", e);
        }
    }
}
