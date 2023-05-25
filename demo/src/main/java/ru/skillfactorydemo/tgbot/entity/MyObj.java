package ru.skillfactorydemo.tgbot.entity;

import javax.persistence.*;

@Entity
@Table(name = "MyObj")
public class MyObj {

    @Id
    private Long id;

    @Column(name = "I")
    private int i;
    @Column(name = "HELLO")
    private String hello;


    public MyObj(Long id, int i, String hello) {
        this.id = id;
        this.i = i;
        this.hello = hello;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyObj() {
    }
}
