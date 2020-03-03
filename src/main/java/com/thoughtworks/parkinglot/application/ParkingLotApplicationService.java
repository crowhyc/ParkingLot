package com.thoughtworks.parkinglot.application;

import com.thoughtworks.parkinglot.domain.Car;
import com.thoughtworks.parkinglot.domain.ParkingService;
import com.thoughtworks.parkinglot.domain.Ticket;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotApplicationService {
  private final ParkingService parkingService;

  public ParkingLotApplicationService(
      ParkingService parkingService) {
    this.parkingService = parkingService;
  }


  public Ticket parkCar(Id parkingLotId, Car car) {
    return parkingService.parkCarByBro(parkingLotId, car);
  }

  public Car getCar(Id id, Ticket ticket) {
    return parkingService.getCar(id, ticket);
  }
}
