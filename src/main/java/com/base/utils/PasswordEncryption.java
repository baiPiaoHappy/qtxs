package com.base.utils;

import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricConfig;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricStringEncryptor;
import org.jasypt.encryption.StringEncryptor;

/**
 * @description: 密码加密类
 * @author: 小猴子
 * @date: 2019-11-07 9:56
 */

public class PasswordEncryption {

    public static void main(String[] args) {
        String encrypted = PropertyEncryptor("Qwer123DB.");
        System.out.printf("Encrypted message %s\n", encrypted);
    }



    //加密
    public static String PropertyEncryptor(String string){
        SimpleAsymmetricConfig  config =  new SimpleAsymmetricConfig();
        config.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArQfyGCvBOdgmDGU6ciGPVNB6jHsMip0b0qOrPvVTSJ/x0offjKARogA2tjGjyr3rUtwg9woMBqv/iyENR0GBnIUa0jkYsznCKeygcflnNa4mrVf7XKXLhSwtY+kCe3diPk+0QPfEsfF9/aK6pWBUFcrE8P2k2sF/8mo8dFJU1t6zQGPspHkNAgR6MLU8SjPZxnMS6EG722MdYhvSYAKsnu02Hozqb4jh/gaQ/E6NkvM3DkqIyIYsRH2smstIFEb9CCiTdiz/OsJKQLgGy/pqIVKtai3lnUxAayEV45Z61rNTOusNJf+icGhZxjqhAeoWjMxOCVmVC2GKa9sisqBgkQIDAQAB");
        StringEncryptor encryptor = new SimpleAsymmetricStringEncryptor(config);
        String message = string;
        String encrypted = encryptor.encrypt(message);
        return encrypted;
    }



}
