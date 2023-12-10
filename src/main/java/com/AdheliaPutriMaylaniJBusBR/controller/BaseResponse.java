package com.AdheliaPutriMaylaniJBusBR.controller;

/**
 * General representation of an API response with three main components: success, message, and payload.
 * @param <T> The type of data payload to be included in the response.
 * @Author Adhelia Putri Maylani
 */

public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    /**
     * Constructor to create an instance of the BaseResponse class.
     * @param success Indicates the success of the operation (true if successful, false if failed).
     * @param message Additional information message about the result of the operation.
     * @param payload Additional data related to the response.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
