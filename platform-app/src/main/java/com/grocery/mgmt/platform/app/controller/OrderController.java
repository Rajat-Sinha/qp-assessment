package com.grocery.mgmt.platform.app.controller;

import com.grocery.mgmt.platform.app.service.IOrderService;
import com.grocery.mgmt.platform.common.representation.request.OrderRequest;
import com.grocery.mgmt.platform.common.util.Constant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = Constant.ORDER_URL)
@Tag(name = "Order Item", description = "Order Item")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        var response = iOrderService.placeOrder(orderRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{userId}/get-order")
    public ResponseEntity<?> getUserOrderList(@PathVariable UUID userId) {
        var response = iOrderService.getUserOrderList(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/available-items")
    public ResponseEntity<?> getAvailableGroceryItems(@RequestParam(name = "offset", required = false) Integer offset,
                                                      @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        var response = iOrderService.getAvailableGroceryItems(offset, limit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
