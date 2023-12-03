package com.grocery.mgmt.platform.common.representation.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemResponseList {

    private List<GroceryItemResponse> data;
    private Integer total;

}
