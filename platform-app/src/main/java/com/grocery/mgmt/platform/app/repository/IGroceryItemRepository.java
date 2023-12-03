package com.grocery.mgmt.platform.app.repository;

import com.grocery.mgmt.platform.common.entity.GroceryItem;
import com.grocery.mgmt.platform.common.entity.Order;
import com.grocery.mgmt.platform.common.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IGroceryItemRepository {

    /**
     * Get Grocery Item By Name
     * @param name
     * @param id
     * @return
     */
    GroceryItem getGroceryItemByName(String name, UUID id);

    /**
     * Save Grocery Item
     * @param groceryItem
     */
    void saveGroceryItem(GroceryItem groceryItem);

    /**
     * Get Grocery Item by id
     * @param id
     * @return
     */
    GroceryItem getGroceryItemById(UUID id);

    /**
     * Update Grocery Item
     * @param groceryItem
     */
    void updateGroceryItem(GroceryItem groceryItem);

    /**
     * Get Grocery List
     * @param offset
     * @param limit
     * @param itemIds
     * @param isQuantityAvailable
     * @return
     */
    List<GroceryItem> getGroceryList(Integer offset, Integer limit, List<UUID> itemIds, Boolean isQuantityAvailable);

    /**
     * Get Total Count of grocery
     * @return
     */
    Integer getTotalCountOfGrocery();

    /**
     * Save Grocer Order
     * @param orderItem
     * @param groceryItemUpdates
     * @param orderItems
     */
    void saveGroceryItemOrder(Order orderItem, List<GroceryItem> groceryItemUpdates, List<OrderItem> orderItems);

    /**
     * Get Order list by user id
     * @param userId
     * @return
     */
    List<Order> getOrderDataByUserId(UUID userId);

    /**
     * Get order item list
     * @param orderIds
     * @return
     */
    List<OrderItem> getOrderItemsList(List<UUID> orderIds);
}
