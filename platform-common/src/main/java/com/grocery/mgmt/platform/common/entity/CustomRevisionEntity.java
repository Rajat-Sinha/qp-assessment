package com.grocery.mgmt.platform.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@RevisionEntity
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVINFO")
public class CustomRevisionEntity implements Serializable{

	@Serial
    private static final long serialVersionUID = 7916134637326101409L;

	@Id
    @RevisionNumber
    @GeneratedValue
    @Column(name = "rev")
    private Integer rev;

    @RevisionTimestamp
    @Column(name = "revtstmp")
    private Long revtstmp;

}
