package com.gingdev.services.domain;

public class Student {
    private Integer id, age;
    private String name;

    public Student() {

    }

    public Student(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(final Integer id, final String name, final Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object[] toArray() {
        return new Object[] { id, name, age };
    }
}
