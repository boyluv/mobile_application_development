package com.example.tuanle.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SOService mService;
    private EditText edtLat;
    private EditText edtLong;
    private Button btnGetResult;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtLat = (EditText) findViewById(R.id.edt_lat);
        edtLong = (EditText) findViewById(R.id.edt_long);
        btnGetResult = (Button) findViewById(R.id.result_btn);
        txtResult = (TextView) findViewById(R.id.result);

        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latlng;
                if(edtLat.getText().length()>0 || edtLong.getText().length()>0) {
                    latlng = edtLat.getText().toString() + "," + edtLong.getText().toString();
                }
                else
                    latlng = "10.881784,106.804496";
                mService = ApiUtils.getSOService();
                mService.getDetail(latlng,"AIzaSyDSY9JMWI_3La8qYtt5C3wchqguSIAnyDM").enqueue(new Callback<DetailAddress>() {
                    @Override
                    public void onResponse(Call<DetailAddress> call, Response<DetailAddress> response) {

                        if(response.isSuccessful()) {
                            try{
                                Log.d("Data", response.body().getResults().get(0).getFormattedAddress());
                                txtResult.setText(response.body().getResults().get(0).getFormattedAddress());
                            }
                            catch (NullPointerException e){
                                Log.e("Data","Null address");
                            }
                        }
                        else
                            Log.d("Data",response.toString());

                    }

                    @Override
                    public void onFailure(Call<DetailAddress> call, Throwable t) {
                        Log.d("Data","failed");

                    }
                });

            }
        });



    }
}
