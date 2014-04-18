package com.example.car;

/**
 * Created by mattschoen on 4/14/14.
 */
public class Car {

    private String mManufacturer=null;
    private String mModel=null;
    private String mType=null;

    public Car(String manufacturer, String model, String type) {
        mManufacturer = manufacturer;
        mModel = model;
        mType = type;
    }

    public String getManufacturer() {
        return mManufacturer ;
    }

    public String getModel() {
        return mModel;
    }

    public String getType() {
        return mType;
    }
}

