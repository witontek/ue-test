/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.test.a;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: AddService.java, v 0.1 2015年9月18日 上午10:50:24 song.li@witontek.com Exp $
 */
public interface AddService {

    public <T> void add(AddReq<T> addReq);
}
