package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Algorithm;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonElement;

import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable> {
    @GetMapping("/{id}")
    default T getById(
            @PathVariable int id
    ){
        return Algorithm.<T>find(getJsonTable(), pred -> pred.id == id);
    }
    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, pred -> true);
    }

}