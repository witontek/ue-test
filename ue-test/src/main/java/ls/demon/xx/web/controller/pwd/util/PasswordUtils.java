/**
 * Witontek.com.
 * Copyright (c) 2012-2017 All Rights Reserved.
 */
package ls.demon.xx.web.controller.pwd.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: PasswordUtils.java, v 0.1 2017年2月28日 下午2:08:12 song.li@witontek.com Exp $
 */
public class PasswordUtils {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    public static String encrypt(String src, String keyStr) {
        byte[] key = Base64.decodeBase64(keyStr);
        try {
            byte[] to = AESCoder.encrypt(Base64.encodeBase64(StringUtils.getBytesUtf8(src)), key);
            return Base64.encodeBase64URLSafeString(to);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static String decrypt(String src, String keyStr) {
        byte[] key = Base64.decodeBase64(keyStr);
        try {
            byte[] to = AESCoder.decrypt(Base64.decodeBase64(src), key);
            return StringUtils.newStringUtf8(Base64.decodeBase64(to));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
