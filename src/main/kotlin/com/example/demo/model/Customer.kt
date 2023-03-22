package com.example.demo.model

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") val id: Long = 0,
    @Column(name = "first_name") val firstName: String,
    @Column(name = "last_name") val lastName: String,
    @Column(name = "phone_number", unique = true) val phoneNumber: String,
    @Column(name = "email", unique = true) val email: String,
    @Column(name = "app") val app: String,
    @Column(name = "date") val date: Instant = Instant.now()
) {
    constructor() : this( 0,"", "",  "", "", "")
}