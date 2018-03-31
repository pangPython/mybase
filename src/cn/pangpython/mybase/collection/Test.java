package cn.pangpython.mybase.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("pangPython",10);
        Person p2 = new Person("pangPython",20);
        Person p3 = new Person("pangPython",10);
        Person p4 = new Person("JackMa",10);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        Set<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        for (Person p:list){
            System.out.println(p);
        }

        System.out.println("=====================");

        for (Person p:set){
            System.out.println(p);
        }

    }
}
