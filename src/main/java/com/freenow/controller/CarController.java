package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("v1/cars")
//@PreAuthorize("hasRole('MODERATOR')")
public class CarController
{

    private final CarService carService;


    @Autowired
    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @GetMapping("/findFreeCars")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<CarDTO> findFreeCars() throws EntityNotFoundException
    {
        return CarMapper.makeCarDTOList(carService.findFreeCars());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }

    @PutMapping("/{carId}")
    public void updateLocation(
            @PathVariable long carId, @RequestParam String manufacturerName, @RequestParam String manufacturerModel)
            throws EntityNotFoundException
    {
        carService.updateManufacturer(carId, manufacturerName, manufacturerModel);
    }


}
