package com.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 日志
 * @author: 小猴子
 * @date: 2020-05-25 15:10
 */
@Configuration
public class LogConfig {

    public static final Logger Log = LoggerFactory.getLogger(Logger.class);


    @Bean
    public void logMethod(){
        Log.info("" +
                "______         _  _                          \n" +
                "| ___ \\       (_)| |                         \n" +
                "| |_/ /  __ _  _ | |      ___   _ __    __ _ \n" +
                "| ___ \\ / _` || || |     / _ \\ | '_ \\  / _` |\n" +
                "| |_/ /| (_| || || |____| (_) || | | || (_| |\n" +
                "\\____/  \\__,_||_|\\_____/ \\___/ |_| |_| \\__, |\n" +
                "         _____  _                _      __/ |\n" +
                "        /  ___|| |              | |    |___/ \n" +
                "        \\ `--. | |_  __ _  _ __ | |_         \n" +
                "         `--. \\| __|/ _` || '__|| __|        \n" +
                "        /\\__/ /| |_| (_| || |   | |_         \n" +
                "        \\____/  \\__|\\__,_||_|    \\__|        \n" +
                "                                             \n" +
                "                                             ");

    }

    private void console(String console){
        Log.info("\033[31;0m"+console+"\033[0m");
    }



}


