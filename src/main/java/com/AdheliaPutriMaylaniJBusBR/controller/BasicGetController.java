package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Algorithm;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.AdheliaPutriMaylaniJBusBR.Algorithm.paginate;

public interface BasicGetController <T extends Serializable>{
    JsonTable<T> getJsonTable();

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ) {
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, a -> true);
    }
}
