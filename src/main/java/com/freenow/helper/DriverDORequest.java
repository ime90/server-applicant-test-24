package com.freenow.helper;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.OnlineStatus;

public class DriverDORequest {

    private String username;
    private OnlineStatus onlineStatus;
    private CarDO carDO;


    public DriverDORequest(String username, OnlineStatus onlineStatus, CarDO carDO) {
        this.username = username;
        this.onlineStatus = onlineStatus;
        this.carDO = carDO;
    }

    public DriverDORequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public CarDO getCarDO() {
        return carDO;
    }

    public void setCarDO(CarDO carDO) {
        this.carDO = carDO;
    }
}
