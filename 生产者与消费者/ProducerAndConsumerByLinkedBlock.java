package com.hd.practice;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName ProducerAndConsumerByBlock
 * @Description 使用阻塞队列实现 生产者与消费者demo
 * @Author huangda
 * @Date 2021/1/6 4:59 下午
 * @Version 1.0
 **/
public class ProducerAndConsumerByLinkedBlock {
    private static final int CAPACITY = 5;


    public static void main(String[] args) {
        // LinkedBlockingQueue 为阻塞队列BlockingQueue的实现类，具有同步的特性，另一个实现类则是ArrayBlockingQueue
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(CAPACITY);

        Thread product1 = new Thread(new Producer("p1", linkedBlockingQueue, CAPACITY));
        Thread product2 = new Thread(new Producer("p2", linkedBlockingQueue, CAPACITY));
        Thread product3 = new Thread(new Producer("p3", linkedBlockingQueue, CAPACITY));
        Thread consumer1 = new Thread(new Consumer("c1", linkedBlockingQueue, CAPACITY));
        Thread consumer2 = new Thread(new Consumer("c2", linkedBlockingQueue, CAPACITY));

        product1.start();
        product2.start();
        product3.start();

        consumer1.start();
        consumer2.start();
    }

    public static class Producer implements Runnable{
        private LinkedBlockingQueue<String> linkedBlockingQueue;
        String name;
        int maxSize;
        int size;

        public Producer(String name, LinkedBlockingQueue<String> linkedBlockingQueue, int maxSize){
            this.name = name;
            this.linkedBlockingQueue = linkedBlockingQueue;
            this.maxSize = maxSize;
        }


        @Override
        public void run() {
            while (true){
                try {
                    size++;
                    String product = name+"-"+size;
                    System.out.println(name+"生产了"+product);
                    linkedBlockingQueue.put(product);
                    // 暂停最多1s
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable{
        private LinkedBlockingQueue<String> linkedBlockingQueue;
        String name;
        int maxSize;

        public Consumer(String name, LinkedBlockingQueue<String> linkedBlockingQueue, int maxSize){
            this.name = name;
            this.linkedBlockingQueue = linkedBlockingQueue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                try {
                    String product = linkedBlockingQueue.take();
                    System.out.println(name+"消费了"+product);

                    // 暂停最多1s
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


