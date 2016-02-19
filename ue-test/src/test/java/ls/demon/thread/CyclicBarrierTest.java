/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: CyclicBarrierTest.java, v 0.1 2015年10月23日 下午1:48:41 song.li@witontek.com Exp $
 */
public class CyclicBarrierTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) {

        int parties = 2;
        ExecutorService exe = Executors.newFixedThreadPool(4);
        List<Integer> list = Arrays.asList(new Integer[] { new Integer(0), new Integer(10000) });

        final CyclicBarrier cb = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
                logger.info("all over");
            }
        });

        for (int i = 0; i < parties; i++) {
            exe.execute(new TestTask(cb, i, list));
        }

    }

    static class TestTask implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(TestTask.class);

        private CyclicBarrier       cb;
        private int                 index;
        private List<Integer>       result;

        public TestTask(CyclicBarrier cb, int index, List<Integer> result) {
            this.cb = cb;
            this.index = index;
            this.result = result;
        }

        @Override
        public void run() {
            for (int i = 0;; i++) {
                try {
                    cb.await();
                    result.set(index, result.get(index) + 1);
                    logger.info("[{}] index={} - {}:{}", Thread.currentThread().getName(), index,
                        i, result.get(index));
                } catch (Exception e) {
                    logger.error("", e);
                    break;
                }
            }
            logger.info("[END] {}", Thread.currentThread().getName());
        }
    }
}
