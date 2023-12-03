package com.grocery.mgmt.platform.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order_item")
@Audited
@AuditTable(value = "t_order_item_audit")
public class OrderItem {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price", columnDefinition = "FLOAT")
    private Float price;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "updated_by", nullable = false)
    private UUID updatedBy;

    @Column(name = "last_action_type")
    private String lastActionType;

    public OrderItem(UUID id, UUID orderId, UUID itemId, int quantity, Float price, Timestamp createdOn, UUID createdBy) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = createdOn;
        this.updatedBy = createdBy;
    }
}
