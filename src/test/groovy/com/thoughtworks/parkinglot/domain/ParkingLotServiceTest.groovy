package com.thoughtworks.parkinglot.domain

import com.thoughtworks.parkinglot.domain.concepts.Id
import com.thoughtworks.parkinglot.domain.exception.ParkingLotNoSpaceException
import com.thoughtworks.parkinglot.infra.ParkingLotRepository
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.*

class ParkingLotServiceTest extends Specification {

    ParkingLotRepository parkingLotRepository = Mock(ParkingLotRepository.class)
    ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository)


    def 'should return a ticket when park a car'() {
        given:
        def parkingLot = new ParkingLot()
        def parkingLotId = parkingLot.getParkingLotId()
        def car = new Car()
        parkingLotRepository.queryParkingLotById(parkingLotId) >> parkingLot
        when:
        def ticket = parkingLotService.parkCar(parkingLotId, car)
        then:
        assertNotNull(ticket)
        assertEquals(1, parkingLot.getParkCars().size())

    }

    def "should return car when get car with valid ticket"() {
        given:
        def parkingLot = Mock(ParkingLot)
        def ticket = new Ticket(Id.getNewId())
        parkingLotRepository.queryParkingLotById(parkingLot.getParkingLotId()) >> parkingLot
        parkingLot.getCar(ticket) >> new Car()
        when:
        def car = parkingLotService.getCar(parkingLot.getParkingLotId(), ticket)
        then:
        assertNotNull(car)
    }

    def "should return null when get car with invalid ticket"() {
        given:
        def parkingLot = Mock(ParkingLot)
        def ticket = new Ticket(Id.getNewId())
        parkingLotRepository.queryParkingLotById(parkingLot.getParkingLotId()) >> parkingLot
        parkingLot.getCar(ticket) >> null
        when:
        def car = parkingLot.getCar(ticket)
        then:
        assertNull(car)
    }
    def "should return null when park car over limit"(){
        given:
        def lot = new ParkingLot(0)

        parkingLotRepository.queryParkingLotById(lot.getParkingLotId()) >> lot
        when:
        parkingLotService.parkCar(lot.getParkingLotId(), new Car())

        then:
        thrown ParkingLotNoSpaceException
    }
}
