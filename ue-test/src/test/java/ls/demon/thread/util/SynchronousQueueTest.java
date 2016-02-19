/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.thread.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: SynchronousQueueTest.java, v 0.1 2015年10月28日 上午11:16:20 song.li@witontek.com Exp $
 */
public class SynchronousQueueTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(SynchronousQueueTest.class);

    public static void main(String[] args) {
        ExecutorService exe = Executors.newFixedThreadPool(4);

        final SynchronousQueue<String> sq = new SynchronousQueue<String>();

        exe.execute(new Runnable() {

            @Override
            public void run() {
                for (int i = 0;; i++) {
                    try {
                        //Thread.sleep(1000);
                        String obj = "X" + i;
                        boolean r = sq.offer(obj, 1, TimeUnit.SECONDS);
                        logger.info("[add] {} {} , {}", Thread.currentThread().getName(), obj, r);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }
                }

            }
        });

        // 
        exe.execute(new Runnable() {

            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(2000);
                        // String obj = sq.poll(1, TimeUnit.SECONDS);
                        String obj = sq.poll();
                        logger.info("[get] {} {}", Thread.currentThread().getName(), obj);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }
                }

            }
        });
    }
}
