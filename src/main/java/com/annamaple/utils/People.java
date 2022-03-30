package com.annamaple.utils;

public class People implements Comparable<People> {

    private String name;
    private Integer likability;

    public People(String name, Integer likability) {
        this.name = name;
        this.likability = likability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikability() {
        return likability;
    }

    public void setLikability(Integer likability) {
        this.likability = likability;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", likability=" + likability +
                '}';
    }

    @Override
    public int compareTo(People o) {
        if (o == null) throw new IllegalStateException("比较对象不能为null");
        return this.getLikability() - o.getLikability();
    }
}
