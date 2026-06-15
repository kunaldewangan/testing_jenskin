package com.example.test.jenskin.CICD.DummyVehicle.vehicle.others;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Errors {

    @GetMapping("/error")
    public String error_API()
    {
        return "This is Error API...";
    }

}
