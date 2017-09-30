package com.manager.login;

import com.manager.login.util.CryptUtil;
import com.util.SpringContext;
import org.apache.log4j.Logger;

import java.util.Map;

public class ManagerLoginDecrypt {
    public static Logger LOG = Logger.getLogger(ManagerLoginDecrypt.class);

    private  String key = "7041034e724f4d0b92df9bcc25f1890c";
    private  String enPassword = null;
    private  String dePassword = null;

    public void setKey(String key) {
        this.key = key;
    }

    public void setEnPassword(String enPassword) {
        this.enPassword = enPassword;
    }

    public void setDePassword(String dePassword) {
        this.dePassword = dePassword;
    }

    private Map map;


    public ManagerLoginDecrypt() {
        map = (Map<String,String>) SpringContext.getBean("managerLoginConfig");
    }

    public   String decrypt() throws Exception{
        String tKey = (String) map.get("key");
        String tPassword = (String) map.get("enPassword");
        if(tKey!=null&&tKey.length()>0){
            key = tKey;
        }
        if(enPassword!=null&&enPassword.trim().length()>0){
        }else {
            enPassword = tPassword;
        }
        LOG.info("综合平台登录密码密文："+enPassword);
        byte[] deBytes = enPassword.getBytes("UTF-8");
        byte[] tBytes = CryptUtil.hex2byte(deBytes);
        byte[] enBytes = CryptUtil.decrypt(key, tBytes);
        String dePassword = new String(enBytes);
        LOG.info("综合平台登录密码解密："+dePassword);
        return dePassword;
    }

    public   String encrypt() throws Exception{
        String tKey = (String) map.get("key");
        String tPassword = (String) map.get("dePassword");
        if(tKey!=null&&tKey.length()>0){
            key = tKey;
        }
        if(dePassword!=null&&dePassword.trim().length()>0){
        }else {
            dePassword = tPassword;
        }
        LOG.info("综合平台登录密码原文："+dePassword);

        byte[] deBytes = dePassword.getBytes("UTF-8");
        byte[] enBytes = CryptUtil.encrypt(key,deBytes);
        enPassword = CryptUtil.byte2hex(enBytes);

        LOG.info("综合平台登录密码加密："+enPassword);
        return enPassword;
    }

}
