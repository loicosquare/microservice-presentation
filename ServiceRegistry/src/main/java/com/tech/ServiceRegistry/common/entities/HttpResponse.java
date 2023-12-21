package com.tech.ServiceRegistry.common.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europa/Paris")
    private Date timeStamp;
    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String reason;
    private String message;
}
