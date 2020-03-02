package com.thoughtworks.parkinglot.application;

import com.thoughtworks.parkinglot.domain.Car;
import com.thoughtworks.parkinglot.domain.ParkingLotService;
import com.thoughtworks.parkinglot.domain.Ticket;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotApplicationService {
  private final ParkingLotService parkingLotService;

  public ParkingLotApplicationService(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  public Ticket parkCar(Id parkingLotId, Car car) {
    return parkingLotService.parkCar(parkingLotId, car);
  }

  public Car getCar(Id id, Ticket ticket) {
    return parkingLotService.getCar(id, ticket);
  }
}
