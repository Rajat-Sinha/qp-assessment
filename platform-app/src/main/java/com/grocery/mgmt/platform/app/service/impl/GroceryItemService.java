package com.grocery.mgmt.platform.app.service.impl;

import com.grocery.mgmt.platform.app.repository.IGroceryItemRepository;
import com.grocery.mgmt.platform.app.service.IGroceryItemService;
import com.grocery.mgmt.platform.common.entity.GroceryItem;
import com.grocery.mgmt.platform.common.exception.DataNotFoundException;
import com.grocery.mgmt.platform.common.exception.DuplicateDataException;
import com.grocery.mgmt.platform.common.exception.ParameterMissingException;
import com.grocery.mgmt.platform.common.representation.*;
import com.grocery.mgmt.platform.common.representation.request.GroceryItemRequest;
import com.grocery.mgmt.platform.common.representation.request.InventoryManagementRequest;
import com.grocery.mgmt.platform.common.representation.response.GroceryItemResponse;
import com.grocery.mgmt.platform.common.representation.response.GroceryItemResponseList;
import com.grocery.mgmt.platform.common.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class GroceryItemService implements IGroceryItemService {

    @Autowired
    IGroceryItemRepository iGroceryItemRepository;

    @Override
    public Response getGroceryList(Integer offset, Integer limit) {

        Integer totalGrocery = 0;
        List<GroceryItemResponse> itemResponses = new LinkedList<>();
        var items = iGroceryItemRepository.getGroceryList(offset, limit, null, null);
        if(ObjectUtils.isNotEmpty(items)) {
            itemResponses = items.stream().map(groceryItem -> new GroceryItemResponse(groceryItem.getId(), groceryItem.getName(), groceryItem.getPrice(), groceryItem.getDescription(), groceryItem.getQuantity())).toList();
            totalGrocery = iGroceryItemRepository.getTotalCountOfGrocery();
        }

        return new Response(Constant.OK, false, new GroceryItemResponseList(itemResponses, totalGrocery));
    }

    @Override
    public Response addGroceryItem(GroceryItemRequest gr) {

        var name = gr.getName();
        var price = gr.getPrice();
        var description = gr.getDescription();
        var quantity = gr.getQuantity();

        if(ObjectUtils.isEmpty(name)) {
            throw new ParameterMissingException("Name is missing");
        }

        if(ObjectUtils.isEmpty(price)) {
            throw new ParameterMissingException("Price is missing");
        }

        if(ObjectUtils.isEmpty(description)) {
            throw new ParameterMissingException("Description is missing");
        }

        var groceryItemO = iGroceryItemRepository.getGroceryItemByName(name, null);
        if(ObjectUtils.isNotEmpty(groceryItemO)) {
            throw new DuplicateDataException("Grocery Item already exists");
        }

        if(ObjectUtils.isEmpty(quantity)) {
            quantity = 0;
        }

        var createdOn = Timestamp.valueOf(LocalDateTime.now());
        var createdBy = Constant.DEFAULT_USERID;
        var groceryItem = new GroceryItem(UUID.randomUUID(), name, price, description, quantity, createdOn, createdBy);
        iGroceryItemRepository.saveGroceryItem(groceryItem);

        return new Response(Constant.OK, "Grocery Item added Successfull", false);
    }

    @Override
    public Response getGroceryItem(UUID id) {

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        var groceryItem = iGroceryItemRepository.getGroceryItemById(id);
        if(ObjectUtils.isEmpty(groceryItem)) {
            throw new DataNotFoundException("Grocery item not found");
        }

        var response = new GroceryItemResponse(id, groceryItem.getName(), groceryItem.getPrice(), groceryItem.getDescription(), groceryItem.getQuantity());

        return new Response(Constant.OK, false, response);
    }

    @Override
    public Response updateGroceryItem(UUID id, GroceryItemRequest gr) {

        var name = gr.getName();
        var price = gr.getPrice();
        var description = gr.getDescription();
        var quantity = gr.getQuantity();

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        if(ObjectUtils.isEmpty(name)) {
            throw new ParameterMissingException("Name is missing");
        }

        if(ObjectUtils.isEmpty(price)) {
            throw new ParameterMissingException("Price is missing");
        }

        if(ObjectUtils.isEmpty(description)) {
            throw new ParameterMissingException("Description is missing");
        }

        var groceryItemO = iGroceryItemRepository.getGroceryItemByName(name, id);
        if(ObjectUtils.isNotEmpty(groceryItemO)) {
            throw new DuplicateDataException("Grocery Item already exists");
        }

        var groceryItem = iGroceryItemRepository.getGroceryItemById(id);
        if(ObjectUtils.isEmpty(groceryItem)) {
            throw new DataNotFoundException("Grocery item not found");
        }

        var createdOn = Timestamp.valueOf(LocalDateTime.now());
        var createdBy = Constant.DEFAULT_USERID;

        groceryItem.setName(name);
        groceryItem.setPrice(price);
        if(ObjectUtils.isNotEmpty(quantity)) {
            groceryItem.setQuantity(quantity);
        }
        groceryItem.setDescription(description);
        groceryItem.setUpdatedBy(createdBy);
        groceryItem.setUpdatedOn(createdOn);
        groceryItem.setLastActionType(ActionType.UPDATE.name());

        iGroceryItemRepository.updateGroceryItem(groceryItem);

        return new Response(Constant.OK, "Grocery Item updated Successfull", false);
    }

    @Override
    public Response manageInventory(UUID id, InventoryManagementRequest imr) {

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        var groceryItem = iGroceryItemRepository.getGroceryItemById(id);
        if(ObjectUtils.isEmpty(groceryItem)) {
            throw new DataNotFoundException("Grocery item not found");
        }

        var itemQuantity = groceryItem.getQuantity();
        var action = imr.getAction().getValue();
        var quantity = imr.getQuantity();

        if(action.equals(InventoryAction.INCREASE.getValue())) {
            itemQuantity = itemQuantity + quantity;
        } else if(action.equals(InventoryAction.DECREASE.getValue())){
            if(itemQuantity <= 0 || quantity > itemQuantity) {
                throw new DataNotFoundException("Insufficient quantity available");
            } else {
                itemQuantity = itemQuantity - quantity;
            }
        } else {
            throw new ParameterMissingException("Invalid action");
        }

        var createdOn = Timestamp.valueOf(LocalDateTime.now());
        var createdBy = Constant.DEFAULT_USERID;

        groceryItem.setQuantity(itemQuantity);
        groceryItem.setUpdatedBy(createdBy);
        groceryItem.setUpdatedOn(createdOn);
        groceryItem.setLastActionType(ActionType.UPDATE.name());

        iGroceryItemRepository.updateGroceryItem(groceryItem);

        return new Response(Constant.OK, "Inventory updated successfully", false);
    }

    @Override
    public Response removeGroceryItems(UUID id) {

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        var groceryItem = iGroceryItemRepository.getGroceryItemById(id);
        if(ObjectUtils.isEmpty(groceryItem)) {
            throw new DataNotFoundException("Grocery item not found");
        }

        var createdOn = Timestamp.valueOf(LocalDateTime.now());
        var createdBy = Constant.DEFAULT_USERID;

        groceryItem.setUpdatedBy(createdBy);
        groceryItem.setUpdatedOn(createdOn);
        groceryItem.setLastActionType(ActionType.DELETE.name());

        iGroceryItemRepository.updateGroceryItem(groceryItem);

        return new Response(Constant.OK, "Grocery Item deleted Successfull", false);
    }
}
