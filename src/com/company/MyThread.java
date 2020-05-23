package com.company;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.RateLimitException;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/*
 * Date: 22.05.2020<br/>
 * Time: 17:18<br/>
 * Author: Горбунов Константин, gorbounov.k@gmail.com
 */
public class MyThread extends Thread {
    public int threadNumber;
    public String ACCESS_TOKEN;

    Semaphore sem;
//    public int timeout;

    MyThread(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        System.out.println(getName() + " - started");
        try {
            System.out.println(getName() + " - ожидает разрешения");
            sem.acquire();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date now = new Date();


            // Create Dropbox client
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);


            Robot r = new Robot();
            BufferedImage image = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            InputStream in = new ByteArrayInputStream(os.toByteArray());

            String imageFileName = "/" + formatter.format(now) + ".png";
            client.files().uploadBuilder(imageFileName).uploadAndFinish(in);
            System.out.println(imageFileName + " uploaded");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
        System.out.println(getName() + " - finished");

//        }
    }
}
