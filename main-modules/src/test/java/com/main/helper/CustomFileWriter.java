package com.main.helper;

import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWriter extends FileWriter {

    public CustomFileWriter(String fileName) throws IOException {
        super(fileName);
    }



    @Override
    public void write(int number){
        try {
            super.write(number);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(String text){
        try {
            super.write(text);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try {
            super.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeAndFlush(String text){
        try {
            super.write(text);
            super.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
