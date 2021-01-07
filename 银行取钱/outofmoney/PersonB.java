package com.hd.practice.outofmoney;

/**
 * @ClassName PersonB
 * @Description
 * @Author huangda
 * @Date 2021/1/7 10:41 上午
 * @Version 1.0
 **/
public class PersonB extends Thread {

    Bank bank;

    String mode;

    public PersonB( Bank bank, String mode){
        this.bank = bank;
        this.mode = mode;
    }

    @Override
    public void run() {
        super.run();
        while (bank.testAccount >= 200){
            try {
                bank.outOfMoney(200, mode);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
