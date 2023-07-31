package com.example.smartdeals.dto.response;

import com.example.smartdeals.Enum.ProductCategory;
import com.example.smartdeals.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {


    String sellerName;

    String productName;

    ProductCategory category;

    int price;

    int availableQuantity;

    ProductStatus productStatus;

    int productId;

}
