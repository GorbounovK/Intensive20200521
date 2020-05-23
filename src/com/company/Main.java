package com.company;
/*
 * Date: 22.05.2020<br/>
 * Time: 17:10<br/>
 * Author: Горбунов Константин, gorbounov.k@gmail.com
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        String ACCESS_TOKEN = "0Wi2ZoXkI9QAAAAAAAACTdUbEDVZ_J0xkuKk-jwM1Bl1FcYeIWJKb5gqlmqazv5e";

        int timeout = 5;   // интервал создания скриншотов в секундах

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

        Semaphore sem = new Semaphore(2); // разрешаем 2 потока

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /*
                 * через заданный интервал создаем новый поток для отправки скриншота
                 */
                Date now = new Date();
                System.out.println("Таймер сработал - " + formatter.format(now));
                try {
                    MyThread thread = new MyThread(sem);
                    thread.threadNumber = 1;
                    thread.ACCESS_TOKEN = ACCESS_TOKEN;
                    thread.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        long delay = 0;
        long interval = timeout * 1000;
        timer.scheduleAtFixedRate(task, delay, interval);

//        System.out.println("Main finished");
    }


}
