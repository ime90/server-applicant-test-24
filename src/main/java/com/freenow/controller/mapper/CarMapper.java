package com.freenow.controller.mapper;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarDO makeCarDO(CarDTO carDTO) {
        CarDO carDO = new CarDO();
        carDO.setId(carDTO.getId());
        carDO.setLicensePlate(carDTO.getLicensePlate());
        carDO.setSeatCount(carDTO.getSeatCount());
        carDO.setConvertible(carDTO.getIsConvertible());
        carDO.setManufacturer(carDTO.getManufacturer());
        carDO.setRating(carDTO.getRating());
        carDO.setEngineType(carDTO.getEngineType());
        carDO.setBeingUsed((carDTO.getBeingUsed()));
        return carDO;
    }


    public static CarDTO makeCarDTO(CarDO carDO) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carDO.getId());
        carDTO.setLicensePlate(carDO.getLicensePlate());
        carDTO.setSeatCount(carDO.getSeatCount());
        carDTO.setIsConvertible(carDO.getConvertible());
        carDTO.setManufacturer(carDO.getManufacturer());
        carDTO.setRating(carDO.getRating());
        carDTO.setEngineType(carDO.getEngineType());
        carDTO.setBeingUsed((carDO.getBeingUsed()));
        return carDTO;
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> Cars) {
        return Cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}

    

