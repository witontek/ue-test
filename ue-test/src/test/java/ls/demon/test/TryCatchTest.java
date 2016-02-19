/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test;

import ls.demon.test.rest.TestRes;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: TryCatchTest.java, v 0.1 2016年2月5日 下午1:32:59 song.li@witontek.com Exp $
 */
public class TryCatchTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(TryCatchTest.class);

    @Test
    public void test_try_catch() {
        try (TestRes t = new TestRes("a")) {
            logger.info("{}", t.isError());
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_try_catch_inn_ex() {
        try (TestRes t = new TestRes("a")) {
            logger.info("{}", t.isError());
            throw new Exception("inn ex");
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_try_catch_ex() {
        try (TestRes t0 = new TestRes("a"); TestRes t = new TestRes("b", true)) {
            logger.info("{}", t0.isError());
            logger.info("{}", t.isError());

            logger.info("{}", "finish");
        } catch (Exception e) {
            logger.error(String.format("获取资源异常 %s", e.getMessage()), e);
        }
    }

    @Test
    public void test_try_catch_close_ex() {
        try (TestRes t0 = new TestRes("a"); TestRes t = new TestRes("b", false)) {
            logger.info("{}", t0.isError());
            logger.info("{}", t.isError());
            logger.info("{}", "finish");
        } catch (Exception e) {
            logger.error(String.format("获取资源异常 %s", e.getMessage()), e);
        }
    }
}
