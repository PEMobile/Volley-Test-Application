package com.example.listeners;

/**
 * Created by mattschoen on 4/14/14.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.volley.Response.Listener;

import com.example.car.Car;

public class ManufacturerModelsListener extends Observable implements Listener<String> {

    private static final String LOG_TAG = "ManufacturerModelsListener";
    private List<Car> mCarList=null;


    public ManufacturerModelsListener() {
        mCarList = new LinkedList<Car>();
    }

    public List<Car> getCarList() {
        return mCarList;
    }

    @Override
    public void onResponse(String response) {
        Car car=null;

        try {
            JSONArray jsonCarsArray = new JSONArray(response);

            for(int counter=0; counter<jsonCarsArray.length(); counter++) {
                JSONObject carItem = jsonCarsArray.getJSONObject(counter);

                car = new Car(carItem.getString("Manufacturer"), carItem.getString("Model"), carItem.getString("Type"));
                mCarList.add(car);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // notify the observer
        this.setChanged();
        this.notifyObservers(response);
    }

}
