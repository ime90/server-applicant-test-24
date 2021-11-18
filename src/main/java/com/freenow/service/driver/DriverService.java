package com.freenow.service.driver;

import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;

import java.util.Collection;
import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    void giveDriverCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException, DriverIsNotOnlineException;

    void removeDriverFromCar(long driverId) throws EntityNotFoundException, DriverHasNoCarException, DriverIsNotOnlineException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO findByUsername(String driverName);

    DriverDO findByLicensePlate(String licensePlate);

    List<DriverDO> findDriversByRating(Integer rating);
}
