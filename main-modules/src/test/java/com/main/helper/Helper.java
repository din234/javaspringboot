package com.main.helper;

import java.io.File;
import java.io.IOException;

public class Helper {
    public static void sleepFor(Integer time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static CustomFileWriter openFileWriter(String pathname) {
        try {
            File file = new File(pathname);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            CustomFileWriter fw = new CustomFileWriter(pathname);
            return fw;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
