package com.freenow.domainobject;

import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;


@Entity
@Table(
        name = "car"
)
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Seat count can not be null!")
    private Integer seatCount;

    @Column(nullable = false)
    private Boolean convertible = false;

    @Column(columnDefinition="tinyint(0) default 0")
    private Boolean isBeingUsed = false;

    @Column(nullable = false)
    private Integer rating;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();

    @Embedded
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToOne(mappedBy = "carDO")
    private DriverDO driverDo;


    public CarDO(Long id, String licensePlate, Integer seatCount, Boolean convertible, Boolean isBeingUsed, Integer rating, ZonedDateTime dateCoordinateUpdated, Manufacturer manufacturer, EngineType engineType, Boolean deleted, DriverDO driverDo) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.isBeingUsed = isBeingUsed;
        this.rating = rating;
        this.dateCoordinateUpdated = dateCoordinateUpdated;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.deleted = deleted;
        this.driverDo = driverDo;
    }

    public CarDO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getBeingUsed() {
        return isBeingUsed;
    }

    public void setBeingUsed(Boolean beingUsed) {
        isBeingUsed = beingUsed;
    }
}
