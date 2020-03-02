package com.thoughtworks.parkinglot.domain;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import lombok.Getter;

@Getter
public class Ticket {
  private Id ticketId;
  private Id carId;

  public Ticket(Id carId) {
    this.ticketId = Id.getNewId();
    this.carId = carId;
  }

  public static Ticket getNewTicket(Id carId) {
    Ticket ticket = new Ticket(carId);
    ticket.ticketId = Id.getNewId();
    return ticket;
  }
}
