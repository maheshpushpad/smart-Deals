package com.example.smartdeals.service;

import com.example.smartdeals.dto.request.SellerRequestDto;
import com.example.smartdeals.dto.response.SellerResponseDto;
import com.example.smartdeals.model.Seller;
import com.example.smartdeals.repository.SellerRepository;
import com.example.smartdeals.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // dto to entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        // save the entity

        Seller savedSeller = sellerRepository.save(seller);

        // prepare response Dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
