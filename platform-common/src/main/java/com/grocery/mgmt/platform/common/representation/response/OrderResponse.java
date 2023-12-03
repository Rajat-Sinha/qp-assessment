package com.grocery.mgmt.platform.common.representation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID orderId;
    private Float totalAmount;
    private Timestamp orderPlacedOn;
    private List<OrderItemResponse> items;

}
