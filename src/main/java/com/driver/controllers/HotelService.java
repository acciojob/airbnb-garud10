package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HotelService {
    HotelRepository hotelRepository = new HotelRepository();

    public String addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null){
            return "FAILURE";
        }
        else if(hotelRepository.hotelMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelRepository.addHotel(hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        hotelRepository.addUser(user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        HashMap<String,Hotel> hotelMap = hotelRepository.hotelMap;
        int facilities = 0;
        String name = "";
        for(String nameOfHotel : hotelMap.keySet()){
            Hotel hotel = hotelMap.get(nameOfHotel);
            int sizeOfFacilities = hotel.getFacilities().size();
            if(sizeOfFacilities > facilities){
                facilities = sizeOfFacilities;
                name = hotel.getHotelName();
            }
            else if(sizeOfFacilities == facilities){
                if(hotel.getHotelName().compareTo(name) < 0){
                    name = hotel.getHotelName();
                }
            }
        }

        if(facilities == 0) return "";
        return name;
    }

    public int bookRoom(Booking booking) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        hotelRepository.bookRoom(uuidAsString,booking);

        Hotel hotel = hotelRepository.hotelMap.get(booking.getHotelName());
        if(hotel.getAvailableRooms() < booking.getNoOfRooms()){
            return -1;
        }
        int ammount = hotel.getPricePerNight() * booking.getNoOfRooms();
        hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());
        return ammount;
    }

    public int getBokkings(Integer aadharCard) {
        return hotelRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelRepository.hotelMap.get(hotelName);
        List<Facility> prevFacilities = hotel.getFacilities();
        for(Facility facility : newFacilities){
            if(!prevFacilities.contains(facility)){
                prevFacilities.add(facility);
            }
        }
        hotel.setFacilities(prevFacilities);
        return hotel;
    }
}

