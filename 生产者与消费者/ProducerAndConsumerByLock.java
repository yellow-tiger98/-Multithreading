package com.hd.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProducerAndConsumerByLock
 * @Description 使用可重入锁实现生产者与消费者demo
 * @Author huangda
 * @Date 2021/1/6 3:11 下午
 * @Version 1.0
 **/
public class ProducerAndConsumerByLock {
    // 仓库最大存储容量
    private static final int CAPACITY = 5;

    // 创建可重入锁
    private static final Lock lock = new ReentrantLock();
    // 仓库满的情况
    private static final Condition fullCondition = lock.newCondition();
    // 仓库空的情况
    private static final Condition emptyCondition = lock.newCondition();


    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

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


    /**
     * @description 生产者 继承Thread方式实现
     */
    public static class Producer extends Thread{
        private Queue<String> queue;
        String name;
        int maxSize;
        int size = 0;

        public Producer(String name, Queue<String> queue, int maxSize){
            this.name = name;
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                // 获得锁
                lock.lock();
                while (maxSize == queue.size()){
                    try {
                        System.out.println("仓库已满，暂停生产，等待消费者消费....");
                        // 仓库满仓，停止生产，进入阻塞状态
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                size++;
                String product = name+"-"+size;
                System.out.println(name+"生产了商品:"+product);
                queue.offer(product);

                // 唤醒其他消费者、生产者
                fullCondition.signalAll();
                emptyCondition.signalAll();

                // 释放锁
                lock.unlock();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @description 消费者 继承Thread方式实现
     */
    public static class Consumer extends Thread{
        private Queue<String> queue;
        String name;
        int maxSize;

        public Consumer(String name, Queue<String> queue, int maxSize){
            this.queue = queue;
            this.name = name;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                lock.lock();
                while (queue.isEmpty()){
                    try {
                        System.out.println("仓库为空，等待生产.......");
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String product = queue.poll();
                System.out.println(name+"消费了商品:"+product);

                fullCondition.signalAll();
                emptyCondition.signalAll();

                lock.unlock();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
