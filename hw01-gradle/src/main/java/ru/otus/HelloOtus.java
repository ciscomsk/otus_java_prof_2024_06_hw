package ru.otus;

import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> names = List.of("John", "Jane", "Adam", "Tom", "Viki", "Tyler");
        List<List<String>> result = Lists.partition(names, 2);
        System.out.println(result);
    }
}
