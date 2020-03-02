package com.thoughtworks.parkinglot.infra;

import com.thoughtworks.parkinglot.domain.ParkingLittleBro;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParkingLittleBroRepository {
  private Map<Id, ParkingLittleBro> broStorage;

  public ParkingLittleBro queryBroById(Id broId) {
    return broStorage.get(broId);
  }

  public void insertBro(ParkingLittleBro bro) {
    broStorage.put(bro.getId(), bro);
  }
}
