package com.base.utils;

/**
 * @description: 控制台输出  /sout
 * @author: 小猴子
 * @date: 2020-05-25 15:58
 */
public class ConsoleUtil {



    public static void consoleRed(String console){
        System.out.println("\033[31;0m"+console+"\033[0m");
    }

    public static void consoleGreen(String console){
        System.out.println("\033[35;0m"+console+"\033[0m");
    }

    public static void consoleYellow(String console){
        System.out.println("\033[33;0m"+console+"\033[0m");
    }

    public static void consoleWhite(String console){
        System.out.println("\033[30;0m"+console+"\033[0m");
    }


}
