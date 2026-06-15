package com.example.test.jenskin.CICD.DummyVehicle.vehicle.repository;



import com.example.test.jenskin.CICD.DummyVehicle.vehicle.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {


}
