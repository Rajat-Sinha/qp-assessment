package com.grocery.mgmt.platform.app.controller;

import com.grocery.mgmt.platform.app.service.IGroceryItemService;
import com.grocery.mgmt.platform.common.representation.request.GroceryItemRequest;
import com.grocery.mgmt.platform.common.representation.request.InventoryManagementRequest;
import com.grocery.mgmt.platform.common.util.Constant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = Constant.GI_URL)
@Tag(name = "Grocery Item", description = "Grocery Item")
public class GroceryItemController {

    @Autowired
    IGroceryItemService iGroceryItemService;

    @GetMapping("/list")
    public ResponseEntity<?> getGroceryList(@RequestParam(name = "offset", required = false) Integer offset,
                                            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        var response = iGroceryItemService.getGroceryList(offset, limit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGroceryItem(@RequestBody GroceryItemRequest groceryItemRequest) {
        var response = iGroceryItemService.addGroceryItem(groceryItemRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getGroceryItem(@PathVariable UUID id) {
        var response = iGroceryItemService.getGroceryItem(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateGroceryItem(@PathVariable UUID id, @RequestBody GroceryItemRequest groceryItemRequest) {
        var response = iGroceryItemService.updateGroceryItem(id, groceryItemRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/manage-inventory")
    public ResponseEntity<?> manageInventory(@PathVariable UUID id, @RequestBody InventoryManagementRequest imr) {
        var response = iGroceryItemService.manageInventory(id, imr);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> removeGroceryItems(@PathVariable UUID id) {
        var response = iGroceryItemService.removeGroceryItems(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
