package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Account;
import com.AdheliaPutriMaylaniJBusBR.Algorithm;
import com.AdheliaPutriMaylaniJBusBR.Renter;
import com.AdheliaPutriMaylaniJBusBR.Predicate;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping
    String index(){
        return "account";
    }

    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        String encyptedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encyptedPassword = sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Account acc = new Account(name, email, password);
        Account responseAcc = new Account(name, email, encyptedPassword);
        if(!acc.name.isBlank() && acc.validate() && !Algorithm.<Account>exists(getJsonTable(), t -> t.email == acc.email)){
            accountTable.add(responseAcc);
            return new BaseResponse<Account>(true, "Register succesful", responseAcc);
        }
        return new BaseResponse<Account>(false, "Failed to register", null);
    }

    @PostMapping("/Login")
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password
    ){
        String encyptedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            encyptedPassword = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        Account acc = new Account(null, email, encyptedPassword);
        if (
                Algorithm.<Account>exists(getJsonTable(), akun -> akun.email.equals(acc.email)
                        && akun.password.equals(acc.password))
        ) {
            return new BaseResponse<Account>(true, "Login successful", acc);
        }
        return new BaseResponse<Account>(false, "Failed to Login", null);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        Account account = Algorithm.<Account>find(accountTable, pred -> pred.id == id);
        if (account != null) {
            Renter renter = new Renter(companyName, address, phoneNumber);
            return new BaseResponse<>(true, "Successful", renter);
        }
        return new BaseResponse<>(false, "Failed", null);
    }

    @PostMapping("/{id}/topUp")
    boolean topUp(@PathVariable int id, @RequestParam double balance ){
        Account account = Algorithm.<Account>find(getJsonTable(), acc -> id == acc.id);
        if (account != null){
            account.balance += balance;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public JsonTable<Account> getJsonTable() {
        return this.accountTable;
    }
}