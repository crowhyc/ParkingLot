package com.thoughtworks.parkinglot.domain

import com.thoughtworks.parkinglot.domain.concepts.Id
import com.thoughtworks.parkinglot.domain.exception.AllParkingLotNoSpaceException
import com.thoughtworks.parkinglot.domain.exception.ParkingLotNoSpaceException
import com.thoughtworks.parkinglot.infra.repo.ParkingLittleBroRepository
import com.thoughtworks.parkinglot.infra.repo.ParkingLotRepository
import spock.lang.Specification

import static com.google.common.collect.Lists.newArrayList
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertNull

class ParkingLittleBroTest extends Specification {

    ParkingLittleBroRepository broRepository = Mock(ParkingLittleBroRepository)
    ParkingLotRepository parkingLotRepository = Mock(ParkingLotRepository)
    ParkingService parkingService = new ParkingService(parkingLotRepository, broRepository)


    def "should return ticket when park car success"() {
        given:
        def bro = new ParkingLittleBro(newArrayList(Id.getNewId()))
        broRepository.queryBroById(_) >> bro
        def parkingLot = new ParkingLot(1)
        parkingLotRepository.queryParkingLotById(_)>>parkingLot
        when:
        def ticket = parkingService.parkCarByBro(Id.getNewId(), new Car())

        then:
        assertNotNull(ticket)
    }
    def "should park car at second parkingLot when first parkingLot no space"() {
        given:
        def first = new ParkingLot(0)
        def second = new ParkingLot(1)


        def bro = new ParkingLittleBro(newArrayList(first.getParkingLotId(),second.getParkingLotId()))
        broRepository.queryBroById(_) >> bro
        parkingLotRepository.queryParkingLotById(first.getParkingLotId())>>first
        parkingLotRepository.queryParkingLotById(second.getParkingLotId())>>second
        when:
        parkingService.parkCarByBro(Id.getNewId(), new Car())
        then:
        assertEquals(1, second.getParkCars().size())

    }
    def "should throw exception when all parkingLot no space"() {
        given:
        def bro = new ParkingLittleBro(newArrayList(Id.getNewId()))
        broRepository.queryBroById(_) >> bro
        def parkingLot = new ParkingLot(0)
        parkingLotRepository.queryParkingLotById(_)>>parkingLot
        when:
         parkingService.parkCarByBro(Id.getNewId(), new Car())
        then:
        thrown AllParkingLotNoSpaceException
    }
    def 'should return a ticket when park a car'() {
        given:
        def parkingLot = new ParkingLot(1)
        def parkingLotId = parkingLot.getParkingLotId()
        def car = new Car()
        parkingLotRepository.queryParkingLotById(parkingLotId) >> parkingLot
        when:
        def ticket = parkingService.parkCarByParkingLot(parkingLotId, car)
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
        def car = parkingService.getCar(parkingLot.getParkingLotId(), ticket)
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
        parkingService.parkCarByParkingLot(lot.getParkingLotId(), new Car())

        then:
        thrown ParkingLotNoSpaceException
    }
}
