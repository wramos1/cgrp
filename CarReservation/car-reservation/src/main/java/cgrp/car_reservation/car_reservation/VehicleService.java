package cgrp.car_reservation.car_reservation;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(ObjectId id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getAllByType(String type){
        logger.info("Mapping hit!");
        return vehicleRepository.findByType(type);
    }
}
