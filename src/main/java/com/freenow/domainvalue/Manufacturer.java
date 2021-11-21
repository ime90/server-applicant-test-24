package com.freenow.domainvalue;

import javax.persistence.*;

@Embeddable
@AttributeOverrides({
        @AttributeOverride( name = "name", column = @Column(name = "manufacturer_name")),
        @AttributeOverride( name = "model", column = @Column(name = "manufacturer_model")),
})
public class Manufacturer {



    @Column(name = "manufacturer_name")
    private String name;
    @Column(name = "manufacturer_model")
    private String model;

    public Manufacturer(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public Manufacturer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
