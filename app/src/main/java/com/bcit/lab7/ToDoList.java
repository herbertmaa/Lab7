package com.bcit.lab7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoList {

    public String task;
    public String who;
    public Date date;
    public boolean done;

    public ToDoList(String task, String who, Date date, boolean done){
        this.task = task;
        this.who = who;
        this.date = date;
        this.done = done;
    }


    private ToDoList(){}

    public String getTask() {
        return task;
    }

    public String getWho() {
        return who;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDone() {
        return done;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(task);
        sb.append(" " + who + "\n");
        sb.append(" " + df.format(date) + " ");


        if(done){
            sb.append(" DONE " );
        }else{
            sb.append( " NOT DONE ");
        }

        return sb.toString();
    }
}
