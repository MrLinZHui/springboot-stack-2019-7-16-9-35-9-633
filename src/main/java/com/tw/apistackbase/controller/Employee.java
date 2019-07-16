package com.tw.apistackbase.controller;

public class Employee {

    private int age;
    private int id;

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private String gender;
    public Employee(int id, String name, int age, String gender) {
        this.id = id;
        this.name =name;
        this.age = age;
        this.gender = gender;
    }

    public Employee() {
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
