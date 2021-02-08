package com.knoldus.user;

public class User {

    int id;
    String name;
    int age;
    String course;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                '}';
    }

    public int getId() {

        return id;
    }

    public int getAge() {

        return age;
    }

    public String getName() {

        return name;
    }

    public String getCourse() {

        return course;
    }

    public User() {

    }

    public User(int id,String name,int age, String course) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.course = course;
    }


}
