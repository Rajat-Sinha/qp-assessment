package com.grocery.mgmt.platform.app.service;

import com.grocery.mgmt.platform.common.representation.Response;
import com.grocery.mgmt.platform.common.representation.request.GroceryItemRequest;
import com.grocery.mgmt.platform.common.representation.request.InventoryManagementRequest;

import java.util.UUID;

public interface IGroceryItemService {

    /**
     * Get Grocery List
     * @return
     * @param offset
     * @param limit
     */
    Response getGroceryList(Integer offset, Integer limit);

    /**
     * Add Grocery items
     * @param groceryItemRequest
     * @return
     */
    Response addGroceryItem(GroceryItemRequest groceryItemRequest);

    /**
     * Get Grocery Item
     * @param id
     * @return
     */
    Response getGroceryItem(UUID id);

    /**
     * Update Grocery items
     * @param id
     * @param groceryItemRequest
     * @return
     */
    Response updateGroceryItem(UUID id, GroceryItemRequest groceryItemRequest);

    /**
     * Manage Inventory
     * @param id
     * @param imr
     * @return
     */
    Response manageInventory(UUID id, InventoryManagementRequest imr);

    /**
     * Remove Grocery Items
     * @param id
     * @return
     */
    Response removeGroceryItems(UUID id);
}
