package com.hd.practice.outofmoney;

/**
 * @ClassName Bank
 * @Description
 * @Author huangda
 * @Date 2021/1/7 10:40 上午
 * @Version 1.0
 **/
public class Bank {

    // 假设一个账户有500元
    static double testAccount = 1000;

    /**
     * @Description 柜台取钱
     * @Param money
     * @return void
     * @Author huangda
     * @Date 2021/1/7 10:46 上午
     **/
    private void counter(double money){
        Bank.testAccount -= money;
        System.out.println("从柜台取出"+money+"元，账户余额："+testAccount+"元");
    }

    /**
     * @Description  ATM取钱
     * @Param money
     * @return void
     * @Author huangda
     * @Date 2021/1/7 10:47 上午
     **/
    private void atm(double money){
        Bank.testAccount -= money;
        System.out.println("从ATM取出"+money+"元，账户余额："+testAccount+"元");
    }

    /**
     * @Description 公共取钱方法（使用synchronized保证金额同步）
     * @Param money
     * @Param mode
     * @return void
     * @Author huangda
     * @Date 2021/1/7 10:49 上午
     **/
    public synchronized void outOfMoney(double money, String mode) throws Exception {
        if (money > Bank.testAccount){
            throw new Exception("账户余额不足！");
        }
        if ("conter".equals(mode)){
            counter(money);
        }
        if ("atm".equals(mode)){
            atm(money);
        }
    }
}
