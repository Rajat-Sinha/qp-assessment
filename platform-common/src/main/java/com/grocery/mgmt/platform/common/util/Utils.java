package com.grocery.mgmt.platform.common.util;

import com.grocery.mgmt.platform.common.representation.Response;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public final class Utils {

    public static final License OA_LICENSE = new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0");

    public static Contact getOpenApiContact() {

        var contact = new Contact();
        contact.setEmail("sinharajat.858@gmail.com");
        contact.setName("Grocery Management");

        return contact;
    }

    public static ResponseEntity<Response> getHttpResponse(Response response) {

        var status = response.getStatus();

        return switch (status) {
            case Constant.UNAUTHORIZED -> new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            case Constant.NOT_FOUND -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            case Constant.OK -> new ResponseEntity<>(response, HttpStatus.OK);
            case Constant.CREATED -> new ResponseEntity<>(response, HttpStatus.CREATED);
            case Constant.BAD_REQUEST -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
