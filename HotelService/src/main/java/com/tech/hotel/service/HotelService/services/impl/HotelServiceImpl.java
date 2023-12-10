package com.tech.hotel.service.HotelService.services.impl;

import com.tech.hotel.service.HotelService.entities.Hotel;
import com.tech.hotel.service.HotelService.exception.ResourceNotFoundException;
import com.tech.hotel.service.HotelService.repository.HotelRepository;
import com.tech.hotel.service.HotelService.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    /**
     * @param hotel
     * @return
     */
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();

        return  hotelRepository.save(Hotel.builder()
                .id(hotelId)
                .name(hotel.getName())
                .location(hotel.getLocation())
                .about(hotel.getAbout())
                .build()
        );
    }

    /**
     * @return
     */
    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll().stream().toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with id : {} not found !!" + id));
    }
}
