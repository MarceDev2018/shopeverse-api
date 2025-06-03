package com.technova.shopeverse.model;

import jakarta.persistence.*;

@Entity

@Table(name = "client")

public class Customer {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;



    @Column(name = "full_name", length = 100, nullable = false)

    private String name;

    private String email;



    @Transient

    private String temporaryToken;

}