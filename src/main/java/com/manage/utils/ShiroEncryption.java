package com.manage.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Description:加密工具类
 * Author:kbq
 * Date: 2019-11-27 14:21
 */
public class ShiroEncryption {

    public static String shiroEncryption(String password, String salt){
        int times = 2;
        String algorithmName = "MD5";
        String encodePassword = new SimpleHash(algorithmName,password,salt,times).toString();
        return encodePassword;
    }
}
