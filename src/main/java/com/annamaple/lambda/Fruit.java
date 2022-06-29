package com.annamaple.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;


/**
 * @author xionglei
 * @create 2022-06-29 15:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Fruit {

    private String name;
    private Double price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(name, fruit.name) && Objects.equals(price, fruit.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
