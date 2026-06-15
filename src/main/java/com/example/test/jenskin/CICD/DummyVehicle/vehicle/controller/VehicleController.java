package com.example.test.jenskin.CICD.DummyVehicle.vehicle.controller;



import com.example.test.jenskin.CICD.DummyVehicle.vehicle.model.VehicleModel;
import com.example.test.jenskin.CICD.DummyVehicle.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:5173")             //for React API call.
@RequestMapping("/api/v1/public/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Get all Mercedes vehicles
    @GetMapping
    public List<VehicleModel> getAll() {
        return vehicleService.getAllVehicles();
    }





}
