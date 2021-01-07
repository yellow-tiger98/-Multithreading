package com.hd.practice.outofmoney;

/**
 * @ClassName TestMain
 * @Description
 * @Author huangda
 * @Date 2021/1/7 11:13 上午
 * @Version 1.0
 **/
public class TestMain {

    public static void main(String[] args) {
        Bank bank = new Bank();

        PersonA personA = new PersonA(bank,"counter");
        PersonB personB = new PersonB(bank, "atm");

        personB.start();
        personA.start();
    }
}
