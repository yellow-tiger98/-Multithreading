package com.hd.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @ClassName ProducerAndConsumer
 * @Description 使用wait()和notifyAll() 实现生产者与消费者demo
 * @Author huangda
 * @Date 2021/1/6 1:46 下午
 * @Version 1.0
 **/
public class ProducerAndConsumer {
 private static final int CAPACITY = 5;

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        Thread producer1 = new Thread(new Producer("p1", queue, CAPACITY));
        Thread producer2 = new Thread(new Producer("p2", queue, CAPACITY));
        Thread producer3 = new Thread(new Producer("p3", queue, CAPACITY));
        Thread consumer1 = new Thread(new Counsumer("c1", queue, CAPACITY));
        Thread consumer2 = new Thread(new Counsumer("c2", queue, CAPACITY));
        Thread consumer3 = new Thread(new Counsumer("c3", queue, CAPACITY));

        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

    }

}

/**
 * @description 生产者
 */
class Producer implements Runnable{
    //线程名称
    String name;
    // 商品仓库
    private Queue<Integer> queue;
    // 仓库最大容量
    int maxSize;
    // 初始化生产数量
    int size = 0;

    public Producer(String name , Queue<Integer> queue, int maxSize){
        this.name = name;
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            // 同步代码块
            synchronized (queue){
                while(maxSize == queue.size()){
                    try{
                        System.out.println("仓库已满，暂停生产，等待被消费者消费......");
                        // 进入等待队列
                        queue.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                size++;
                System.out.println(name+"生产"+size+"个产品......");
                queue.offer(size);
                queue.notifyAll();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

/**
 * @description 消费者
 */
class Counsumer implements Runnable{
    private Queue<Integer> queue;
    String name;
    int maxSize;

    public Counsumer(String name, Queue<Integer> queue, int maxSize){
        this.name = name;
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while(queue.isEmpty()){
                    try {
                        System.out.println("仓库为空，停止消费，等待生产.....");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 从仓库中取出商品
                int x = queue.poll();
                System.out.println(name+"消费了"+x);
                // 唤醒其他生产者 或者 消费者
                queue.notifyAll();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
