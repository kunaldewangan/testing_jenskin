package com.example.test.jenskin.CICD.DummyVehicle.vehicle.others;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/")
    public String index()
    {
        return "Hi there.... Welcome to the App!";
    }


    @GetMapping("/welcome")
    public String welcome()
    {
        return "Hi there, This is a Welcome API for the Application....";
    }

}
