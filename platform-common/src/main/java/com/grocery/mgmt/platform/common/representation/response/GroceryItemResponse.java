package com.grocery.mgmt.platform.common.representation.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemResponse {

    private UUID id;
    private String name;
    private Float price;
    private String description;
    private Integer quantity;

}
