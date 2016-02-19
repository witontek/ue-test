/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.xx;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: BaseModel.java, v 0.1 2015年8月13日 下午5:02:52 song.li@witontek.com Exp $
 */
public class BaseModel implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
