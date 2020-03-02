package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import lombok.Getter;

@Getter
public class Car {
  private Id carId;

  public Car() {
    carId = Id.getNewId();
  }
}
