package com.newborne.yizhi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Api response.
 *
 * @param <T> the type parameter
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private T data;

    /**
     * Success api response.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the api response
     */
    public static <T> ApiResponse success(T data) {
        return ApiResponse.success("请求成功", data);
    }

    /**
     * Success api response.
     *
     * @param <T>     the type parameter
     * @param message the message
     * @param data    the data
     * @return the api response
     */
    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(Boolean.TRUE, message, data);
    }

    /**
     * Failed api response.
     *
     * @return the api response
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * Failed api response.
     *
     * @param message the message
     * @return the api response
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse(Boolean.FALSE, message, null);
    }
}
