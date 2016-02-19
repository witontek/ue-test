/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: DigestTest.java, v 0.1 2016年1月22日 上午11:26:57 song.li@witontek.com Exp $
 */
public class DigestTest {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(DigestTest.class);

    @Test
    public void test_001() {

        String[] ss = new String[] { "QOaKcVwfSQGZvjfofVuDBQ==", "QOaKcVwfSQGZvjfofVuDBQ==",
                "87T7QCEQg52tKOCym5a5dg==", "QOaKcVwfSQGZvjfofVuDBQ==", "QOaKcVwfSQGZvjfofVuDBQ==",
                "6bwOE6ihbLsHsXXZKhExJg==", "wovuPs3aeSNy76KqiN9UHA==", "7g5OlFF5ENLONlB5/KYZiw==",
                "JplOBeCVwi5boV2j3T0kBA==", "zGRVNoXP8m8QN02GMO4wig==", "QOaKcVwfSQGZvjfofVuDBQ==",
                "TUr2E1a5QdDqIUp3E7iQow==", "9Pl0pCS/cstFUF90Gl0Cig==", "Ey14ZG6RTzIopaOcFTr3WQ==",
                "QOaKcVwfSQGZvjfofVuDBQ==", "aUtcgwF0VkP7XGwyxJhYIQ==", "QOaKcVwfSQGZvjfofVuDBQ==",
                "6KMKZI8GyX66tlv5ChdZvg==", "rphb2lUH2H2NVw9XR7fqbw==", "LWOKb2a35Dzt5ENf70QZkQ==",
                "XUwNCzHogHpDeK8DVeQJHg==", "oiGp8E+3Cv+adxUEcYGjow==", "AUcwwcnm9vGF7XhMrANn3A==",
                "1JnwqlLMx2pjvj3/xoiEwQ==",

        };

        for (String s : ss) {
            if (StringUtils.isNotBlank(s)) {
                logger.info("{} = {}", s, Hex.encodeHexString(Base64.decodeBase64(s)));
            }
        }
        // logger.info("{}", Hex.encodeHexString(Base64.decodeBase64("QOaKcVwfSQGZvjfofVuDBQ==")));
    }

    @Test
    public void test_002() {
        logger.info("{}", DigestUtils.md5Hex("12345"));
    }
}
