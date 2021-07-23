package com.newborne.yizhi.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 接口返回对象
 * @author 刘博文
 * @date 2021/07/20 20:43
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private T data;

    /**
     * 请求成功
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    public static <T> ApiResponse success(T data) {
        return ApiResponse.success("请求成功", data);
    }

    /**
     * 请求成功
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(Boolean.TRUE, message, data);
    }

    /**
     * 请求失败
     * @author 刘博文
     * @date 2021/07/20 20:43
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * 请求失败
     * @author 刘博文
     * @date 2020-03-07 11:06
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse(Boolean.FALSE, message, null);
    }
}
