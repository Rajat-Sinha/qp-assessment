package com.grocery.mgmt.platform.common.representation.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemRequest {

    private String name;
    private Float price;
    private String description;
    private Integer quantity;

}
