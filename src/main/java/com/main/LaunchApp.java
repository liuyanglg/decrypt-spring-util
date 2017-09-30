package com.main;

import com.dserv.jdbc.DservJdbcDecrypt;
import com.manager.jdbc.ManagerJdbcDecrypt;
import com.manager.login.ManagerLoginDecrypt;
import com.util.SpringContext;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

public class LaunchApp {
    public static Logger LOG = Logger.getLogger(LaunchApp.class);

    public static void main(String[] args) {
        SpringContext.init();
        int choose = menu();
        while (choose != 0) {
            switch (choose) {
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                case 3:
                    case3();
                    break;
                default:
                    break;
            }
            choose = menu();
        }
    }

    private static int menu() {
        System.out.println();
        System.out.println("------------------------------------华丽的分割线--------------------------------------------");
        System.out.println("jdbc文件加解密提示：\n" +
                "加密：请将文件命名为jdbc.properties,放入config/jdbc/decrypt目录下，加密好的文件存放在encrypt下;\n" +
                "解密：请将文件命名为jdbc.properties,放入config/jdbc/encrypt目录下，解密好的文件存放在decrypt下;\n");
        System.out.print("" +
                "1. 综合平台登录密码加解密；\n" +
                "2. 综合平台jdbc.properties加解密；\n" +
                "3. 定时任务项目jdbc.properties加解密；\n" +
                "0. 退出；\n" +
                "请输入对应序号：");
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        if(no==0){
            System.out.println("程序已退出！");
            return no;
        }
        while (no < 1 | no > 3) {
            System.out.print("输入不合法请重新输入：");
            sc = new Scanner(System.in);
            no = sc.nextInt();
        }
        return no;
    }

    private static int subMenu() {
        System.out.print("" +
                "1.加密   2.解密，请输入：");
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        while (no < 1 | no > 2) {
            System.out.print("输入不合法请重新输入：");
            sc = new Scanner(System.in);
            no = sc.nextInt();
        }
        return no;
    }

    private static void case1() {
        int choose = subMenu();
        String input;
        ManagerLoginDecrypt decrypt = new ManagerLoginDecrypt();
        switch (choose) {
            case 1:
                input = readInput(choose);
                if (input != null && input.trim().length() > 0) {
                    decrypt.setDePassword(input.trim());
                } else {
                    System.out.println("输入为空，将使用配置文件中属性值！");
                }
                try {
                    String enData = decrypt.encrypt();
                    System.out.println("加密为：" + enData);
                } catch (Exception e) {
                    System.out.println("加密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            case 2:
                input = readInput(choose);
                if (input != null && input.trim().length() > 0) {
                    decrypt.setEnPassword(input.trim());
                } else {
                    System.out.println("输入为空，将使用配置文件中属性值！");
                }
                try {
                    String deData = decrypt.decrypt();
                    System.out.println("解密为：" + deData);
                } catch (Exception e) {
                    System.out.println("解密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            default:
                break;
        }
    }

    private static void case2() {
        int choose = subMenu();
        ManagerJdbcDecrypt decrypt = new ManagerJdbcDecrypt();
        switch (choose) {
            case 1:
                try {
                    String result = decrypt.encrypt();
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("加密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            case 2:
                try {
                    String result = decrypt.decrypt();
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("解密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            default:
                break;
        }
    }

    private static void case3() {
        int choose = subMenu();
        DservJdbcDecrypt decrypt = new DservJdbcDecrypt();
        switch (choose) {
            case 1:
                try {
                    String result = decrypt.encrypt();
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("加密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            case 2:
                try {
                    String result = decrypt.decrypt();
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println("解密过程出现了意外");
                    e.printStackTrace();
                    LOG.info(e);
                }
                break;
            default:
                break;
        }
    }

    private static String readInput(int choose) {
        if (choose == 1) {
            System.out.print("请输入需要加密的密码：");
        }
        if (choose == 2) {
            System.out.print("请输入需要解密的密码：");
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
