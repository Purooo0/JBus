package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import static com.AdheliaPutriMaylaniJBusBR.controller.AccountController.accountTable;
import static com.AdheliaPutriMaylaniJBusBR.controller.BusController.busTable;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    @JsonAutowired(value = Payment.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/payment.json")
    public static JsonTable<Payment> paymentTable;
    @RequestMapping(value = "/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        Account buyer = Algorithm.<Account>find((Iterator<Account>) accountTable, pred -> pred.id == buyerId);
        Bus bus = Algorithm.<Bus>find(busTable, pred -> pred.id == busId);

        if (buyer == null || bus == null){
            return new BaseResponse<>(false, "Buyer not found", null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try{
            date = sdf.parse(departureDate);
        } catch(ParseException e){
            e.printStackTrace();
            return null;
        }

        Timestamp departureTimestamp = new Timestamp(date.getTime());
        if(buyer.balance < bus.price.price){
            return new BaseResponse<>(false, "The balance is not sufficient", null);
        }

        Schedule schedule = Algorithm.<Schedule>find(bus.schedules, s -> s.departureSchedule.equals(departureTimestamp));
        if(schedule == null){
            return new BaseResponse<>(false, "Schedule not found", null);
        }
        if(Payment.makeBooking(departureTimestamp, busSeats, bus)){
            buyer.balance -= bus.price.price * busSeats.size();

            Payment payment = new Payment(buyerId, renterId, busId, busSeats.toString(), departureTimestamp);
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Successful payment", payment);
        } else {
            return new BaseResponse<>(false, "Failed to make payment", null);
        }
    }
    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept (@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return new BaseResponse<>(false, "Failed", null);
        } else {
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Successful", payment);
        }
    }
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel (@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return new BaseResponse<>(false, "Failde", null);
        }
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == payment.buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, bus1 -> bus1.id == payment.getBusId());
        payment.status = Invoice.PaymentStatus.FAILED;
        buyer.balance += bus.price.price;
        return new BaseResponse<>(true, "Successful", payment);
    }
    @Override
    public JsonTable<Payment> getJsonTable() {
        return null;
    }
}
