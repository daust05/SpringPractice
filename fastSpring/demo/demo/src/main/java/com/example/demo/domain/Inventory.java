package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    Long money;
    Integer size;
}
