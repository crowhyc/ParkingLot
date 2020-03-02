package com.thoughtworks.parkinglot.domain

import com.thoughtworks.parkinglot.domain.concepts.Id
import com.thoughtworks.parkinglot.domain.exception.AllParkingLotNoSpaceException
import com.thoughtworks.parkinglot.infra.ParkingLittleBroRepository
import com.thoughtworks.parkinglot.infra.ParkingLotRepository
import spock.lang.Specification

import static com.google.common.collect.Lists.newArrayList
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNotNull

class ParkingLittleBroTest extends Specification {

    ParkingLittleBroRepository broRepository = Mock(ParkingLittleBroRepository)
    ParkingLotRepository parkingLotRepository = Mock(ParkingLotRepository)
    ParkingLittleBroService parkingLittleBroService = new ParkingLittleBroService(parkingLotRepository, broRepository)


    def "should return ticket when park car success"() {
        given:
        def bro = new ParkingLittleBro(newArrayList(Id.getNewId()))
        broRepository.queryBroById(_) >> bro
        def parkingLot = new ParkingLot(1)
        parkingLotRepository.queryParkingLotById(_)>>parkingLot
        when:
        def ticket = parkingLittleBroService.parkCar(Id.getNewId(), new Car())

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
        parkingLittleBroService.parkCar(Id.getNewId(), new Car())
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
         parkingLittleBroService.parkCar(Id.getNewId(), new Car())
        then:
        thrown AllParkingLotNoSpaceException
    }
}
