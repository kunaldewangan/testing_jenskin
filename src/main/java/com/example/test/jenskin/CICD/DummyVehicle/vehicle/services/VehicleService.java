package com.example.test.jenskin.CICD.DummyVehicle.vehicle.services;



import com.example.test.jenskin.CICD.DummyVehicle.vehicle.model.VehicleModel;
import com.example.test.jenskin.CICD.DummyVehicle.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleModel> getAllVehicles() {
        return vehicleRepository.findAll();
    }




}
