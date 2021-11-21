package com.freenow.service.car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.Manufacturer;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
public class DefaultCarService implements CarService
{

    private static final Logger LOG = LoggerFactory.getLogger(com.freenow.service.car.DefaultCarService.class);

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given username, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
    }

    /**
     * Updates the selected car with a make and model of the manufacturer.
     *
     * @param carId
     * @param manufacturerName
     * @param manufacturerModel
     */
    @Override
    @Transactional
    public void updateManufacturer(long carId, String manufacturerName, String manufacturerModel) throws EntityNotFoundException {
        CarDO carDO = findCarChecked(carId);
        carDO.setManufacturer(new Manufacturer(manufacturerName, manufacturerModel));
    }

    @Override
    public Collection<CarDO> findFreeCars() {
        return carRepository.findByIsBeingUsed(false);
    }

    /**
     *
     * Used to check if car exists
     *
     * @param carId
     * @return
     * @throws EntityNotFoundException
     */
    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

}
