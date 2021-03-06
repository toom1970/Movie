package com.ddd.movie.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private String salt;
    private Set<Role> roles;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.salt = name;
        this.password = new SimpleHash("MD5", password, salt, 2).toString();
    }
}
