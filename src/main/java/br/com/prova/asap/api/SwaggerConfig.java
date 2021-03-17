/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prova.asap.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Santana
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Emanuel - Prova ASAP", "", ""))
                .title("Apolices")
                .description("Documentação API APOLICES")
                .build();

    }

    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(200)
                        .message("OK")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("NOT FOUND")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("BAD REQUEST")
                        .build());
            }
        };
    }
    
    private List<ResponseMessage> responseMessageForDELETE() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(200)
                        .message("OK")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("NOT FOUND")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("BAD REQUEST")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessageForPOST() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(201)
                        .message("CREATED")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("INTERNAL SERVER ERROR")
                        .responseModel(new ModelRef("Error"))
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("BAD REQUEST")
                        .build());

            }
        };
    }

    private List<ResponseMessage> responseMessageForPUT() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(200)
                        .message("OK")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("INTERNAL SERVER ERROR")
                        .responseModel(new ModelRef("Error"))
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("NOT FOUND")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("BAD REQUEST")
                        .build());

            }
        };
    }
}
