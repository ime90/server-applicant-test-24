package com.freenow.controller;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.dataaccessobject.CustomDriverRepository;
import com.freenow.dataaccessobject.DriverSpecification;
import com.freenow.datatransferobject.DriverDTO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;
import com.freenow.helper.DriverDORequest;
import com.freenow.service.driver.DriverService;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;

    private final CustomDriverRepository customDriverRepository;




    @Autowired
    public DriverController(final DriverService driverService, CustomDriverRepository customDriverRepository)
    {
        this.driverService = driverService;
        this.customDriverRepository = customDriverRepository;
    }



    @GetMapping("/getDrivers")
    public List<DriverDO> getUsersList(@Valid @RequestBody DriverDORequest request) {
        DriverSpecification driverSpecification = new DriverSpecification(request);
        return customDriverRepository.findAll(driverSpecification);
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
