package com.study.web2.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "message", "result"})
public class BaseResponse<T> {
    private final Boolean isSuccess;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public BaseResponse(T result) {
        this.isSuccess = true;
        this.message = null;
        this.result = result;
    }

    public BaseResponse(Exception e) {
        this.isSuccess = false;
        this.message = (e.getLocalizedMessage() != null) ? e.getLocalizedMessage() : "Unexpected Error";
        this.result = null;
    }
}