package com.leonid.crudtask.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Size(min = 1, max = 25, message = "Name should have {min} - {max} characters")
    @Pattern(regexp = "[A-Za-zА-Яа-я]*", message = "Name:Only letters allowed")
    private String name;
    @Max(value = 150, message = "Please check once more. Your age can't be more than 150")
    @Min(value = 0, message = "Age should be positive")
    private int age;
    private boolean isAdmin;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "Date should be in Past, cannot be updated")
    private Date createdDate = new Date();

    public User(){
    }

    public User(String name, int age, boolean isAdmin, int id, Date createdDate) {
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.createdDate = createdDate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
