package com.grocery.mgmt.platform.common.representation.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private UUID itemId;
    private Integer quantity;

}
