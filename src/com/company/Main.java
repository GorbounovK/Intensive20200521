package com.company;
/*
 * Date: 22.05.2020<br/>
 * Time: 17:10<br/>
 * Author: Горбунов Константин, gorbounov.k@gmail.com
 */

public class Main {

    public static void main(String[] args) {
            System.out.println("Lesson2");
            String ACCESS_TOKEN = "0Wi2ZoXkI9QAAAAAAAACTdUbEDVZ_J0xkuKk-jwM1Bl1FcYeIWJKb5gqlmqazv5e";

            MyThread thread = new MyThread();
            thread.threadNumber =1;
            thread.ACCESS_TOKEN = ACCESS_TOKEN;
            thread.timeout = 5000;
            thread.start();

      }


}
