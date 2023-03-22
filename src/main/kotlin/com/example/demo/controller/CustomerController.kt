package com.example.demo.controller

import com.example.demo.model.Customer
import com.example.demo.model.CustomerParams
import com.example.demo.service.CustomerService
import io.swagger.annotations.Api
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Validated
@Api(tags = ["Customer Controller"])
class CustomerController(
    private val service: CustomerService
) {
    @PostMapping("/costumer")
    fun createCustomer(@Valid @RequestBody customer: Customer): Customer {
        return service.createCostumer(customer)
    }

    @GetMapping("/costumer")
    fun getCustomer(
        @ModelAttribute params: CustomerParams
    ): Customer {
        return service.getCustomer(params)
    }
}