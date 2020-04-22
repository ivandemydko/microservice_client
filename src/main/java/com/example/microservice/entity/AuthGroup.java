package com.example.microservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH_USER_GROUP")
public class AuthGroup {


    @Id
    @Column(name = "AUTH_USER_GROUP_ID")
    private long id;
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "AUTH_GROUP")
    private String authGroup;


    public AuthGroup(Long userId, String authGroup) {
        this.userId = userId;
        this.authGroup = authGroup;
    }


    public AuthGroup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

}
