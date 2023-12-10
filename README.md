# JBus Application
JBus is an application designed to facilitate bus booking and management. Below is a brief overview of how the application works:

1. Bus Management:
* The application allows the creation and management of buses with details such as name, facilities, price, capacity, type, departure, arrival, and schedules.
* Each bus has a list of schedules, representing its departure timings.

2. Booking Process:
* Users can create booking threads that attempt to add a schedule to a bus at a specified timestamp.
* The booking process is synchronized to avoid conflicts when multiple threads attempt to book the same schedule simultaneously.
* If a booking is successful, a message is printed, otherwise, an error message is displayed.

3. Invoice and Payment:
* The application supports invoicing, representing the interaction between a buyer, renter, and a bus.
* Invoice includes buyer and renter information, rating, payment status, and timestamp.

4. Payment Handling:
* The Payment class extends the Invoice class and adds details related to bus ID, bus seats, and departure date.
* It provides methods for making bookings for specific seats, handling payments, and checking seat availability.

5. Entities and Utilities:
* The application includes various entities such as Bus, Facility, City, BusType, Price, Rating, Renter, Review, Schedule, Station, Voucher, and more.
* Utility classes like Validate are provided for filtering and handling Price objects based on specific criteria.

6. Voucher System:
* The Voucher class represents discount or rebate vouchers that can be applied to prices.
* It checks if a voucher can be applied based on certain conditions and adjusts the price accordingly.

7. Serialization and Storage:
* Serializable is a base class extended by various entities for reading and writing object data.
* Review and Voucher classes demonstrate the use of serialization.

8. Rating System:
* The Rating class manages a rating system by keeping track of the total and count of ratings to calculate the average rating.

9. Validation:
* The application includes validation mechanisms, such as validating Renter information based on company name and phone number patterns.

10. Enums:
* Enums like Type, Facility, City, and BusType are used to represent specific types and categories within the application.

Note:
Detailed functionality and interactions between entities may require a more in-depth examination of the code. The overview provides a high-level understanding of the JBus application's key features.

## Overview
The Bus Booking System (BusBR) is a Java application designed to manage and facilitate bus bookings. It includes classes for buses, schedules, bookings, vouchers, and more. The system is developed as a console application with various functionalities related to bus operations and bookings.

## How to Use
1. Clone the repository.
2. Open the project in your favorite Java IDE.
3. Run the Main class to start the console application.
4. Follow the on-screen instructions to navigate through the functionalities.

## Author
Adhelia Putri Maylani [2206814816]
