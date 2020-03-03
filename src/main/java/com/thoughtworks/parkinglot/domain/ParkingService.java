package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import com.thoughtworks.parkinglot.domain.exception.AllParkingLotNoSpaceException;
import com.thoughtworks.parkinglot.domain.exception.ParkingLotNoSpaceException;
import com.thoughtworks.parkinglot.infra.repo.ParkingBoyRepository;
import com.thoughtworks.parkinglot.infra.repo.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

  private final ParkingLotRepository parkingLotRepository;
  private final ParkingBoyRepository broRepository;

  public ParkingService(
      ParkingLotRepository parkingLotRepository, ParkingBoyRepository broRepository) {
    this.parkingLotRepository = parkingLotRepository;
    this.broRepository = broRepository;
  }

  public Ticket parkCarByParkingLot(Id parkingLotId, Car car) {
    ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(parkingLotId);
    return parkingLot.park(car);
  }

  public Car getCar(Id id, Ticket ticket) {
    ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(id);
    return parkingLot.getCar(ticket);
  }

  public Ticket parkCarByBro(Id broId, Car car) {
    ParkingBoy parkingBoy = broRepository.queryBroById(broId);
    for (Id lotId : parkingBoy.getParkingLotId()) {
      ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(lotId);
      try {
        return parkingLot.park(car);
      } catch (ParkingLotNoSpaceException ignored) {

      }
    }
    throw new AllParkingLotNoSpaceException();
  }
}
