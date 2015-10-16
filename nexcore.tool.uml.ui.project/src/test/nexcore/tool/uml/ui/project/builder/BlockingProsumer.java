package nexcore.tool.uml.ui.project.builder;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingProsumer {
    /**
     * queue
     */
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    /**
     * main
     *  
     * @param args void
     */
    public static void main(String[] args) {
        BlockingProsumer b = new BlockingProsumer();
        // 소비자 3명
        Consumer c1 = new Consumer("1", b.queue);
        c1.start();
        Consumer c2 = new Consumer("2", b.queue);
        c2.start();
        Consumer c3 = new Consumer("3", b.queue);
        c3.start();
        // 생산자 1명
        Producer p1 = new Producer(b.queue);
        p1.start();
    }

    /**
     * 생산자 스레드
     */
    private static class Producer extends Thread {
        private volatile static int INDEX = 1; // INDEX

        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(500));
                    // 임의의 시간마다 데이터를 넣어 줌 데이터를 넣고 나면 알아서 notify시켜 줌
                    queue.put(INDEX++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 소비자 클래스
     */
    private static class Consumer extends Thread {
        private BlockingQueue<Integer> queue;

        private String name;

        public Consumer(String name, BlockingQueue<Integer> queue) {
            this.name = name;
            this.queue = queue;
        }

        public void run() {
            while (true) {
                try {
                    // queue에 data가 없으면 알아서 wait하고 있음
                    Integer index = queue.take();
                    System.out.println("Consumer[" + name + "]\tIndex : " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
