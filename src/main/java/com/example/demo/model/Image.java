package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    private long id;
    private String name;
    private String url;

    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
//    private Introduction introduction;
//
//    public Introduction getIntroduction() {
//        return introduction;
//    }

}
