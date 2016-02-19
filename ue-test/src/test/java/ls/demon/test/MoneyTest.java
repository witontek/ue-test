/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test;

import java.math.RoundingMode;
import java.util.Locale;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: MoneyTest.java, v 0.1 2016年1月16日 下午5:26:18 song.li@witontek.com Exp $
 */
public class MoneyTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(MoneyTest.class);

    @Test
    public void test_001() {
        try {
            logger.info("");
            Money m = Money.of(CurrencyUnit.of(Locale.CHINA), 11.22);
            logger.info("{}", m);
            logger.info("{}", m.getAmount());
            logger.info("{}", m.getAmountMajor());
            logger.info("{}", m.getAmountMajorInt());
            logger.info("{}", m.getAmountMajorLong());

            logger.info("{}", m.getAmountMinor());
            logger.info("{}", m.getAmountMinorInt());

            logger.info("{}", m.getMinorPart());

        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_002() {
        try {
            logger.info("");
            Money m = Money.of(CurrencyUnit.of(Locale.CHINA), 11.11);
            Money m2 = Money.of(CurrencyUnit.of(Locale.CHINA), 11.22);

            logger.info("{}", m.getAmount().multiply(m2.getAmount()));
            logger.info("{}", m.multipliedBy(11.22, RoundingMode.HALF_EVEN));

        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
