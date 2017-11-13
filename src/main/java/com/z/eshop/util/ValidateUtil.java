package com.z.eshop.util;

import java.util.Collection;

/**
 * Created by root on 17-11-12.
 */

/**
 * 校验工具类
 */
public class ValidateUtil {
    /**
     * 判断集合的有效性
     * @param col
     * @return
     */
    public static boolean isValid(Collection col){
        if(col == null || col.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
