package com.thoughtworks.parkinglot.infra.repo;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.parkinglot.domain.ParkingLittleBro;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParkingLittleBroRepository {
  private Map<Id, ParkingLittleBro> broStorage;

  public ParkingLittleBroRepository() {
    this.broStorage = newHashMap();
    broStorage.put(
        new Id("1"), new ParkingLittleBro(newArrayList(new Id("1"), new Id("2"), new Id("3"))));
  }

  public ParkingLittleBro queryBroById(Id broId) {
    return broStorage.get(broId);
  }

  public void insertBro(ParkingLittleBro bro) {
    broStorage.put(bro.getId(), bro);
  }
}
