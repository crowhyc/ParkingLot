package com.thoughtworks.parkinglot.domain;

import static com.google.common.collect.Lists.newArrayList;

import com.thoughtworks.parkinglot.domain.concepts.Id;
import com.thoughtworks.parkinglot.domain.exception.ParkingLotNoSpaceException;
import java.util.List;
import lombok.Getter;

@Getter
public class ParkingLot {
  private Id parkingLotId;
  private int limit;
  private List<Car> parkCars = newArrayList();
  private List<Ticket> parkCarTickets = newArrayList();

  public ParkingLot(int limit) {
    this.limit = limit;
    this.parkingLotId = Id.getNewId();
  }

  public Ticket park(Car car) {
    if (parkCars.size() >= limit) {
      throw new ParkingLotNoSpaceException();
    }
    Ticket ticket = Ticket.getNewTicket(car.getPlateNumber());
    parkCars.add(car);
    parkCarTickets.add(ticket);
    return ticket;
  }

  public Car getCar(Ticket ticket) {

    boolean isValidTicket =
        parkCarTickets.stream()
                .filter(t -> t.getTicketId().getId().equals(ticket.getTicketId().getId()))
                .count()
            == 1;
    if (!isValidTicket) {
      return null;
    }
    return parkCars.stream()
        .filter(c -> c.getPlateNumber().equals(ticket.getPlateNumber()))
        .findFirst()
        .get();
  }
}
