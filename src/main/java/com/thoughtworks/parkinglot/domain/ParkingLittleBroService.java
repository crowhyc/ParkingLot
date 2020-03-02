package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import com.thoughtworks.parkinglot.domain.exception.ParkingLotNoSpaceException;
import com.thoughtworks.parkinglot.domain.exception.AllParkingLotNoSpaceException;
import com.thoughtworks.parkinglot.infra.ParkingLittleBroRepository;
import com.thoughtworks.parkinglot.infra.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLittleBroService {

  private final ParkingLotRepository parkingLotRepository;
  private final ParkingLittleBroRepository broRepository;

  public ParkingLittleBroService(
      ParkingLotRepository parkingLotRepository, ParkingLittleBroRepository broRepository) {
    this.parkingLotRepository = parkingLotRepository;
    this.broRepository = broRepository;
  }

  public Ticket parkCar(Id broId, Car car) {
      ParkingLittleBro parkingLittleBro = broRepository.queryBroById(broId);
    for (Id lotId:parkingLittleBro.getParkingLotId()) {
      ParkingLot parkingLot = parkingLotRepository.queryParkingLotById(lotId);
      try{
        return parkingLot.park(car);
      }catch (ParkingLotNoSpaceException ignored){

      }
    }
    throw new AllParkingLotNoSpaceException();
  }
}
