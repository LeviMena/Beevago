package com.Bivago.App.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bivago.App.exceptions.HotelAddressLengthException;
import com.Bivago.App.exceptions.HotelCityNameLengthException;
import com.Bivago.App.exceptions.HotelExistsException;
import com.Bivago.App.exceptions.HotelNameLengthException;
import com.Bivago.App.models.HotelModel;
import com.Bivago.App.repositories.HotelRepository;

@Service
public class HotelService {
    
    @Autowired
    private HotelRepository hr;

    private static final int HOTELNAMEMINIMUMLENGTH = 2;
    private static final int HOTELNAMEMAXIMUMLENGTH = 48;
    private static final int HOTELCITYNAMEMAXIMUMLENGTH = 128;
    private static final int HOTELADDRESSMAXIMUMLENGTH = 512;

    public void saveHotel(HotelModel hotel) throws Exception {

        try {
            
            if (hr.findByHotelName(hotel.getHotelName()) != null) {
                throw new HotelExistsException("Já existe um Hotel com esse nome.");
            }

            if (hotel.getHotelName().length() < HOTELNAMEMINIMUMLENGTH || hotel.getHotelName().length() > HOTELNAMEMAXIMUMLENGTH ) {
                throw new HotelNameLengthException("Nome do Hotel deve conter entre 2 a 48 caracteres.");
            }

            if (hotel.getHotelAddress().length() < HOTELADDRESSMAXIMUMLENGTH) {
                throw new HotelAddressLengthException("Endereço do Hotel deve conter no máximo 512 caracteres.");
            }

            if (hotel.getHotelCity().length() < HOTELCITYNAMEMAXIMUMLENGTH) {
                throw new HotelCityNameLengthException("Nome da Cidade do Hotel deve conter no máximo 128 caracteres.");
            }

        } catch (Exception e) {
            
        }

        hr.save(hotel);

    }

    public List<HotelModel> findAllHotels() {
        return hr.findAll();
    }

}
