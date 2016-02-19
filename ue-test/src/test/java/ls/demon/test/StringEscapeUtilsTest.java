/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: StringEscapeUtilsTest.java, v 0.1 2015年9月15日 下午1:40:55 song.li@witontek.com Exp $
 */
public class StringEscapeUtilsTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(StringEscapeUtilsTest.class);

    @Test
    public void test_sql_escape() {
        logger.info("{}", StringEscapeUtils.escapeSql(" % ' "));
        logger.info("{}", StringEscapeUtils.escapeSql(" % \" "));
    }
}
