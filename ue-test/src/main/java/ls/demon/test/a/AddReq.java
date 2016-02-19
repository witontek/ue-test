/**
 * Witontek.com.
 * Copyright (c) 2012-2015 All Rights Reserved.
 */
package ls.demon.test.a;

import ls.demon.xx.BaseModel;

/**
 * 
 * @author song.li@witontek.com
 * @version $Id: AddReq.java, v 0.1 2015年9月18日 上午10:49:06 song.li@witontek.com Exp $
 */
public class AddReq<T> extends BaseModel {

    /**  */
    private static final long serialVersionUID = -3148751779566065048L;

    private Class<T>          clazz;

    private Object            obj;

    private T                 target;

    /**
     * Getter method for property <tt>target</tt>.
     * 
     * @return property value of target
     */
    public T getTarget() {
        return target;
    }

    /**
     * Setter method for property <tt>target</tt>.
     * 
     * @param target value to be assigned to property target
     */
    public void setTarget(T target) {
        this.target = target;
    }

    /**
     * Getter method for property <tt>clazz</tt>.
     * 
     * @return property value of clazz
     */
    public Class<T> getClazz() {
        return clazz;
    }

    /**
     * Setter method for property <tt>clazz</tt>.
     * 
     * @param clazz value to be assigned to property clazz
     */
    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Getter method for property <tt>obj</tt>.
     * 
     * @return property value of obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * Setter method for property <tt>obj</tt>.
     * 
     * @param obj value to be assigned to property obj
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

}
