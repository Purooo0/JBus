package AdheliaPutriMaylaniJBusBR;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Renter{
    public String address;
    public String companyName;
    public String phoneNumber;
    private final String REGEX_PHONE = "^[0-9]{9,12}$";
    private final String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{3,19}$";

    public Renter(int id, String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }

    public Renter(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Renter(String companyName) {
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }

    public Renter(String companyName, String address) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = "";
    }

    public boolean validate() {
        Pattern namePattern = Pattern.compile(REGEX_NAME);
        Matcher name = namePattern.matcher(companyName);
        Pattern phonePattern = Pattern.compile(REGEX_PHONE);
        Matcher phone = phonePattern.matcher(phoneNumber);

        boolean companyNameValid = name.matches();
        boolean phoneNumberValid = phone.matches();

        return companyNameValid && phoneNumberValid;
    }
}