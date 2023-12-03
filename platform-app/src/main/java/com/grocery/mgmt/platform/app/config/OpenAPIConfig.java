package com.grocery.mgmt.platform.app.config;

import com.grocery.mgmt.platform.common.util.Constant;
import com.grocery.mgmt.platform.common.util.Utils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        var info = new Info()
                .title("Grocery Management & Booking Service")
                .version(Constant.V1_API_VERSION.replace("/", ""))
                .contact(Utils.getOpenApiContact())
                .description("Grocery Management & Booking Service api")
                .license(Utils.OA_LICENSE);

        return new OpenAPI().info(info);

    }
}
