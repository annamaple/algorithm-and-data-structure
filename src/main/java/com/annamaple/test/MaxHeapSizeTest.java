package com.annamaple.test;

/**
 * @author xionglei
 * @create 2022-04-22 15:21
 */
public class MaxHeapSizeTest {

    // -Xms1042m -Xmx1042m
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long totalMem = rt.totalMemory();
        long maxMem = rt.maxMemory();
        long freeMem = rt.freeMemory();
        // 1024 * 1024
        double megs = 1 << 20;
        System.out.println(megs);
        System.out.println("Total Memory: " + totalMem + " (" + (totalMem / megs) + " MiB)");
        System.out.println("Max Memory:   " + maxMem + " (" + (maxMem / megs) + " MiB)");
        System.out.println("Free Memory:  " + freeMem + " (" + (freeMem / megs) + " MiB)");
    }
}
