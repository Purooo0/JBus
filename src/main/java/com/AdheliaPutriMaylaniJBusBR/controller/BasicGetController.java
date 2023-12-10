package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.Algorithm;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * An interface that provides basic operations for retrieving data from a data source.
 * @param <T> The type of object that can be serialized and processed by the data source.
 *
 * @Author Adhelia Putri Maylani
 */
public interface BasicGetController <T extends Serializable> {
    /**
     * Gets the JSON table used to store data.
     *
     * @return The JSON table corresponding to the object operated by the implementation of the interface.
     */
    JsonTable<T> getJsonTable();

    /**
     * Gets an object with a specific ID.
     *
     * @param id The requested object ID.
     * @return The object with the corresponding ID or null if not found.
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id) {
        return Algorithm.<T>find(getJsonTable(), e -> e.id == id);
    }

    /**
     * Returns a page of data with a specified number and page.
     *
     * @param page     The requested page number (default: 0).
     * @param pageSize The number of data to be displayed on one page (default: 5).
     * @return The list of data on the requested page.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, pred -> true);
    }

    /**
     * Retrieves a list of all entities.
     *
     * @return A list of all entities.
     */
    @GetMapping("/getAll")
    public default List<T> getAll() {
        return Algorithm.<T>findAll(getJsonTable());
    }
    /**
     * Retrieves the total count of entities.
     *
     * @return the total count of entities
     */
    @GetMapping("/total")
    public default int total() {
        return Algorithm.<T>count(getJsonTable(), t->true);
    }
}