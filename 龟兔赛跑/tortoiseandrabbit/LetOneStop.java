package com.hd.practice.tortoiseandrabbit;

/**
 * @ClassName LetOneStop
 * @Description
 * @Author huangda
 * @Date 2021/1/7 1:42 下午
 * @Version 1.0
 **/
public class LetOneStop implements Animal.Calltoback {

    Animal a;

    public LetOneStop(Animal a) {
        this.a = a;
    }

    @Override
    public void win() {
        a.isStop = true;
    }
}
