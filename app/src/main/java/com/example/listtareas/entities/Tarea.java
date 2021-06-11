package com.example.listtareas.entities;

public class Tarea {

    public String id;
    public String assigned;
    public String date;
    public int isDone;
    public String description;

    public Tarea(String date, String description) {
        this.date = date;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

