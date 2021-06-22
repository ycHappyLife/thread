package com.yc.study.concurrent.use;

import com.yc.study.concurrent.util.SleepUtils;

import java.io.*;

/**
 * @author ycrag
 */
public class PipeTest {

    public static void main(String[] args) {

        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        try {
            pipedWriter.connect(pipedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t1 = new Thread(new ReaderThread(pipedReader));
        Thread t2 = new Thread(new WriterThread(pipedWriter));
        t1.start();
        SleepUtils.second(1);
        t2.start();
    }

    static class ReaderThread implements Runnable {

        private PipedReader pipedReader;

        public ReaderThread(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {

        private PipedWriter pipedWriter;

        public WriterThread(PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
            try {
                pipedWriter.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
