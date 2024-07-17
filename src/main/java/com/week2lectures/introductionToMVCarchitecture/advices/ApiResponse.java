package com.week2lectures.introductionToMVCarchitecture.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{

    @JsonFormat(pattern="hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public ApiResponse(T data){
        this();
        this.data=data;
    }
    public ApiResponse(ApiError error){
        this();
        this.error=error;
    }
    public ApiResponse(){
        this.timestamp= LocalDateTime.now();
    }
}




