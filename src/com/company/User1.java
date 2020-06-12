package com.company;

/**
 * @description:
 * @Date: 2020-06-12 20:21
 * @author: liufeng
 **/
public class User1 {

    private int id;

    private String name;

    public User1(){

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

    public void sout(){
        System.out.println("**************user*************");
    }
}
