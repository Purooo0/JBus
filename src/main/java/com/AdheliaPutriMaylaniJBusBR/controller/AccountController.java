package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Account;
import com.AdheliaPutriMaylaniJBusBR.Algorithm;
import com.AdheliaPutriMaylaniJBusBR.Renter;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles the HTTP requests related to user accounts.
 * Controller for handling user account-related operations.
 * @Author Adhelia Putri Maylani
 */

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    // Handling GET request to "/account"
    @GetMapping
    String index(){
        return "account";
    }

    // JsonTable for Account entities
    @JsonAutowired(value = Account.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/account.json")
    public static JsonTable<Account> accountTable;

    @Override
    public JsonTable<Account> getJsonTable() {
        return this.accountTable;
    }

    /**
     * Registers a new account.
     * @param name The name of the account owner.
     * @param email The email address of the account.
     * @param password The password for the account.
     * @return A BaseResponse containing the registration status and the registered account.
     */
    @PostMapping("/register")
    BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        // Encrypting the password using MD5
        String encryptedPassword = encryptPassword(password);
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedPassword = sb.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // Creating new Account instance
        Account acc = new Account(name, email, password);
        Account responseAcc = new Account(name, email, encryptedPassword);

        // Validation and registration process
        if(!acc.name.isBlank() && acc.validate() && !Algorithm.<Account>exists(getJsonTable(), t -> t.email == acc.email)){
            accountTable.add(responseAcc);
            return new BaseResponse<Account>(true, "Register succesful", responseAcc);
        }
        return new BaseResponse<Account>(false, "Failed to register", null);
    }

    /**
     * Handles login requests for user accounts.
     * @param email The email address of the account.
     * @param password The password for the account.
     * @return A BaseResponse containing the login status and the account details if successful.
     */
    @PostMapping("/Login")
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password
    ){
        // Encrypting the provided password using MD5
        String encryptedPassword = encryptPassword(password);
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append((Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1)));
            }
            encryptedPassword = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        // Creating a temporary Account instance for validation
        Account acc = new Account(null, email, encryptedPassword);

        // Checking the existence of the account and validating the login
        if (Algorithm.<Account>exists(getJsonTable(), akun -> akun.email.equals(acc.email) && akun.password.equals(acc.password))) {
            return new BaseResponse<Account>(true, "Login successful", Algorithm.<Account>find(getJsonTable(), t->t.email.equals(acc.email)));
        } else if (Algorithm.<Account>exists(getJsonTable(), t -> t.email.equals(acc.email) && !t.password.equals(acc.password))){
            return new BaseResponse<Account>(false, "Failed to login! Wrong password", null);
        }
        return new BaseResponse<Account>(false, "Failed to Login! Your account doesn't exist", null);
    }

    /**
     * Registers a new Renter for a specific account.
     * @param id The ID of the account for which the Renter is being registered.
     * @param companyName The name of the company associated with the Renter.
     * @param address The address of the Renter.
     * @param phoneNumber The phone number of the Renter.
     * @return A BaseResponse indicating the success or failure of the registration.
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        // Finding the account with the specified ID
        Account account = Algorithm.<Account>find(accountTable, pred -> pred.id == id);
        // Creating a new Renter instance
        Renter renter = new Renter(companyName, address, phoneNumber);

        // Performing validation and registration of the Renter
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.company == null)){
            account.company = renter;
            return new BaseResponse<>(true, "Successful", renter);
        } else if (companyName.isBlank() && address.isBlank() && phoneNumber.isBlank()) {
            return new BaseResponse<>(false, "Field cannot be empty", null);
        } else if (!renter.validate()) {
            return new BaseResponse<>(false, "Failed: Your company name or phone number doesn't meet requirement", null);
        } else if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.company != null)) {
            return new BaseResponse<>(false, "Failed: This id already has company", null);
        }
        return new BaseResponse<>(false, "There was an error in creating a renter account", null);
    }

    /**
     * Processes top-up requests for a specific account.
     * @param id The ID of the account for which the top-up is performed.
     * @param balance The amount to be added to the account balance.
     * @return A BaseResponse containing the top-up status and the updated account balance.
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            @RequestParam double balance
    ){
        // Finding the account with the specified ID
        Account newAccount = Algorithm.<Account>find(accountTable, t -> t.id == id);

        // Performing top-up and returning the response
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id == id && t.countTopUp(balance))== true){
            return new BaseResponse<>(true, "Success to Top Up", newAccount.balance);
        }
        else if (Algorithm.<Account>exists(getJsonTable(), t -> t.countTopUp(balance))!= true){
            return new BaseResponse<>(false, "Failed To Top Up! Your Top Up Amount Is 0 Or Below 0", null);
        }
        if (Algorithm.<Account>exists(getJsonTable(), t -> t.id != id)== true){
            return new BaseResponse<>(false, "Failed To Top Up! Your Id Isn't Exist", null);
        }
        return new BaseResponse<>(false, "Failed to Top Up", null);
    }

    /**
     * Encrypts the provided password using MD5 algorithm.
     * @param password The password to be encrypted.
     * @return The encrypted password.
     */
    private String encryptPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}