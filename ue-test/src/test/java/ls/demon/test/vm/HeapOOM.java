/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: HeapOOM.java, v 0.1 2016年8月25日 下午3:56:26 song.li@witontek.com Exp $
 */
public class HeapOOM {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(HeapOOM.class);

    static class OOMObject {
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        logger.info("{}", Thread.class.getResource("/").getPath());
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (;;) {
            list.add(new OOMObject());
        }

    }

}
