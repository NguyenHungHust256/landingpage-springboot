package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "logo")
public class Logo {
    private long id;
    private String text;

    public Logo() {
    }

    public Logo(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
