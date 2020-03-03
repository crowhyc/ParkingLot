package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import lombok.Getter;

@Getter
public class Car {
  private String plateNumber;

  public Car(String plateNumber) {
    this.plateNumber = plateNumber;
  }
}
