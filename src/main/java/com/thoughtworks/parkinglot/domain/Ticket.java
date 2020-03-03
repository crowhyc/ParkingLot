package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import lombok.Getter;

@Getter
public class Ticket {
  private Id ticketId;
  private String plateNumber;

  public Ticket(String plateNumber) {
    this.ticketId = Id.getNewId();
    this.plateNumber = plateNumber;
  }

  public static Ticket getNewTicket(String plateNumber) {
    Ticket ticket = new Ticket(plateNumber);
    ticket.ticketId = Id.getNewId();
    return ticket;
  }
}
