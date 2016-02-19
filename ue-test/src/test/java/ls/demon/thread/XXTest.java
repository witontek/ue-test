/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: XXTest.java, v 0.1 2015年12月10日 下午3:26:09 song.li@witontek.com Exp $
 */
public class XXTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(XXTest.class);

    public static void main(String[] args) {
        int parties = 2;
        ExecutorService exe = Executors.newFixedThreadPool(parties);

        final TestValue t = new TestValue();

        for (int i = 0; i < parties; i++) {
            exe.execute(new Runnable() {

                @Override
                public void run() {
                    for (int i = 1; i <= 10000; i++) {
                        t.balance.addAndGet(1);
                    }
                    logger.info("end {}", Thread.currentThread().getName());
                }
            });
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("", e);
        }

        System.out.println(String.format("%s", t.balance));
        exe.shutdown();
    }

    static class TestValue {
        // public int balance = 0;
        public AtomicInteger balance = new AtomicInteger(0);
    }
}
