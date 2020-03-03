package com.thoughtworks.parkinglot.infra.repo;

import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.parkinglot.domain.ParkingLot;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotRepository {
  private Map<Id, ParkingLot> parkingLotStorage = newHashMap();

  public ParkingLot queryParkingLotById(Id id) {
    return parkingLotStorage.get(id);
  }

}
