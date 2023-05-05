package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.HashSet;

public class HotelRepository {
     HashMap<String,Hotel> hotelMap;
    HashMap<Integer,User> userMap;
    HashMap<String,Booking> bookingMap;

     public HotelRepository(){
         this.hotelMap = new HashMap<>();
         this.userMap = new HashMap<>();
         this.bookingMap = new HashMap<>();
     }


    public void addHotel(Hotel hotel) {
         hotelMap.put(hotel.getHotelName(), hotel);
    }

    public void addUser(User user) {
         userMap.put(user.getaadharCardNo(),user);
    }

    public void bookRoom(String uuidAsString, Booking booking) {
         bookingMap.put(uuidAsString,booking);
    }

    public int getBookings(Integer aadharCard) {
         int countNoOfBookings = 0;
         for(String key : bookingMap.keySet()){
             Booking booking = bookingMap.get(key);
             if(booking.getBookingAadharCard() == aadharCard) countNoOfBookings++;
         }
         return countNoOfBookings;
    }
}
