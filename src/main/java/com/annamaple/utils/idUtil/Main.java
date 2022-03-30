package com.annamaple.utils.idUtil;

/**
 * @author xionglei
 * @create 2021-11-15 14:09
 */
public class Main {

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(5, 5, 12);
        System.out.println(idWorker.getWorkerId());
        System.out.println(idWorker.nextId());
    }
}
