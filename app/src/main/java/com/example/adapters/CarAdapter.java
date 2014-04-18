package com.example.adapters;

/**
 * Created by mattschoen on 4/14/14.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.car.Car;
import com.example.astestrestfulservices.app.R;

public class CarAdapter extends BaseAdapter {
    Context mContext=null;
    List<Car> mCarList=null;
    private static LayoutInflater mInflater=null;

    public CarAdapter(Context context, List<Car> cars) {
        mContext = context;
        mCarList=cars;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mCarList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mCarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = mInflater.inflate(R.layout.list_car_item, null);
        TextView manufacturerTv = (TextView) vi.findViewById(R.id.manufacturerTv);
        TextView modelTv = (TextView) vi.findViewById(R.id.modelTv);
        TextView typeTv = (TextView) vi.findViewById(R.id.typeTv);

        Car car = mCarList.get(position);
        manufacturerTv.setText(car.getManufacturer());
        modelTv.setText(car.getModel());
        typeTv.setText(car.getType());
        return vi;
    }

}