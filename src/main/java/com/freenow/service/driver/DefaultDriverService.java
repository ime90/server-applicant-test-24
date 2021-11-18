package com.freenow.service.driver;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final CarRepository carRepository;


    public DefaultDriverService(final DriverRepository driverRepository, CarRepository carRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }

    /**
     * Give a driver a car
     *
     * @param driverId
     * @param carId
     */
    @Override
    @Transactional
    public void giveDriverCar(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException, DriverIsNotOnlineException {
        DriverDO driverDO = getDriverDO(driverId);
        CarDO carDO = findCarChecked(carId);

        if (!carDO.getBeingUsed()) {
            driverDO.setCarDO(carDO);
            carDO.setBeingUsed(true);
        } else {
            throw new CarAlreadyInUseException("Car with id " + carId + " is already in use");
        }

    }


    /**
     * Remove driver from car
     *
     * @param driverId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void removeDriverFromCar(long driverId) throws EntityNotFoundException, DriverHasNoCarException, DriverIsNotOnlineException {
        DriverDO driverDO = getDriverDO(driverId);

        if (driverDO.getCarDO() != null) {
            CarDO carDO = findCarChecked(driverDO.getCarDO().getId());

            driverDO.setCarDO(null);
            carDO.setBeingUsed(false);
        } else {
            throw new DriverHasNoCarException("Driver with id " + driverId + " has no car");
        }
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    @Override
    public DriverDO findByUsername(String driverName) {
        return driverRepository.findByUsername(driverName);
    }

    @Override
    public DriverDO findByLicensePlate(String licensePlate) {
        return driverRepository.findByCarDOLicensePlate(licensePlate);
    }

    @Override
    public List<DriverDO> findDriversByRating(Integer rating) {
        return driverRepository.findByCarDO_Rating(rating);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

    private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

    private DriverDO getDriverDO(long driverId) throws EntityNotFoundException, DriverIsNotOnlineException {
        DriverDO driverDO = findDriverChecked(driverId);
        if (driverDO.getOnlineStatus() == OnlineStatus.OFFLINE) {
            throw new DriverIsNotOnlineException("Driver with id " + driverId + " is not online");
        }
        return driverDO;
    }


}
