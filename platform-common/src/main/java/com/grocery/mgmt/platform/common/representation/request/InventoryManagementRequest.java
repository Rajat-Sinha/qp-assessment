package com.grocery.mgmt.platform.common.representation.request;

import com.grocery.mgmt.platform.common.representation.InventoryAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryManagementRequest {

    private InventoryAction action;
    private Integer quantity;

}
