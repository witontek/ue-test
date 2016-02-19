/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: MemTest.java, v 0.1 2016年1月12日 上午8:46:17 song.li@witontek.com Exp $
 */
public class MemTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(MemTest.class);

    public static void main(String[] args) {
        for (;;) {
            //logger.info("{}", i);
            try {
                byte[] buff = new byte[Integer.MAX_VALUE];
                logger.info("{}", buff.length);
            } catch (Throwable e) {
                logger.error("{}", e.getMessage());
            }
        }
    }
}
