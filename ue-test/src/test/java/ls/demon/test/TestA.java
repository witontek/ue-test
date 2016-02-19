/**
 * LS_Demon Org.
 * Copyright (c) 2005-2015 All Rights Reserved.
 */
package ls.demon.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author lisong
 * @version $Id: TestA.java, v 0.1 2015年2月11日 下午8:37:50 lisong Exp $
 */
public class TestA {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(TestA.class);

    @Test
    public void test_show() {
        try {
            logger.error("{}", URLEncoder.encode("?", "utf-8"));
            logger.error("{}", URLDecoder.decode("%3F", "utf-8"));
            logger.error("xxxx");
            String s1 = "eJwBIADf%2F8gO31arHpnEbSuctM0JbhhXlsT3fxIhqjaAsRf2INp1%2FycPXQ%3D%3D";
            logger.error("{}", URLDecoder.decode(s1, "utf-8"));
            logger.error("{}", URLDecoder.decode(s1, "gbk"));

            String s2 = "eJwBIADf/8gO31arHpnEbSuctM0JbhhXlsT3fxIhqjaAsRf2INp1/ycPXQ==";
            logger.error("{}", Base64.decodeBase64(s2));
            logger.error("{}", Hex.encodeHexString(Base64.decodeBase64(s2)));
            logger.error("{}", new String(Base64.decodeBase64(s2), "utf-8"));
            logger.error("{}", new String(Base64.decodeBase64(s2), "gbk"));
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
    }

    @Test
    public void test_show_xx() {
        try {
            logger.error("2222222");
            String s1 = "eJwBwAE%2F%2Fmmy1%2BEjVkwvI2ZX3C41eg4uxPpHGNiJRmeV2ccyR76gpa4yo4R7YLJNZ3bQOY4o5skLm1N3Ay%2F527L1Pdci%2FGjef5lMDywXV53w9sc3wDb5EpkaonQBRGUcbTNEMlj2VWqUTpYPmBQl%2BkoPbjIAyNi%2FbZPZCgb7H%2F6SdXRmZTVwS%2BUP9XqNk6kQqXnzMQLXa2eGFwlZIOXCtgHoX96SYqVWcLCB%2BIHg1K6xuobcht7Rcvisy5l%2BLmEZTpZFTQMtZi8IS72EHWf%2BS1Xh%2F%2Fqp8Qs5T5ENTjeRiR%2FA8o0HzoLQft9lAg3Deon4T2bT%2B76PYESIR5CkhIH6JiXnSbXeVka8VWxbYYuLXj7fuPlDBWdBmRpE5OuDDxoxLWPOjvF%2BUWB2otowsLrbuGMRz0oSbO%2FMvH68PgEGpnjuCXJ%2BsEvPk2%2BGCaInkF1vCVd1YAVlGqDBpaKcfK4u6aHfg%2FZzoCSzKCIPCjn4WSi0NICUeXJ4DTdYKX6ylEboD5Cu1IYOTMizVYLCVfUgXIKETT6FEU%2F32nul3DWbJeiNKQee2MttBGPI3snlwxWqh96q337cnaC5D6jWmBuapEcSvSBqppoHXdnr";
            logger.error("{}", URLDecoder.decode(s1, "utf-8"));
            logger.error("{}", URLDecoder.decode(s1, "gbk"));

            String s2 = "eJwBwAE//mmy1+EjVkwvI2ZX3C41eg4uxPpHGNiJRmeV2ccyR76gpa4yo4R7YLJNZ3bQOY4o5skLm1N3Ay/527L1Pdci/Gjef5lMDywXV53w9sc3wDb5EpkaonQBRGUcbTNEMlj2VWqUTpYPmBQl+koPbjIAyNi/bZPZCgb7H/6SdXRmZTVwS+UP9XqNk6kQqXnzMQLXa2eGFwlZIOXCtgHoX96SYqVWcLCB+IHg1K6xuobcht7Rcvisy5l+LmEZTpZFTQMtZi8IS72EHWf+S1Xh//qp8Qs5T5ENTjeRiR/A8o0HzoLQft9lAg3Deon4T2bT+76PYESIR5CkhIH6JiXnSbXeVka8VWxbYYuLXj7fuPlDBWdBmRpE5OuDDxoxLWPOjvF+UWB2otowsLrbuGMRz0oSbO/MvH68PgEGpnjuCXJ+sEvPk2+GCaInkF1vCVd1YAVlGqDBpaKcfK4u6aHfg/ZzoCSzKCIPCjn4WSi0NICUeXJ4DTdYKX6ylEboD5Cu1IYOTMizVYLCVfUgXIKETT6FEU/32nul3DWbJeiNKQee2MttBGPI3snlwxWqh96q337cnaC5D6jWmBuapEcSvSBqppoHXdnr";
            logger.error("{}", Base64.decodeBase64(s2));
            logger.error("{}", Hex.encodeHexString(Base64.decodeBase64(s2)));
            logger.error("{}", new String(Base64.decodeBase64(s2), "utf-8"));
            logger.error("{}", new String(Base64.decodeBase64(s2), "gbk"));
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
    }

    public <T> T getO(Class<T> a, Object o) {
        return a.cast(o);
    }

}
