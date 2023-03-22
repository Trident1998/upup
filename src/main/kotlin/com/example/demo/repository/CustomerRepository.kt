package com.example.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.demo.model.Customer
import org.springframework.data.jpa.repository.Query

interface CustomerRepository: JpaRepository<Customer, Long> {
    @Query(
        """select c from Customer c
            where c.phoneNumber = :phoneNumber or
            c.email = :email
        """
    )
    fun findByParams(phoneNumber: String?, email: String?, ): Customer
}