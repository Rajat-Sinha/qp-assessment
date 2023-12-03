package com.grocery.mgmt.platform.common.representation;

import com.grocery.mgmt.platform.common.util.Constant;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ToString
public class Response {

	private Integer status;
	private String message;
	private Object response;
	private Long timestamp;
	private Boolean errorType;

	public Response() {
		super();
	}

	public Response(Integer status, String message, Long timestamp, Boolean errorType, Object response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
		this.timestamp = timestamp;
		this.errorType = errorType;
	}
	
	public Response(Integer status, String message, Boolean errorType, Object response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
		this.timestamp = System.currentTimeMillis();
		this.errorType = errorType;
	}

	public Response(Integer status, Boolean errorType, Object response) {
		super();
		this.status = status;
		this.message = Constant.EMPTY_STRING;
		this.response = response;
		this.timestamp = System.currentTimeMillis();
		this.errorType = errorType;
	}

	public Response(Integer status, String message, Boolean errorType) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
		this.errorType = errorType;
	}
}

