package com.hd.practice;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName ProductAndConsumerByArrayBlock
 * @Description
 * @Author huangda
 * @Date 2021/1/6 9:07 下午
 * @Version 1.0
 **/
public class ProductAndConsumerByArrayBlock {

    private static final int CAPACITY = 5;

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(CAPACITY);
        Thread producer1 = new Producer("p1", queue, CAPACITY);
        Thread producer2 = new Producer("p2", queue, CAPACITY);
        Thread producer3 = new Producer("p3", queue, CAPACITY);

        Thread consumer1 = new Consumer("c1", queue, CAPACITY);
        Thread consumer2 = new Consumer("c2", queue, CAPACITY);

        producer1.start();
        producer2.start();
        producer3.start();

        consumer1.start();
        consumer2.start();
    }

    public static class Producer extends Thread{
        private ArrayBlockingQueue<String> queue;
        String name;
        int maxSize;
        int size = 0;

        public Producer(String name, ArrayBlockingQueue<String> queue, int maxSize){
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                try {
                    size++;
                    String product = name+"-"+size;
                    queue.put(product);
                    System.out.println(name+"生产了商品"+product);

                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static class Consumer extends Thread{
        private ArrayBlockingQueue<String> queue;
        String name;
        int maxSize;

        public Consumer(String name, ArrayBlockingQueue<String> queue, int maxSize){
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                try {
                    String product = queue.take();
                    System.out.println(name+"消费了商品"+product);

                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
