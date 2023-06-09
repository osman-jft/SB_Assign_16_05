package com.example.sb_assign_16_05_23.customEndpoints;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.util.Constants;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Endpoint(id = "hello-world")
@Component
public class HelloWorldEndpoint {
    @ReadOperation
    public ResponseDTO<String> customEndPointByName() {
        return ResponseDTO.<String>builder().data("Hello World")
                .message(Constants.SUCCESS_MSG).status(HttpStatus.OK.value()).build();
    }
}
