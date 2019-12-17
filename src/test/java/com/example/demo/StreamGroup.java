package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroup {

    public static void main(String[] args) {
        List<Student> list = getStudents();
        Map<String,List<Student>> userGroupMap = list.stream().collect(Collectors.groupingBy(Student::getType));
        System.out.println(userGroupMap);
        userGroupMap.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.toString());
        });

    }
    public static List<Student> getStudents(){
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("1", "小学", "小明1"));
        list.add(new Student("2", "小学", "小明2"));
        list.add(new Student("3", "初中", "小明3"));
        list.add(new Student("4", "初中", "小明4"));
        list.add(new Student("5", "高中", "小明5"));
        return list;
    }
}
class Student {
    private String id;
    private String type;
    private String name;

    public Student() {
    }

    public Student(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
