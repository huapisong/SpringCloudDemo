package com.example.demo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Test {
    /**
     * Java 8 允许我们使用default关键字，为接口声明添加非抽象的方法实现。这个特性又被称为扩展方法。
     */
    interface Formula{
        double calculate(int a);
        default double sqrt(int a){
            return Math.sqrt(a);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
       Stream<String> aa =  list.stream().filter(item ->
               "aa".equals(item)
        );
        for (Object o : aa.toArray()) {
            System.out.println(o);
        }
        Stream.of(list.toArray()).forEach(System.out::println);

        int[] a = {4,5,6,7,1,2,3};
        Arrays.stream(a).sorted().forEach(System.out::println);
        Integer var = 1111;
        Integer varc = var; // 1111;

        System.out.println(var == varc);
        System.out.println(var.equals(varc));
        //
//        Formula formula = new Formula(){
//            @Override
//            public double calculate(int a) {
//                return sqrt(a * 100);
//            }
//        };
//        double a = formula.calculate(100);
//        double b = formula.sqrt(16);
//        System.out.println(a); // 100.0
//        System.out.println(b); // 4.0

        /**
         * Lambda表达式
         */
        /**排序
        * */
//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        /*直接用创建匿名Comparator对象，并把它作为参数传递给sort方法*/
//        Collections.sort(names, new Comparator<String>(){
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });
//        System.out.println(names.toString());
//        /*除了创建匿名对象以外，Java 8 还提供了一种更简洁的方式，Lambda表达式*/
//        Collections.sort(names, (String a, String b) -> { return a.compareTo(b); });
//        /*这段代码就比之前的更加简短和易读。但是，它还可以更加简短*/
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//        /*只要一行代码，包含了方法体。你甚至可以连大括号对{}和return关键字都省略不要。不过这还不是最短的写法*/
//        Collections.sort(names, (a,b) -> b.compareTo(a));
        /**
         * 函数式接口
         */
//        Converter<String, Integer> converter = (from -> Integer.valueOf(from));
//        Integer converted = converter.convert("123"); // 123

//        Converter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123"); // 123
//        System.out.println(converted);

        /**
         * 方法和构造函数引用
         * */
//        Something something = new Something();
//        Converter<String, String> converter = something::startsWith;
//        String converted = converter.convert("Java");
//        System.out.println(converted); // J

//        PersonFactory<Person> personFactory = Person::new;
//        Person person = personFactory.create("Pater", "Parker");
//        System.out.println(person.toString());
        // 数据流如何工作
//        List<String> list = Arrays.asList("a1", "a2", "a3", "b1", "b2", "c1", "c2");
//        list
//                .stream()
//                .filter(s -> s.startsWith("c"))
//                .map(String::toUpperCase)
//                .sorted()
//                .forEach(System.out::println); // c1 c2
//        // 数据流的不同类型
//        // 在对象列表上调用stream()方法会返回一个通常的对象流
//        Arrays.asList("a1", "a2", "a3")
//                .stream()
//                .findFirst()
//                .ifPresent(System.out::println); // a1
        // 只要使用Stream.of()，就可以从一系列对象引用中创建数据流。
//        Stream.of("a1", "a2", "a3")
//                .findFirst()
//                .ifPresent(System.out::println); // a1
//        IntStream.range(1, 4)
//                .forEach(System.out::println); // 1 // 2 // 3
//
//        Arrays.stream(new int[] {1, 2, 3})
//                .map(n -> 2 * n + 1)
//                .average()
//                .ifPresent(System.out::println); // 5.0
//        Stream.of("a1", "a2", "a3")
//                .map(s -> s.substring(1))
//                .mapToInt(Integer::parseInt)
//                .max()
//                .ifPresent(System.out::println); // 3
//        IntStream.range(1, 4)
//                .mapToObj(i -> "a" + i)
//                .forEach(System.out::println); // a1 //a 2 // a3
//        Stream.of(1.0, 2.0, 3.0)
//                .mapToInt(Double::intValue)
//                .mapToObj(i -> "a" + i)
//                .forEach(System.out::println); // a1 //a 2 // a3

//        Stream.of("d2", "a2", "b1", "b3", "c")
//                .filter(s -> {
//                    System.out.println("filter:" + s);
//                    return true;
//                })
//                .forEach(s -> System.out.println("forEach:" + s));

//        Stream.of("a2", "a2", "b1", "b3", "c")
//                .map(s -> {
//                    System.out.println("map:" + s);
//                    return s.toUpperCase();
//                })
//                .anyMatch(s -> {
//                    System.out.println("anyMatch:" + s);
//                    return s.startsWith("A");
//                });
//        map:a2
//        anyMatch:A2

        /**
         * 复用数据流
         */
      /*  Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);*/
    }

    /**
     * 函数式接口
     */
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    /**
     * 方法和构造函数引用
     * */
    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }
    static class Person {
        String firstName;
        String lastName;
        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }



}
