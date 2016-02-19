/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.test.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: AddServiceImpl.java, v 0.1 2015年9月18日 上午10:51:39 song.li@witontek.com Exp $
 */
public class AddServiceImpl implements AddService {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(AddServiceImpl.class);

    /** 
     * @see ls.demon.test.a.AddService#add(ls.demon.test.a.AddReq)
     */
    @Override
    public <T> void add(AddReq<T> addReq) {
        T rr = addReq.getClazz().cast(addReq.getObj());

        logger.info("{}", rr);

    }

}
