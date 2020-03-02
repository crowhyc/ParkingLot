package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import com.thoughtworks.parkinglot.infra.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

  private final ParkingLotRepository parkingLotRepository;

  public ParkingLotService(ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public Ticket parkCar(Id parkingLotId, Car car) {
    ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(parkingLotId);
    return parkingLot.park(car);
  }

  public Car getCar(Id id, Ticket ticket) {
    ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(id);
    return parkingLot.getCar(ticket);
  }
}
