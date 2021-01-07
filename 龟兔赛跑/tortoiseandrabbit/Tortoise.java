package com.hd.practice.tortoiseandrabbit;

/**
 * @ClassName Tortoise
 * @Description
 * @Author huangda
 * @Date 2021/1/7 1:31 下午
 * @Version 1.0
 **/
public class Tortoise extends Animal{

    public Tortoise(){
        setName("乌龟");
        isStop = false;
    }

    @Override
    public void running() {
        // 乌龟速度
        int dis = 2;

        while (!isStop){
            length -= dis;
            System.out.println("乌龟跑了"+dis+"米，距离终点还有"+length+"米");
            if (length <= 0){
                length = 0;
                isStop = true;
                System.out.println("乌龟获得了胜利");

                // 让兔子不要再跑了
                if (calltoback != null){
                    calltoback.win();
                }
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
