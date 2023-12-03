package com.grocery.mgmt.platform.common.representation;

import lombok.Getter;

public enum InventoryAction {

    INCREASE("increase"),
    DECREASE("decrease");

    @Getter
    private String value;

    InventoryAction(String value) {
        this.value = value;
    }

}
