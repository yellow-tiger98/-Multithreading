package com.hd.practice.tortoiseandrabbit;

/**
 * @ClassName TestMain
 * @Description
 * @Author huangda
 * @Date 2021/1/7 1:45 下午
 * @Version 1.0
 **/
public class TestMain {

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();

        LetOneStop letRabbitStop = new LetOneStop(rabbit);

        Tortoise tortoise = new Tortoise();

        LetOneStop letTortoiseStop = new LetOneStop(tortoise);

        rabbit.calltoback = letTortoiseStop;

        tortoise.calltoback = letRabbitStop;

        rabbit.start();
        tortoise.start();
    }
}
