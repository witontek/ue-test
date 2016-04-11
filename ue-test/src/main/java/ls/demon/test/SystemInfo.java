/**
 * Witontek.com.
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package ls.demon.test;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Administrator
 * @version $Id: SystemInfo.java, v 0.1 2016年4月11日 上午10:57:08 Administrator Exp $
 */
@Component
public class SystemInfo implements InitializingBean {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(SystemInfo.class);

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            logger.info("{} = {}", key, env.get(key));
        }
        logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        Properties props = System.getProperties();
        for (Object key : props.keySet()) {
            logger.info("{} = {}", key, props.get(key));
        }
    }

}
