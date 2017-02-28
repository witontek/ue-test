/**
 * Witontek.com.
 * Copyright (c) 2012-2017 All Rights Reserved.
 */
package ls.demon.xx.web.controller.pwd;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ls.demon.xx.web.controller.pwd.util.AESCoder;
import ls.demon.xx.web.controller.pwd.util.PasswordUtils;

/**
 * http://localhost:8080/ut/s/tool/pwd.htm
 * 
 * @author song.li@witontek.com
 * @version $Id: PwdController.java, v 0.1 2017年2月28日 下午3:56:35 song.li@witontek.com Exp $
 */
@Controller
@RequestMapping("/tool/pwd.htm")
public class PwdController {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(PwdController.class);

    //    private static final String KEY    = "9H9d8jNbS5wKgfaATf6E1w==";
    private static final String KEY    = "owNYZ1qRZmZCPNkXoKUPv+uWNF75uaLKdBLgsHhzdcA=";

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap modelMap, String pwd) {
        logger.info("pwd = {}", pwd);

        return "tool/pwd";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap modelMap, String pwd) {
        logger.info("pwd = {}", pwd);

        String encryptPwd = PasswordUtils.encrypt(pwd, KEY);
        modelMap.addAttribute("encryptPwd", encryptPwd);
        modelMap.addAttribute("pwd", pwd);
        return "tool/pwd";
    }

    public static void main(String[] args) {
        try {
            logger.info("{}", Base64.encodeBase64String(AESCoder.initKey()));
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
