package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    private long id;
    private long employeeId;
    private String description;

    public Review(long id, long employeeId, String description) {
        this.id = id;
        this.employeeId = employeeId;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "employee_id", nullable = false)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
