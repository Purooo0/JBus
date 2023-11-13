package com.AdheliaPutriMaylaniJBusBR.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.AdheliaPutriMaylaniJBusBR.controller.AccountController.accountTable;

@RestController
@RequestMapping("/Bus")
public class BusController implements BasicGetController<Bus>{
    @JsonAutowired(value = Bus.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/Bus.java")
    public static JsonTable<Bus> busTable;

    public JsonTable<Bus> getJsonTable(){
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam Station stationDepartureId,
            @RequestParam Station stationArrivalId
    ){
        Account account = Algorithm.<Account>find(accountTable, pred -> pred.id == accountId);
        if(account == null){
            return new BaseResponse<>(false, "Bus not found", null);
        }
        Bus bus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, stationDepartureId, stationArrivalId);
        busTable.add(bus);
        return new BaseResponse<>(true, "Bus created successfully", bus);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time
    ){
        Bus bus =  Algorithm.<Bus>find(busTable, b -> b.id == busId);
        if(bus == null){
            return new BaseResponse<>(false, "Bus not found", null);
        }

        Timestamp scheduleTime;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            scheduleTime = new Timestamp(date.getTime());
        } catch(ParseException e){
            return new BaseResponse<>(false, "Invalid time format", null);
        }
        try{
            bus.addSchedule(scheduleTime);
            return new BaseResponse<>(true, "Schedule added successfully", bus);
        } catch(Exception e){
            return new BaseResponse<>(false, "Failed to add schedule: "+ e.getMessage(), null);
        }
    }

}
