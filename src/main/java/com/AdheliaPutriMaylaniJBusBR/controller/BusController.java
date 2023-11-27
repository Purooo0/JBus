package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import static com.AdheliaPutriMaylaniJBusBR.controller.AccountController.accountTable;

import java.sql.Timestamp;
import java.util.List;
import java.util.Iterator;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    @JsonAutowired(value = Bus.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/bus_db.json")
    public static JsonTable<Bus> busTable;
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
    ) {
        Account account = Algorithm.<Account>find((Iterator<Account>) accountTable, pred -> pred.id == accountId);

        if (account == null && account.company == null) {
            return new BaseResponse<>(false, "Failed", null);
        }
        Bus bus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, stationDepartureId, stationArrivalId);
        busTable.add(bus);
        return new BaseResponse<>(true, "Successful", bus);
    }
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam Timestamp time
    ) {
        Bus bus = Algorithm.<Bus>find(busTable, pred -> pred.id == busId);
        if (bus == null) {
            return new BaseResponse<>(false, "Bus not found", null);
        }
        try {
            bus.addSchedule(time);
            return new BaseResponse<>(true, "Successfully added schedule", bus);
        }
        catch (Exception e) {
            return new BaseResponse<>(false, "Failed to add schedule", null);
        }
    }

    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId){
        return Algorithm.<Bus>collect(getJsonTable(), b -> b.accountId == accountId);
    }
    @Override
    public JsonTable<Bus> getJsonTable() {
        return null;
    }
}
