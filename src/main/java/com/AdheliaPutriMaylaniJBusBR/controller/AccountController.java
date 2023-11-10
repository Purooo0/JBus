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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/account.json")
    public static JsonTable<Account> accountTable;

    @GetMapping("/account")
    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        if(!name.isBlank() && new Account(name, email, password).validate() && !Algorithm.exists((Iterable<Account>) accountTable, (Predicate<Account>) e -> e.email.equals(email))){
            String finalPass = null;
            try{
                MessageDigest msgs = MessageDigest.getInstance("MD5");
                msgs.update(password.getBytes());
                byte[] bytes = msgs.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                finalPass = sb.toString();
            }
            catch(NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            Account account =  new Account(name, email, finalPass);
            return new BaseResponse<>(true, "Berhasil register.", account);
        } else {
            return new BaseResponse<>(false, "Gagal registe.", null);
        }
    }

    @PostMapping("/Login")
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password
    )
    {
        Account account = Algorithm.<Account>find((Iterator<Account>) accountTable, pred -> pred.email == email);
        if(account != null){
            String resultPass = null;
            try{
                MessageDigest msg = MessageDigest.getInstance("MD5");
                msg.update(password.getBytes());
                byte[] bytes = msg.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                resultPass = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (resultPass == account.password) {
                return new BaseResponse<>(true, "Berhasil login", account);
            }
        }
        return new BaseResponse<>(false, "Gagal login", null);
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
}