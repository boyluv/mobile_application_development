package com.example.tuanle.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.edt_input)
    EditText edtInput;

    @BindView(R.id.edt_output)
    EditText edtOutput;


    @BindView(R.id.spinner_currency)
    Spinner spinType;

    @BindView(R.id.tv_input)
    TextView input;

    @BindView(R.id.tv_output)
    TextView output;

    private final double USDTOVND = 22794.0;
    private final double EURTOVND = 28165.86;
    private final double JPYTOVND = 212.06;
    private String inputUnit = "";
    private String outputUnit = "";

    private final double USDTOEURO = 0.81;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private double numbConstant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        df2.setRoundingMode(RoundingMode.UP);
        numbConstant = USDTOVND;

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.currency_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinType.setAdapter(adapter);
        spinType.setOnItemSelectedListener(this);

        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    edtOutput.setText(df2.format(Double.parseDouble(s.toString()) * numbConstant));

                }
                else
                    edtOutput.setText("");

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() + position, Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                numbConstant = USDTOVND;
                input.setText(getResources().getString(R.string.usd));
                output.setText(getResources().getString(R.string.vnd));
                break;
            case 1:
                numbConstant = EURTOVND;
                input.setText(getResources().getString(R.string.euro));
                output.setText(getResources().getString(R.string.vnd));
                break;
            case 2:
                numbConstant = 1/USDTOVND;
                input.setText(getResources().getString(R.string.vnd));
                output.setText(getResources().getString(R.string.usd));
                break;
            case 3:
                numbConstant = 1/EURTOVND;
                input.setText(getResources().getString(R.string.vnd));
                output.setText(getResources().getString(R.string.euro));
                break;
            case 4:
                numbConstant = JPYTOVND;
                input.setText(getResources().getString(R.string.jpy));
                output.setText(getResources().getString(R.string.vnd));
                break;
            case 5:
                numbConstant = 1/JPYTOVND;
                input.setText(getResources().getString(R.string.vnd));
                output.setText(getResources().getString(R.string.jpy));
                break;
        }
        edtOutput.setText("");
        edtInput.setText("");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
