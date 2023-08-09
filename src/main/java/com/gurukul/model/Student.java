package com.gurukul.model;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private int age;

    public Student() {
    }

    @Override
    public String toString() {
	return "Student [id=" + id + ", name=" + name + ", email=" + email + ", course=" + course + ", age=" + age
		+ "]";
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getCourse() {
	return course;
    }

    public void setCourse(String course) {
	this.course = course;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public Student(String name, String email, String course, int age) {
	super();
	// this.id = id;
	this.name = name;
	this.email = email;
	this.course = course;
	this.age = age;
    }

}
