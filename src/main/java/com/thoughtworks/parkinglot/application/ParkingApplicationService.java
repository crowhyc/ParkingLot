package com.thoughtworks.parkinglot.application;

import com.thoughtworks.parkinglot.domain.Car;
import com.thoughtworks.parkinglot.domain.ParkingService;
import com.thoughtworks.parkinglot.domain.Ticket;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import org.springframework.stereotype.Component;

@Component
public class ParkingApplicationService {
  private final ParkingService parkingService;

  public ParkingApplicationService(ParkingService parkingService) {
    this.parkingService = parkingService;
  }

  public Ticket parkCarByBro(Id broId, Car car) {
    return parkingService.parkCarByBro(broId, car);
  }

  public Ticket parkCarByParkingLot(Id parkingLotId, Car car) {
    return parkingService.parkCarByParkingLot(parkingLotId, car);
  }

  public Car getCar(Id id, Ticket ticket) {
    return parkingService.getCar(id, ticket);
  }
}
