package com.example.astestrestfulservices.app;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapters.CarAdapter;
import com.example.car.Car;
import com.example.listeners.ManufacturerModelsListener;
import com.example.listeners.TypeModelsListener;
import com.example.listeners.WebServiceErrorListener;
import com.example.restfulrequest.GetManufacturerModelsRequest;
import com.example.restfulrequest.GetTypeModelsRequest;

public class MainActivity extends Activity implements OnClickListener {

    private static final String LOG_TAG="MainActivity";
    private EditText mEditText=null;
    private Activity mActivity=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendQueryByManufacturerBtn = (Button) findViewById(R.id.query_manufacturer_btn);
        sendQueryByManufacturerBtn.setOnClickListener(this);

        Button sendQueryByTypeBtn = (Button) findViewById(R.id.query_type_btn);
        sendQueryByTypeBtn.setOnClickListener(this);

        mEditText = (EditText) findViewById(R.id.queryStringEditTxt);
        mActivity=this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {
        String queryStr = this.mEditText.getText().toString();
        Log.d(LOG_TAG, "Query with string: " + queryStr);
        if(queryStr == null || queryStr.equals("")) {
            Log.e(LOG_TAG, "Must provide query string");
            return;
        }

        if(v.getId() == R.id.query_manufacturer_btn) {
            Log.d(LOG_TAG, "Query by manufacturer clicked");

            // create observable objects for manufacturer models data
            ManufacturerModelsListener manufacturerModelsSuccessObservable = new ManufacturerModelsListener();
            WebServiceErrorListener agentsResponseErrorObservable = new WebServiceErrorListener();

            // create observer objects
            final ManufacturerModelsSuccessObserver manufacturerModelsSuccessObserver = new ManufacturerModelsSuccessObserver();
            final ManufacturerModelsFailureObserver manufacturerModelsFailureObserver = new ManufacturerModelsFailureObserver();

            manufacturerModelsSuccessObservable.addObserver(manufacturerModelsSuccessObserver);
            agentsResponseErrorObservable.addObserver(manufacturerModelsFailureObserver);

            GetManufacturerModelsRequest manModelsReq = null;
            try {
                manModelsReq = new GetManufacturerModelsRequest(this, queryStr, manufacturerModelsSuccessObservable, agentsResponseErrorObservable);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            manModelsReq.send();
        }
        else if(v.getId() == R.id.query_type_btn) {
            Log.d(LOG_TAG, "Query by type clicked");

            // create observable objects for manufacturer models data
            TypeModelsListener typeModelsSuccessObservable = new TypeModelsListener();
            WebServiceErrorListener typeModelsErrorObservable = new WebServiceErrorListener();

            // create observer objects
            final TypeModelsSuccessObserver typeModelsSuccessObserver = new TypeModelsSuccessObserver();
            final TypeModelsFailureObserver typeModelsFailureObserver = new TypeModelsFailureObserver();

            typeModelsSuccessObservable.addObserver(typeModelsSuccessObserver);
            typeModelsErrorObservable.addObserver(typeModelsFailureObserver);

            GetTypeModelsRequest typeModelsReq = null;
            try {
                typeModelsReq = new GetTypeModelsRequest(this, queryStr, typeModelsSuccessObservable, typeModelsErrorObservable);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            typeModelsReq.send();
        }
    }

    public class ManufacturerModelsFailureObserver implements Observer {

        private final static String LOG_TAG = "ManufacturerModelsFailureObserver";

        public void update(Observable obj, Object arg) {
            if(arg instanceof String) {
                String resp = (String) arg;
                Log.d(LOG_TAG, "Response: " + resp);
            }
        }
    }

    public class ManufacturerModelsSuccessObserver implements Observer {
        private final static String LOG_TAG = "AgentsResponseSuccessObserver";

        public void update(Observable obj, Object arg) {

            ManufacturerModelsListener manufacturerModelsResponseLisener = (ManufacturerModelsListener) obj;
            List<Car> carList = manufacturerModelsResponseLisener.getCarList();

            ListView listView = (ListView) findViewById(R.id.car_list_view);
            listView.setAdapter(null);

            listView.setAdapter(new CarAdapter(mActivity, carList));

        }
    }

    public class TypeModelsFailureObserver implements Observer {

        private final static String LOG_TAG = "TypeModelsFailureObserver";

        public void update(Observable obj, Object arg) {
            if(arg instanceof String) {
                String resp = (String) arg;
                Log.d(LOG_TAG, "Response: " + resp);
            }
        }
    }

    public class TypeModelsSuccessObserver implements Observer {
        private final static String LOG_TAG = "TypeModelsSuccessObserver";

        public void update(Observable obj, Object arg) {
            TypeModelsListener typeModelsResponseListener = (TypeModelsListener) obj;
            List<Car> carList = typeModelsResponseListener.getCarList();

            ListView listView = (ListView) findViewById(R.id.car_list_view);
            listView.setAdapter(null);

            listView.setAdapter(new CarAdapter(mActivity, carList));
        }
    }
}
