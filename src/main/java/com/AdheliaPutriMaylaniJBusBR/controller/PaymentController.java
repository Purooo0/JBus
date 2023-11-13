package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/Payment")
public class PaymentController implements BasicGetController<Payment>{
    @JsonAutowired(value = Payment.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/Payment.java")
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return false;
        } else {
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id){
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING){
            return false;
        }
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == payment.buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, bus1 -> bus1.id == payment.getBusId());
        payment.status = Invoice.PaymentStatus.FAILED;
        buyer.balance += bus.price.price;
        return true;
    }

    @PostMapping("/makeBooking")
    public Payment makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ){
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, bus1 -> bus1.id == busId);

        if (buyer == null || bus == null){
            return null;
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
            return null;
        }

        Schedule schedule = Algorithm.<Schedule>find(bus.schedules, s -> s.departureSchedule.equals(departureTimestamp));
        if(schedule == null){
            return null;
        }
        if(Payment.makeBooking(departureTimestamp, busSeats, bus)){
            buyer.balance -= bus.price.price * busSeats.size();

            Payment payment = new Payment(buyerId, renterId, busId, busSeats, departureTimestamp);
            paymentTable.add(payment);
            return payment;
        } else {
            return null;
        }
    }
}
