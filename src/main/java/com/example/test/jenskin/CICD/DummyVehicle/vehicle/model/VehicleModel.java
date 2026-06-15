package com.example.test.jenskin.CICD.DummyVehicle.vehicle.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "vehicles", schema = "public")
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data // Generates getters, setters, toString, equals, and hashCode via Lombok
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "v_identifier", length = 10, nullable = false, unique = true)
    private String vIdentifier;

    @Column(name = "model_name", length = 50, nullable = false)
    private String modelName;

    @Column(name = "model_year", nullable = false)
    private Integer modelYear;

    @Column(name = "series", length = 30)
    private String series;

    @Column(name = "mileage")
    private Integer mileage = 0;

    @Column(name = "vehicle_type", length = 30)
    private String vehicleType;

    @Column(name = "engine", length = 50)
    private String engine;

    @Column(name = "horse_power")
    private Integer horsePower;

    @Column(name = "engine_type", length = 20)
    private String engineType;

    @Column(name = "status", length = 20)
    private String status = "Active";

    @Column(name = "launch_date")
    private LocalDate launchDate;

    @Column(name = "life_time")
    private Integer lifeTime;

    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = ZonedDateTime.now();
        }
    }




//   -- Write without Lambok -> All arg and no arg constructor, Getter Setter --


}
