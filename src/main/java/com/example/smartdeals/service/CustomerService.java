package com.example.smartdeals.service;

import com.example.smartdeals.dto.request.CustomerRequestDto;
import com.example.smartdeals.dto.response.CustomerResponseDto;
import com.example.smartdeals.model.Cart;
import com.example.smartdeals.model.Customer;
import com.example.smartdeals.repository.CustomerRepository;
import com.example.smartdeals.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
  CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        // dto -> entity

//        Customer customer = new Customer();
//        customer.setName(customerRequestDto.getName());
//        customer.setGender(customerRequestDto.getGender());

        // dto -. entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer =customerRepository.save(customer);   // saves both customer and cart;

        // prepare the response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }
}