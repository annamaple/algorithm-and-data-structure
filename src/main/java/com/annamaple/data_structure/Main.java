package com.annamaple.data_structure;

public class Main {

    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(5);
        skipList.insert(6);
        skipList.insert(0);
        skipList.printAll();
    }
}
