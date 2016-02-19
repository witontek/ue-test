/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.xx.web;

import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * md5签名使用方法
 * 
 * @author song.li@witontek.com
 * @version $Id: SignUtil.java, v 0.1 2015年6月5日 下午3:13:51 song.li@witontek.com Exp $
 */
public class SignUtil {

    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 对参数进行排序后，拼接成：key1=value1,key2=value2,...,keyN=valueN,salt
     * 进行md5摘要，返回md5值
     * 
     * @param params    参数集
     * @param salt      混淆用
     * @return
     */
    public static String sign(Map<String, String> params, String salt) {
        TreeSet<String> keys = new TreeSet<>(params.keySet());
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append(",");
        }
        sb.append(salt);
        String str = sb.toString();
        logger.info("{}", str);
        return DigestUtils.md5Hex(str);
    }

    public static String toSignStr(Map<String, String> params, String salt) {
        TreeSet<String> keys = new TreeSet<>(params.keySet());
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(params.get(key)).append(",");
        }
        sb.append(salt);
        String str = sb.toString();
        logger.info("{}", str);
        return str;
    }

    /**
     * 
     * @param params
     * @param salt
     * @return
     */
    public static String signForSha1(Map<String, String> params) {
        TreeSet<String> keys = new TreeSet<>(params.keySet());
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (String key : keys) {
            if (!first) {
                sb.append("&");
            }
            sb.append(key).append("=").append(params.get(key));
            first = false;
        }

        String str = sb.toString();
        logger.debug("{}", str);
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 
     * @param params
     * @param salt
     * @param sign
     * @return
     */
    public static boolean verifySign(Map<String, String> params, String salt, String sign) {
        String s = sign(params, salt);
        logger.debug("s = {}", s);
        return StringUtils.equalsIgnoreCase(s, sign);
    }
}
