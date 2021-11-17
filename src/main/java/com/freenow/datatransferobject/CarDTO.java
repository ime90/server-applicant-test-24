package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.EngineType;
import com.freenow.domainvalue.Manufacturer;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {


    private Long id;

    @NotNull(message = "License plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Seat count can not be null!")
    private Integer seatCount;

    private Boolean isConvertible;

    private Integer rating;

    private Manufacturer manufacturer;

    private EngineType engineType;

    private Boolean isBeingUsed;


    public CarDTO() {
    }

    public CarDTO(Long id, String licensePlate, Integer seatCount, Boolean isConvertible, Integer rating, Manufacturer manufacturer, EngineType engineType, Boolean isBeingUsed) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.isConvertible = isConvertible;
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.isBeingUsed = isBeingUsed;
    }

    @JsonProperty
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

    public Boolean getIsConvertible() {
        return isConvertible;
    }

    public void setIsConvertible(Boolean isConvertible) {
        this.isConvertible = isConvertible;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Boolean getBeingUsed() {
        return isBeingUsed;
    }

    public void setBeingUsed(Boolean beginUsed) {
        isBeingUsed = beginUsed;
    }
}
