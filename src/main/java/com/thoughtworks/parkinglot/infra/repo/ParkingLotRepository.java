package com.thoughtworks.parkinglot.infra.repo;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.parkinglot.domain.ParkingLot;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotRepository {
  private Map<Id, ParkingLot> parkingLotStorage;

  public ParkingLotRepository() {
      this.parkingLotStorage=newHashMap();
    parkingLotStorage.put(new Id("1"), new ParkingLot(1));
    parkingLotStorage.put(new Id("2"), new ParkingLot(2));
    parkingLotStorage.put(new Id("3"), new ParkingLot(3));
  }

  public ParkingLot queryParkingLotById(Id id) {
    return parkingLotStorage.get(id);
  }

}
