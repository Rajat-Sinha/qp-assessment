package com.grocery.mgmt.platform.app.service;

import com.grocery.mgmt.platform.common.representation.Response;
import com.grocery.mgmt.platform.common.representation.request.OrderRequest;

import java.util.UUID;

public interface IOrderService {

    /**
     * Place Order
     * @param orderRequest
     * @return
     */
    Response placeOrder(OrderRequest orderRequest);

    /**
     * Get User Order List
     * @param userId
     * @return
     */
    Response getUserOrderList(UUID userId);

    /**
     * Get Available Grocery Items
     * @return
     * @param offset
     * @param limit
     */
    Response getAvailableGroceryItems(Integer offset, Integer limit);
}
