package com.grocery.mgmt.platform.common.representation.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private UUID userId;
    private List<OrderItemRequest> items;

}
