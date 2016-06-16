/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.xx.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ls.demon.xx.web.controller.form.XXForm;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: AccessTotenToolController.java, v 0.1 2015年8月12日 上午10:57:13 song.li@witontek.com Exp $
 */
@Controller
@RequestMapping("/tool/at.htm")
public class AccessTotenToolController {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(AccessTotenToolController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap modelMap, String appId, String appSecret) {
        logger.info("appId={},appSecret={}", appId, appSecret);

        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appSecret)) {

            String timestamp = String.valueOf(System.currentTimeMillis());

            String accessToken = DigestUtils
                .md5Hex(String.format("%s%s%s", appId, appSecret, timestamp));

            modelMap.addAttribute("appId", appId);
            modelMap.addAttribute("appSecret", appSecret);
            modelMap.addAttribute("timestamp", timestamp);
            modelMap.addAttribute("checkToken", accessToken);
        }

        return "tool/at";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap modelMap, XXForm form,
                         HttpServletRequest request) throws IOException {
        logger.info("[doPost]form={}", form);

        logger.info("{}", request.getCharacterEncoding());
        logger.info("{}", request.getContentType());
        logger.info("{}", request.getHeaderNames());

        StringWriter sw = new StringWriter();
        BufferedReader br = new BufferedReader(
            new InputStreamReader(request.getInputStream(), request.getCharacterEncoding()));
        for (;;) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            sw.write(line);
        }
        logger.info("content={}", sw.toString());

        if (StringUtils.isNotBlank(form.getAppId())
            && StringUtils.isNotBlank(form.getAppSecret())) {

            String timestamp = String.valueOf(System.currentTimeMillis());

            String accessToken = DigestUtils
                .md5Hex(String.format("%s%s%s", form.getAppId(), form.getAppSecret(), timestamp));

            modelMap.addAttribute("appId", form.getAppId());
            modelMap.addAttribute("appSecret", form.getAppSecret());
            modelMap.addAttribute("timestamp", timestamp);
            modelMap.addAttribute("checkToken", accessToken);
        }

        return "tool/at";
    }

}
