package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.List;
import lombok.Getter;

@Getter
public class ParkingBoy {

  private Id id;
  private List<Id> parkingLotId;

  public ParkingBoy(List<Id> parkingLotId) {
    this.id = Id.getNewId();
    this.parkingLotId = parkingLotId;
  }
}
