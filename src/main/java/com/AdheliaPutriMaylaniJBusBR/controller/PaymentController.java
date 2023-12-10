package com.AdheliaPutriMaylaniJBusBR.controller;

import com.AdheliaPutriMaylaniJBusBR.*;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonAutowired;
import com.AdheliaPutriMaylaniJBusBR.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * Controller for managing operations related to payment and ticket booking.
 *
 * @see BasicGetController
 * @see Payment
 * @see JsonAutowired
 * @see JsonTable
 * @author Adhelia Putri Maylani
 */

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{
    /**
     * The JSON table storing payment data.
     */
    @JsonAutowired(value = Payment.class, filepath = "/Users/adhelia/Desktop/CS/JBus/src/main/java/com/AdheliaPutriMaylaniJBusBR/json/payment.json")
    public static JsonTable<Payment> paymentTable;

    /**
     * Retrieves the JSON table for payments.
     *
     * @return the JSON table for payments
     */
    @Override
    public JsonTable<Payment> getJsonTable() {
        return this.paymentTable;
    }

    /**
     * Endpoint for making ticket reservations and payments.
     *
     * @param buyerId        ID of the buyer
     * @param renterId       ID of the renter
     * @param busId          ID of the reserved bus
     * @param busSeats       List of reserved seats
     * @param departureDate  Departure date
     * @return Response containing the result of the reservation and payment
     */
    @RequestMapping(value = "/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        try {
            // Finding buyer account and bus based on ID
            Account buyer = Algorithm.<Account>find(new AccountController().getJsonTable(), t -> t.id == buyerId);
            Bus bus = Algorithm.<Bus>find(new BusController().getJsonTable(), t -> t.id == busId);
            Timestamp date = Timestamp.valueOf(departureDate);

            // Input validation
            if(buyerId <= 0 || renterId <= 0 || busId <= 0 || busSeats == null || departureDate == null){
                return new BaseResponse<>(false, "Field cannot be empty", null);
            } else if (buyer == null || bus == null) {
                return new BaseResponse<>(false, "Failed: Account doesn't exist", null);
            } else if (buyer.balance < bus.price.price) {
                return new BaseResponse<>(false, "Failed: Your balance isn't enough to make booking", null);
            } else if (!Algorithm.<Schedule>exists(bus.schedules, t-> t.departureSchedule.equals(date))) {
                return new BaseResponse<>(false, "Failed: Schedule not available", null);
            }

            // Creating a Payment object for booking and payment
            Payment payment = new Payment(buyerId, renterId, busId, String.valueOf(busSeats), date);
            payment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Successful", payment);
        } catch(Exception e){
            e.printStackTrace();
            return new BaseResponse<>(false, "There was an error in make your booking", null);
        }
    }

    /**
     * Endpoint for accepting payment.
     *
     * @param id ID of the payment to be accepted
     * @return Response containing the result of the payment acceptance
     */
    @RequestMapping(value="/{id}/accept", method = RequestMethod.POST)
    public BaseResponse<Payment> accept (
            @PathVariable int id
    ) {
        try {
            // Finding Payment object based on ID
            Payment payment = getById(id);
            if(payment == null){
                return new BaseResponse<>(false, "Payment failed", null);
            }

            // Changing payment status to SUCCESS
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment Success", payment);
        } catch (Exception e){
            e.printStackTrace();
            return new BaseResponse<>(false, "There was an error in payment", null);
        }
    }

    /**
     * Endpoint for canceling payment.
     *
     * @param id ID of the payment to be canceled
     * @return Response containing the result of the payment cancellation
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public BaseResponse<Payment> cancel (
            @PathVariable int id
    ) {
        try {
            // Finding Payment object based on ID
            Payment payment = getById(id);

            // Changing payment status to FAILED
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Cancel payment", payment);
        } catch (Exception e){
            e.printStackTrace();
            return new BaseResponse<>(false, "Failed to cancel the payment", null);
        }
    }
}