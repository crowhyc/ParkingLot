package com.thoughtworks.parkinglot.infra.repo;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import com.thoughtworks.parkinglot.domain.ParkingBoy;
import com.thoughtworks.parkinglot.domain.concepts.Id;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ParkingBoyRepository {
  private Map<Id, ParkingBoy> broStorage;

  public ParkingBoyRepository() {
    this.broStorage = newHashMap();
    broStorage.put(
        new Id("1"), new ParkingBoy(newArrayList(new Id("1"), new Id("2"), new Id("3"))));
  }

  public ParkingBoy queryBroById(Id broId) {
    return broStorage.get(broId);
  }

  public void insertBro(ParkingBoy bro) {
    broStorage.put(bro.getId(), bro);
  }
}
