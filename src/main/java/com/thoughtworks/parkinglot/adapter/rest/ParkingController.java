package com.thoughtworks.parkinglot.adapter.rest;

import com.thoughtworks.parkinglot.application.ParkingApplicationService;
import com.thoughtworks.parkinglot.domain.Car;
import com.thoughtworks.parkinglot.domain.Ticket;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking/")
public class ParkingController {
  private final ParkingApplicationService parkingApplicationService;

  public ParkingController(ParkingApplicationService parkingApplicationService) {
    this.parkingApplicationService = parkingApplicationService;
  }

  @PostMapping("/bro/{id}")
  public Ticket parkCarByBro(@PathVariable(value = "id") Id id, @RequestBody Car car) {
    return parkingApplicationService.parkCarByBro(id, car);
  }

  @PostMapping("/parkingLot/{id}")
  public Ticket parkCarByParkingLot(@PathVariable(value = "id") Id id, @RequestBody Car car) {
    return parkingApplicationService.parkCarByParkingLot(id, car);
  }

  @DeleteMapping("/parkingLot/{id}")
  public Car parkCarByParkingLot(@PathVariable(value = "id") Id id, @RequestBody Ticket ticket) {
    return parkingApplicationService.getCar(id, ticket);
  }
}
