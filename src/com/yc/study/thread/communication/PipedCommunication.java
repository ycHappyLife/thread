package com.yc.study.thread.communication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedCommunication {

    private static class Write implements Runnable{

        private PipedWriter pipedWriter;

        public Write(PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = System.in.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + "写入：" + (char)receive);
                    pipedWriter.write(receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    System.in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Print implements Runnable {

        private PipedReader pipedReader;

        public Print(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + "读：" + (char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        out.connect(in);
        Thread printThread = new Thread(new Print(in), "readThread");
        printThread.start();

        Thread writeThread = new Thread(new Write(out), "writeThread");
        writeThread.start();


    }
}
