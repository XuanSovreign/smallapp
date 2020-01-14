package com.space.testfuction;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
        ExecutorService service = Executors.newSingleThreadExecutor();
        Task task = new Task("aaaaa");
        service.execute(task);
        Thread.sleep(1000);
        task.setInfo("bbbbbbbbb");
    }


    public class Task implements Runnable {
        private String info;

        public Task(String info) {
            this.info = info;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(info);
            }
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
