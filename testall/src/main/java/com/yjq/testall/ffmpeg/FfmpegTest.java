package com.yjq.testall.ffmpeg;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yujunquan
 * @Description 描述
 * @createTime 2021-03-03 17:34:35
 */
public class FfmpegTest {
    static String ffmpegPath = " D:\\Programmer\\ffmpeg\\bin\\";
    static String outputPath = " D:\\tmp\\test\\";
    public static void main2(String[] args) throws IOException {

        String commond1 =ffmpegPath +
                "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.64:554/h264/ch1/sub/av_stream " +
                "-c copy " +
                "-y " +
                outputPath +
                "test1.flv";
        String concat = " & ";
        String commond2 =ffmpegPath +
                "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.164:554/h264/ch1/sub/av_stream " +
                "-c copy " +
                "-y " +
                outputPath +
                "test2.flv";
        String commond = commond1 + concat + commond2 ;
        Process process = Runtime.getRuntime().exec(commond);
        InputStreamReader is = new InputStreamReader(process.getErrorStream());
        BufferedReader br = new BufferedReader(is);
        String line = "";
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        process.destroy();
    }

    public static void main4(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        String commond1 =ffmpegPath +
                "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.64:554/h264/ch1/sub/av_stream " +
                "-c copy " +
                "-y " +
                outputPath +
                "test1.flv";
        String commond2 =ffmpegPath +
                "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.164:554/h264/ch1/sub/av_stream " +
                "-c copy " +
                "-y " +
                outputPath +
                "test2.flv";
        T3 t1 = new T3(commond1,"cmd1");
        T3 t2 = new T3(commond2,"cmd2");
        t1.start();
        t2.start();
    }


    static class Util {

        public static void exec(String cmd,String flag) throws IOException {

            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(process.getErrorStream());
            BufferedReader br = new BufferedReader(is);
            String line = "";
            while ((line = br.readLine())!=null){
                System.out.println(flag+"  === "+line);
            }
            process.destroy();

        }

    }

    static class T1 extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            String commond1 =ffmpegPath +
                    "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.64:554/h264/ch1/sub/av_stream " +
                    "-c copy " +
                    "-y " +
                    outputPath +
                    "test1.flv";
            Process process = Runtime.getRuntime().exec(commond1);
            InputStreamReader is = new InputStreamReader(process.getErrorStream());
            BufferedReader br = new BufferedReader(is);
            String line = "";
            while ((line = br.readLine())!=null){
                System.out.println("t1"+line);
            }
            process.destroy();
        }
    }

    static class T2 extends Thread{
        @SneakyThrows
        @Override
        public void run() {
            String commond1 =ffmpegPath +
                    "ffmpeg -re -rtsp_transport tcp -i rtsp://admin:sinorock@123@192.168.0.164:554/h264/ch1/sub/av_stream " +
                    "-c copy " +
                    "-y " +
                    outputPath +
                    "test2.flv";
            Process process = Runtime.getRuntime().exec(commond1);
            InputStreamReader is = new InputStreamReader(process.getErrorStream());
            BufferedReader br = new BufferedReader(is);
            String line = "";
            while ((line = br.readLine())!=null){
                System.out.println("t2"+line);
            }
            process.destroy();

        }
    }
    static class T3 extends Thread {
        String cmd ;
        String flag ;

        public T3(String cmd, String flag) {
            this.cmd = cmd;
            this.flag = flag;
        }

        @SneakyThrows
        @Override
        public void run() {
            Util.exec(cmd,flag);
        }
    }
}
