package com.example.student.simplelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class PriceView extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL1 = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";
    private final String BASE_URL2 = "https://apiv2.bitcoinaverage.com/indices/global/ticker/ETH";
    // Member Variables:
    TextView mPriceTextView;
    String finalUrl1, finalUrl2, spinItem="BTC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_view);

        mPriceTextView = findViewById(R.id.priceLabel);
        Spinner spinner1 = findViewById(R.id.currency_spinner);
        Spinner spinner2 = findViewById(R.id.coin_spinner);
        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.currency_array, R.layout.spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.coin_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        // TODO: Set an OnItemSelected listener on the spinner
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin-Ticker", "" + parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), "Spinner has been set with listener " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                Log.d("Bitcoin-Ticker", "Final url1 is: " + parent.getItemAtPosition(position).toString());
                finalUrl1 = parent.getItemAtPosition(position).toString();
                if (spinItem.equals("BTC")) {
                    finalUrl2 = BASE_URL1 + finalUrl1;
                } else
                    finalUrl2 = BASE_URL2 + finalUrl1;
                letsDoSomeNetworking(finalUrl2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Log.d("Bitcoin-Ticker", "No item selected.");
            }
        });
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin-Ticker", "" + parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), "Spinner has been set with listener" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                Log.d("Bitcoin-Ticker", "Spinner choice is: " + parent.getItemAtPosition(position));
                spinItem = parent.getItemAtPosition(position).toString();
                if (spinItem.equals("BTC")) {
                    finalUrl2 = BASE_URL1 + finalUrl1;
                } else
                    finalUrl2 = BASE_URL2 + finalUrl1;

                Log.d("Bitcoin-Ticker", "Final url2 is: " + finalUrl2);

                letsDoSomeNetworking(finalUrl2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin-Ticker", "JSON: " + response.toString());
                try {
                    String price = response.getString("last");
                    mPriceTextView.setText(price);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin-Ticker", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin-Ticker", "Fail response: " + response);
                Log.e("ERROR", e.toString());

            }
        });


    }


}
