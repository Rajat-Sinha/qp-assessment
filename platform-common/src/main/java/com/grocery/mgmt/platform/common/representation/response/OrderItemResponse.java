package com.grocery.mgmt.platform.common.representation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private UUID itemId;
    private String itemName;
    private Integer quantity;
    private Float price;

}
