package com.grocery.mgmt.platform.common.representation;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginatedValue implements Serializable{

	@Serial
	private static final long serialVersionUID = -13552655219994085L;

	private Integer noOfRows;
	private Integer offset;

}
