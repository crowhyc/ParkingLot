package com.thoughtworks.parkinglot.domain.concepts;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Id {

  private String id;

  public Id() {
  }

  public Id(String id) {
    this.id = id;
  }

  public static Id getNewId() {
    Id id = new Id();
    id.id = UUID.randomUUID().toString();
    return id;
  }
}
