package com.example.smartdeals.transformer;

import com.example.smartdeals.dto.request.SellerRequestDto;
import com.example.smartdeals.dto.response.SellerResponseDto;
import com.example.smartdeals.model.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .pan(sellerRequestDto.getPan())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){

        return  SellerResponseDto.builder()
                .email(seller.getEmail())
                .name(seller.getName())
                .build();
    }
}
