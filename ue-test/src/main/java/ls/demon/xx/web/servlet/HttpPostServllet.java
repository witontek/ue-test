/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.xx.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: HttpPostServllet.java, v 0.1 2015年8月14日 上午8:45:27 song.li@witontek.com Exp $
 */
public class HttpPostServllet extends HttpServlet {
    /**  */
    private static final long   serialVersionUID = 8263868935890015204L;
    /**
    * Logger for this class
    */
    private static final Logger logger           = LoggerFactory.getLogger(HttpPostServllet.class);

    /** 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                                                                          IOException {
        logger.info("{}", req.getContentType());
        logger.info("{}", req.getContentLength());
        logger.info("{}", req.getCharacterEncoding());

        logger.info("====================================== head");
        Enumeration<String> hs = req.getHeaderNames();
        while (hs.hasMoreElements()) {
            String name = (String) hs.nextElement();
            logger.info("{} = {}", name, req.getHeader(name));
        }
        logger.info("====================================== head");

        StringWriter sw = new StringWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),
            req.getCharacterEncoding()));
        for (;;) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            sw.write(line);
            sw.write("\n");
        }
        logger.info("content=\n{}", sw.toString());

        resp.getWriter().write("get end");
    }

    /** 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                                                           throws ServletException,
                                                                           IOException {

        logger.info("{}", req.getContentType());
        logger.info("{}", req.getContentLength());
        logger.info("{}", req.getCharacterEncoding());

        logger.info("====================================== head");
        Enumeration<String> hs = req.getHeaderNames();
        while (hs.hasMoreElements()) {
            String name = (String) hs.nextElement();
            logger.info("{} = {}", name, req.getHeader(name));
        }
        logger.info("====================================== head");

        StringWriter sw = new StringWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),
            req.getCharacterEncoding()));
        for (;;) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            sw.write(line);
            sw.write("\n");
        }
        logger.info("content=\n{}", sw.toString());

        resp.getWriter().write("post end");
    }

}
