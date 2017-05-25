package com.example.a15041867.taskmanager;

import java.io.Serializable;

/**
 * Created by 15041867 on 25/5/2017.
 */

public class Task implements Serializable {
    public int id;
    public String name;
    public String Description;

    public Task(int id,String name, String description) {
        this.id = id;
        this.name = name;
        Description = description;
    }

    public Task(String name, String description) {

        this.name = name;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id ) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return id + name + "\n" + Description;
    }
}
