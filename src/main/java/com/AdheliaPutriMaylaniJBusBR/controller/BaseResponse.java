package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Account;
import com.AdheliaPutriMaylaniJBusBR.Renter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }

    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        return new BaseResponse<>(false, "Gagal register", null);
        return new BaseResponse<>(true, "Berhasil register", account);
    }

    BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ){
        return null;
    }

    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        return null;
    }

    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount){
        return null;
    }

}
