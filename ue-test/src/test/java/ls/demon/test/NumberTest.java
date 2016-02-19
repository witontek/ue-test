/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: NumberTest.java, v 0.1 2015年10月23日 上午11:06:34 song.li@witontek.com Exp $
 */
public class NumberTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(NumberTest.class);

    @Test
    public void test_long() {
        {
            logger.info("{}", Double.POSITIVE_INFINITY);
            logger.info("{}", 10.0 / 0);
            logger.info("{}", 10 / 0.0);
        }
        logger.info("xxxxxxxxxxxxxxxxxxxxxxx");
        {
            logger.info("{}", Double.NEGATIVE_INFINITY);
            logger.info("{}", -10.0 / 0);
            logger.info("{}", -10 / 0.0);
        }
        logger.info("xxxxxxxxxxxxxxxxxxxxxxx");
        {
            logger.info("{}", -10 / 0.0);
            logger.info("{}", 10 / -0.0);
            logger.info("{}", -10 / -0.0);
        }
        logger.info("xxxxxxxxxxxxxxxxxxxxxxx");
        {
            logger.info("[-1.0 / 0.0] isInfinite = {}", Double.isInfinite(-1.0 / 0.0));
            logger.info("[1.0 / 0.0] isInfinite = {}", Double.isInfinite(1.0 / 0.0));
            logger.info("[0.0 / 0.0] isNan = {}", Double.isNaN(0.0 / 0.0));
            logger.info("[1.0 / 0.0] isNan = {}", Double.isNaN(1.0 / 0.0));
        }
        logger.info("xxxxxxxxxxxxxxxxxxxxxxx");
        try {
            logger.info("{}", 10 / 0);
        } catch (Exception e) {
            logger.error("", e);
        }

    }
}
