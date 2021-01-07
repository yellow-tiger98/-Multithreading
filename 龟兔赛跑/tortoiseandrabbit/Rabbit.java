package com.hd.practice.tortoiseandrabbit;

/**
 * @ClassName Rabbit
 * @Description
 * @Author huangda
 * @Date 2021/1/7 1:30 下午
 * @Version 1.0
 **/
public class Rabbit extends Animal{

    public Rabbit(){
        isStop = false;
        setName("兔子");
    }

    @Override
    public void running() {
            // 兔子速度
            int dis = 5;

            while (!isStop){
                length -= dis;

                System.out.println("兔子跑了"+dis+"米,距离终点还有"+length+"米");
                if (length <=0){
                    length = 0;
                    isStop = true;
                    System.out.println("兔子获得了胜利");
                    if (calltoback != null){
                        calltoback.win();
                    }
                }

                try {
                    // 兔子每跑20米休息 1s 再继续跑
                    if ((2000-length) % 20 == 0){
                        sleep(1000);
                    }else{
                        // 0.1秒跑5m
                        sleep(100);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }


    }



}
