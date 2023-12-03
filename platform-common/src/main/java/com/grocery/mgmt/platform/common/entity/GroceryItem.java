package com.grocery.mgmt.platform.common.entity;

import com.grocery.mgmt.platform.common.representation.ActionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_grocery_item")
@Audited
@AuditTable(value = "t_grocery_item_audit")
@Where(clause = "last_action_type != 'DELETE'")
public class GroceryItem {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", columnDefinition = "FLOAT")
    private Float price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

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

    public GroceryItem(UUID id, String name, Float price, String description, int quantity, Timestamp createdOn, UUID createdBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.updatedOn = createdOn;
        this.lastActionType = ActionType.CREATE.name();
    }
}
