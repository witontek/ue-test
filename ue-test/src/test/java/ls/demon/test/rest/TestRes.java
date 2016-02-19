/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: TestRes.java, v 0.1 2016年2月5日 下午1:37:45 song.li@witontek.com Exp $
 */
public class TestRes implements AutoCloseable {
    /**
    * Logger for this class
    */
    private static final Logger logger  = LoggerFactory.getLogger(TestRes.class);

    private boolean             isError = false;

    private String              name;

    /**
     * @param isError
     */
    public TestRes(String name, boolean isError) throws IOException {
        this.name = name;
        this.isError = isError;
        if (isError) {
            throw new IOException("error");
        }
    }

    /**
     * 
     */
    public TestRes(String name) throws IOException {
        this(name, false);
    }

    /**
     * Getter method for property <tt>isError</tt>.
     * 
     * @return property value of isError
     */
    public boolean isError() {
        return isError;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /** 
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws Exception {
        logger.info("{} close", this.name);
        throw new Exception("[" + name + "] exception.");
    }

}
