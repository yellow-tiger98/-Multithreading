package com.hd.practice.tortoiseandrabbit;

/**
 * @ClassName Animal
 * @Description
 * @Author huangda
 * @Date 2021/1/7 1:19 下午
 * @Version 1.0
 **/
public abstract class Animal extends Thread {

    // 赛道长度
    public int length = 2000;

    boolean isStop;

    public abstract void running();

    @Override
    public void run() {
        super.run();
        while (length > 0){
            running();
        }
    }

    // 用于动物完成比赛后 叫停比赛
    public static interface Calltoback{
        public void win();
    }

    // 创建回调接口对象
    public Calltoback calltoback;
}
