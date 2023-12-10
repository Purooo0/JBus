package com.AdheliaPutriMaylaniJBusBR;

import com.AdheliaPutriMaylaniJBusBR.dbjson.Serializable;

/**
 * The Voucher class represents a voucher that can be used for discounts or rebates on prices.
 *
 * @author Adhelia Putri Maylani
 */
public class Voucher extends Serializable {
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;

    /**
     * Constructs a new Voucher object with the specified parameters.
     *
     * @param id The identifier of the voucher.
     * @param name The name of the voucher.
     * @param code The code associated with the voucher.
     * @param type The type of the voucher (DISCOUNT or REBATE).
     * @param minimum The minimum price requirement for applying the voucher.
     * @param cut The discount percentage or rebate amount offered by the voucher.
     */
    public Voucher(int id, String name, int code, Type type, double minimum, double cut) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * Checks if the voucher has been used.
     *
     * @return True if the voucher has been used; otherwise, false.
     */
    public boolean isUsed(){
        return used;
    }

    /**
     * Checks if the voucher can be applied to a given price.
     *
     * @param price The Price object to which the voucher is applied.
     * @return True if the voucher can be applied; otherwise, false.
     */
    public boolean canApply(Price price) {
        if (price.price > minimum && !used) {
            return true;
        }
        return false;
    }

    /**
     * Applies the voucher to a given price and marks it as used.
     *
     * @param price The Price object to which the voucher is applied.
     * @return The adjusted price after applying the voucher.
     */
    public double apply(Price price){
        used = true;

        // Applying discount logic
        if(type == Type.DISCOUNT){
            // Ensure discount percentage is not greater than 100
            if(cut > 100){
                cut = 100;
            }
            // Check if the discount is 100%, return 0 in that case
            if(cut == 100){
                return 0;
            }
            // Calculate the adjusted price after applying the discount
            return price.price - ((cut/100.0) * price.price);
        }
        // Applying rebate logic
        if(type == Type.REBATE){
            // Check if the rebate amount is greater than or equal to the price
            if(cut >= price.price){
                return 0;
            }
            else{
                // Calculate the adjusted price after applying the rebate
                return price.price - cut;
            }
        }
        // Default: return the original price if the voucher type is unknown
        return price.price;
    }

    /**
     * Reads the voucher information from a file.
     *
     * @param file The file path from which to read the voucher information.
     * @return True if reading is successful; otherwise, false.
     */
    public boolean read(String file){
        // Placeholder implementation, to be replaced with actual file reading logic
        return true;
    }

    /**
     * Writes the voucher information to an object.
     *
     * @return Null since the method does not implement writing to an object.
     */
    public Object write(){
        // Placeholder implementation, to be replaced with actual object writing logic
        return null;
    }
}