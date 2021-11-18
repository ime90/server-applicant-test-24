package com.freenow.controller;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;
import com.freenow.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;


    @Autowired
    public DriverController(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @GetMapping("findByUsername/{driverName}")
    public DriverDTO findByUsername(@RequestParam String driverName)
    {
        return DriverMapper.makeDriverDTO(driverService.findByUsername(driverName));
    }

    @GetMapping("findByLicensePlate/{licensePlate}")
    public DriverDTO findByLicensePlate(@RequestParam String licensePlate)
    {
        return DriverMapper.makeDriverDTO(driverService.findByLicensePlate(licensePlate));
    }

    @GetMapping("findDriversByRating/{rating}")
    public List<DriverDTO> findDriversByRating(@RequestParam Integer rating)
    {
        return DriverMapper.makeDriverDTOList(driverService.findDriversByRating(rating));
    }


    @GetMapping("giveDriverCar/{driverId}/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void giveDriverCar(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException, CarAlreadyInUseException, DriverIsNotOnlineException {
        driverService.giveDriverCar(driverId, carId);
    }

    @GetMapping("removeDriverFromCar/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDriverFromCar(@PathVariable long driverId) throws EntityNotFoundException, DriverHasNoCarException, DriverIsNotOnlineException {
        driverService.removeDriverFromCar(driverId);
    }

}
