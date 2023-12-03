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
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order")
@Audited
@AuditTable(value = "t_order_audit")
public class Order {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "total_amount", columnDefinition = "FLOAT")
    private Float totalAmount;

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

    public Order(UUID id, UUID userId, Timestamp orderDate, Float totalAmount, Timestamp createdOn, UUID createdBy) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.updatedOn = createdOn;
        this.lastActionType = ActionType.CREATE.name();
    }
}
