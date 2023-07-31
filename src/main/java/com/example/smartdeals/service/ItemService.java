package com.example.smartdeals.service;

import com.example.smartdeals.dto.request.ItemRequestDto;
import com.example.smartdeals.exception.CustomerNotFoundException;
import com.example.smartdeals.exception.InsufficientQuantityException;
import com.example.smartdeals.exception.ProductNotFoundException;
import com.example.smartdeals.model.Customer;
import com.example.smartdeals.model.Item;
import com.example.smartdeals.model.Product;
import com.example.smartdeals.repository.CustomerRepository;
import com.example.smartdeals.repository.ProductRepository;
import com.example.smartdeals.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Product product = productOptional.get();

        // check for required quantity
        if(product.getAvailableQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! Required quantity not avaiable");
        }

        // create item
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
