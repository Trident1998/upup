package com.example.demo.service

import com.example.demo.excertion.ValidException
import com.example.demo.model.CustomerParams
import com.example.demo.model.Customer
import com.example.demo.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun createCostumer(customer: Customer) = repository.save(customer.isValid())

    fun getCustomer(params: CustomerParams): Customer {
        return repository.findByParams("+${params.phoneNumber}", params.email)
    }
}

private fun Customer.isValid(): Customer {
    if (this.email.isBlank() || !this.email.matches(EMAIL_REGEX)) {
        throw ValidException("The provided email is not valid")
    }
    if (this.phoneNumber.isBlank() || !this.phoneNumber.matches(PHONE_NUMBER_REGEX)) {
        throw ValidException("The provided phone number is not valid")
    }
    return this
}

private val EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$".toRegex()
private val PHONE_NUMBER_REGEX = "^\\+\\d{12}$".toRegex()
