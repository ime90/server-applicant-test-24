package com.freenow;


import com.freenow.dataaccessobject.CarRepository;
import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.DriverHasNoCarException;
import com.freenow.exception.DriverIsNotOnlineException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.DefaultCarService;
import com.freenow.service.driver.DefaultDriverService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreeNowServerApplicantTestApplication.class)
public class DriverTest {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    DefaultDriverService driverService;

    @Autowired
    DefaultCarService carService;


    /**
     * ##Task1
     * Test for checking if adding a driver to an empty car will result in a full car and a driver with a car. At the start of the test we check if the car is empty and the
     * driver is without a car. After calling the "giveDriverCar" method, we do the opposite checks.
     *
     * @throws SQLException
     * @throws CarAlreadyInUseException
     * @throws EntityNotFoundException
     * @throws DriverIsNotOnlineException
     */
    @Test
    @Transactional
    public void addDriverToEmptyCar_assertFullCarAfter() throws SQLException, CarAlreadyInUseException, EntityNotFoundException, DriverIsNotOnlineException {

        DriverDO driver = driverRepository.findById(4L).get();
        CarDO freeCar = carRepository.findById(2L).get();

        Assert.assertNull(driver.getCarDO());
        Assert.assertFalse(freeCar.getBeingUsed());

        driverService.giveDriverCar(4L, 2L);

        Assert.assertNotNull(driver.getCarDO());
        Assert.assertTrue(freeCar.getBeingUsed());
    }


    /**
     * ##Task1
     * Test for checking the "removeDriverFromCar" method. At the start we check if the driver has a car and the car is being used. After the method is called we check
     * the opposite.
     *
     * @throws SQLException
     * @throws CarAlreadyInUseException
     * @throws EntityNotFoundException
     * @throws DriverIsNotOnlineException
     * @throws DriverHasNoCarException
     */
    @Test
    @Transactional
    public void removeDriverFromCar_assertEmptyCarAfter() throws SQLException, CarAlreadyInUseException, EntityNotFoundException, DriverIsNotOnlineException, DriverHasNoCarException {

        DriverDO driver = driverRepository.findById(9L).get();
        CarDO takenCar = carRepository.findById(1L).get();

        Assert.assertNotNull(driver.getCarDO());
        Assert.assertTrue(takenCar.getBeingUsed());

        driverService.removeDriverFromCar(9L);

        Assert.assertNull(driver.getCarDO());
        Assert.assertFalse(takenCar.getBeingUsed());
    }

    /**
     * ##Task1
     * A driver has a car, so that car is taken. After giving the driver another, free car, the taken car should be freed and the free car should be taken.
     * @throws CarAlreadyInUseException
     * @throws EntityNotFoundException
     * @throws DriverIsNotOnlineException
     */
    @Test
    @Transactional
    public void driverAlreadyHasCar_giveHimOtherCar() throws CarAlreadyInUseException, EntityNotFoundException, DriverIsNotOnlineException {
        DriverDO driver = driverRepository.findById(9L).get();
        CarDO takenCar = carRepository.findById(1L).get();
        CarDO freeCar = carRepository.findById(2L).get();

        Assert.assertNotNull(driver.getCarDO());
        Assert.assertTrue(takenCar.getBeingUsed());
        Assert.assertFalse(freeCar.getBeingUsed());

        driverService.giveDriverCar(9L, 2L);

        Assert.assertFalse(takenCar.getBeingUsed());
        Assert.assertTrue(freeCar.getBeingUsed());

    }

    /**
     * ##Task2
     * We are checking if trying to give a car that is being used to a driver will result in a CarAlreadyInUseException. At the start we check that a car is being used.
     * Then we check if the exception we throw will result in a appropriate message.
     */
    @Test
    public void onlyOneDriverPerCarExceptionCheck(){
        CarDO freeCar = carRepository.findById(1L).get();

        Assert.assertTrue(freeCar.getBeingUsed());

        Exception exception = Assert.assertThrows(CarAlreadyInUseException.class, () -> {
            driverService.giveDriverCar(4L, 1L);
        });

        String expectedMessage = "Car with id 1 is already in use";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }


}