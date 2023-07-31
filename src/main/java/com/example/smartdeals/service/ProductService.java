package com.example.smartdeals.service;

import com.example.smartdeals.Enum.ProductCategory;
import com.example.smartdeals.dto.request.ProductRequestDto;
import com.example.smartdeals.dto.response.ProductResponseDto;
import com.example.smartdeals.exception.SellerNotFoundException;
import com.example.smartdeals.model.Product;
import com.example.smartdeals.model.Seller;
import com.example.smartdeals.repository.CustomerRepository;
import com.example.smartdeals.repository.ProductRepository;
import com.example.smartdeals.repository.SellerRepository;
import com.example.smartdeals.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepository.findByEmail(productRequestDto.getSellerEmail());
        if (seller == null) {
            throw new SellerNotFoundException("Seller doesn't exist");
        }

        // dto -> entity
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller); // save both product and seller
        List<Product> productList = savedSeller.getProducts();
        Product latestProduct = productList.get(productList.size() - 1);

        // prepare response dto
        return ProductTransformer.ProductToProductResponseDto(latestProduct);
    }

    public List<ProductResponseDto> getProdByCategoryAndPriceGreaterThan(int price,
                                                                         ProductCategory category) {

        List<Product> products = productRepository.getProdByCategoryAndPriceGreaterThan(price, category);

        // prepare list of response dtos
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }
}
