package com.grocery.mgmt.platform.app.service.impl;

import com.grocery.mgmt.platform.app.repository.IGroceryItemRepository;
import com.grocery.mgmt.platform.app.service.IOrderService;
import com.grocery.mgmt.platform.common.entity.GroceryItem;
import com.grocery.mgmt.platform.common.entity.Order;
import com.grocery.mgmt.platform.common.entity.OrderItem;
import com.grocery.mgmt.platform.common.exception.DataNotFoundException;
import com.grocery.mgmt.platform.common.exception.ParameterMissingException;
import com.grocery.mgmt.platform.common.representation.ActionType;
import com.grocery.mgmt.platform.common.representation.Response;
import com.grocery.mgmt.platform.common.representation.request.OrderItemRequest;
import com.grocery.mgmt.platform.common.representation.request.OrderRequest;
import com.grocery.mgmt.platform.common.representation.response.GroceryItemResponse;
import com.grocery.mgmt.platform.common.representation.response.OrderItemResponse;
import com.grocery.mgmt.platform.common.representation.response.OrderResponse;
import com.grocery.mgmt.platform.common.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    IGroceryItemRepository iGroceryItemRepository;

    @Override
    public Response placeOrder(OrderRequest or) {

        var userId = or.getUserId();
        var items = or.getItems();

        if(ObjectUtils.isEmpty(userId)) {
            throw new ParameterMissingException("User Id is missing");
        }

        if(ObjectUtils.isEmpty(items)) {
            throw new ParameterMissingException("Items is missing");
        }

        var createdOn = Timestamp.valueOf(LocalDateTime.now());
        var createdBy = Constant.DEFAULT_USERID;
        var orderId = UUID.randomUUID();
        List<OrderItem> orderItems = new ArrayList<>();
        List<GroceryItem> groceryItemUpdates = new ArrayList<>();
        var itemIds = items.stream().map(OrderItemRequest::getItemId).toList();
        var groceryItems = iGroceryItemRepository.getGroceryList(null, null, itemIds, null);
        float totalAmount = 0;

        for(OrderItemRequest item : items) {
            var groceryItemO = groceryItems.stream().filter(i -> i.getId().equals(item.getItemId())).findFirst();
            if(groceryItemO.isEmpty()) {
                throw new DataNotFoundException(item.getItemId() + " not found");
            }
            var groceryItem = groceryItemO.get();
            if(item.getQuantity() > groceryItem.getQuantity()) {
                throw new DataNotFoundException("Insufficient quantity available for item " + groceryItem.getName());
            }
            groceryItem.setQuantity(groceryItem.getQuantity() - item.getQuantity());
            groceryItem.setUpdatedBy(createdBy);
            groceryItem.setUpdatedOn(createdOn);
            groceryItem.setLastActionType(ActionType.UPDATE.name());
            groceryItemUpdates.add(groceryItem);
            totalAmount = totalAmount + (item.getQuantity() * groceryItem.getPrice());
            orderItems.add(new OrderItem(UUID.randomUUID(), orderId, groceryItem.getId(), item.getQuantity(), groceryItem.getPrice(), createdOn, createdBy));
        };

        var orderItem = new Order(orderId, userId, createdOn, totalAmount, createdOn, createdBy);
        iGroceryItemRepository.saveGroceryItemOrder(orderItem, groceryItemUpdates, orderItems);

        return new Response(Constant.OK, "Order created successfully", false);
    }

    @Override
    public Response getUserOrderList(UUID userId) {

        if(ObjectUtils.isEmpty(userId)) {
            throw new ParameterMissingException("User Id is missing");
        }

        List<OrderResponse> responses = new ArrayList<>();

        var orders = iGroceryItemRepository.getOrderDataByUserId(userId);
        if(ObjectUtils.isNotEmpty(orders)) {

            var orderIds = orders.stream().map(Order::getId).toList();
            var orderItems = iGroceryItemRepository.getOrderItemsList(orderIds);
            var itemIds = orderItems.stream().map(OrderItem::getItemId).toList();
            var groceryItems = iGroceryItemRepository.getGroceryList(null, null, itemIds, true);
            responses = orders.stream().map(r -> {

                var items = orderItems.stream().filter(o -> o.getOrderId().equals(r.getId()))
                        .map(o -> {
                            var groceryItemO = groceryItems.stream().filter(i -> i.getId().equals(o.getItemId())).findFirst();
                            if(groceryItemO.isEmpty()) {
                                return null;
                            }
                            return new OrderItemResponse(o.getItemId(), groceryItemO.get().getName(), o.getQuantity(), o.getPrice());
                        }).filter(ObjectUtils::isNotEmpty).toList();

                return new OrderResponse(r.getId(), r.getTotalAmount(), r.getOrderDate(), items);
            }).toList();

        }

        return new Response(Constant.OK, false, responses);
    }

    @Override
    public Response getAvailableGroceryItems(Integer offset, Integer limit) {

        List<GroceryItemResponse> itemResponses = new LinkedList<>();
        var items = iGroceryItemRepository.getGroceryList(offset, limit, null, true);
        if(ObjectUtils.isNotEmpty(items)) {
            itemResponses = items.stream().map(groceryItem -> new GroceryItemResponse(groceryItem.getId(), groceryItem.getName(), groceryItem.getPrice(), groceryItem.getDescription(), groceryItem.getQuantity())).toList();
        }

        return new Response(Constant.OK, false, itemResponses);
    }
}
