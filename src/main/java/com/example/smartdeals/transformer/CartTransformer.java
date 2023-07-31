package com.example.smartdeals.transformer;

import com.example.smartdeals.dto.response.CartResponseDto;
import com.example.smartdeals.dto.response.ItemResponseDto;
import com.example.smartdeals.model.Cart;
import com.example.smartdeals.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartReponseDto(Cart cart) {

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item item : cart.getItems()) {
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}