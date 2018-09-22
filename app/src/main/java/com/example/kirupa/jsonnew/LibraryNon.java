package com.example.kirupa.jsonnew;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibraryNon extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ResponsePogo.CountriesBean> responsePogo;
    ArrayList<String> dataArray1=new ArrayList<>();
    ArrayList<String> dataArray2=new ArrayList<>();
    ArrayList<String> dataArray3=new ArrayList<>();
    ArrayList<String> dataArray4=new ArrayList<>();
    ArrayList<String> dataArray5=new ArrayList<>();
    ArrayList<String> dataArray6=new ArrayList<>();
    private static String url="http://wptrafficanalyzer.in/p/demo1/first.php/countries/";
    String TAG="LibraryNon";
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_non);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LibraryNon.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.d(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
/*
{
countries: [
{
countryname: "India",
flag: "http://wptrafficanalyzer.in/p/demo1/india.png",
language: "Hindi",
capital: "New Delhi",
currency: {
code: "INR",
currencyname: "Rupee"
}
},
{
countryname: "Pakistan",
flag: "http://wptrafficanalyzer.in/p/demo1/pakistan.png",
language: "Urdu",
capital: "Islamabad",
currency: {
code: "PKR",
currencyname: "Pakistani Rupee"
}
},
{
countryname: "Sri Lanka",
flag: "http://wptrafficanalyzer.in/p/demo1/srilanka.png",
language: "Sinhala",
capital: "Sri Jayawardenapura Kotte",
currency: {
code: "SKR",
currencyname: "Sri Lankan Rupee"
}
},
{
countryname: "China",
flag: "http://wptrafficanalyzer.in/p/demo1/china.png",
language: "Chineese",
capital: "Beijing",
currency: {
code: "CNY",
currencyname: "Renminbi"
}
},
{
countryname: "Bangladesh",
flag: "http://wptrafficanalyzer.in/p/demo1/bangladesh.png",
language: "Bangla",
capital: "Dhaka",
currency: {
code: "BDT",
currencyname: "Taka"
}
},
{
countryname: "Nepal",
flag: "http://wptrafficanalyzer.in/p/demo1/nepal.png",
language: "Nepal Bhasa",
capital: "Kathmandu",
currency: {
code: "NPR",
currencyname: "Napalese Rupee"
}
},
{
countryname: "Afghanistan",
flag: "http://wptrafficanalyzer.in/p/demo1/afghanistan.png",
language: "Dari Persian",
capital: "Kabul",
currency: {
code: "AFN",
currencyname: "Afghani"
}
},
{
countryname: "North Korea",
flag: "http://wptrafficanalyzer.in/p/demo1/nkorea.png",
language: "Korean",
capital: "Pyongyang",
currency: {
code: "KPW",
currencyname: "North Korean Won"
}
},
{
countryname: "South Korea",
flag: "http://wptrafficanalyzer.in/p/demo1/skorea.png",
language: "Korean",
capital: "Seoul",
currency: {
code: "KRW",
currencyname: "South Korean Won"
}
},
{
countryname: "Japan",
flag: "http://wptrafficanalyzer.in/p/demo1/japan.png",
language: "Japanese",
capital: "Tokyo",
currency: {
code: "JPY",
currencyname: "Yen"
}
}
]
}
*/
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray jsonArrayData = jsonObj.getJSONArray("countries");
                    Log.d(TAG, " jsonArrayData length(): " +  jsonArrayData.length());
                    ResponsePogo.CountriesBean responsePogo=new ResponsePogo.CountriesBean();
                    ResponsePogo.CountriesBean.CurrencyBean responsePogo1=new ResponsePogo.CountriesBean.CurrencyBean();
                    for (int i = 0; i < jsonArrayData.length(); i++) {
                        JSONObject c = jsonArrayData.getJSONObject(i);
                        String obj1 = c.getString("countryname");
                        String obj2 = c.getString("flag");
                        String obj3 = c.getString("language");
                        String obj4 = c.getString("capital");
                        responsePogo.setCountryname(obj1);
                        responsePogo.setFlag(obj2);
                        responsePogo.setLanguage(obj3);
                        responsePogo.setCapital(obj4);
                        Log.d(TAG, "obj1:>>"+ obj1+">>obj2:>>"+obj2+">>obj3:>>"+
                                obj3+">>obj4:>>"+obj4);

                        JSONObject c1=c.getJSONObject("currency");
                        Log.d(TAG, "c1 length :>>"+ c1.length());
                        String obj5 = c1.getString("code");
                        String obj6 = c1.getString("currencyname");
                        Log.d(TAG, "obj5:>>"+ obj5+">>obj6:>>"+obj6);
                        responsePogo1.setCode(obj5);
                        responsePogo1.setCurrencyname(obj6);

                        dataArray1.add(obj1);
                        dataArray2.add(obj2);
                        dataArray3.add(obj3);
                        dataArray4.add(obj4);
                        dataArray5.add(obj5);
                        dataArray6.add(obj6);

                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            // set a LinearLayoutManager with default vertical orientation
//        GridLayoutManager linearLayoutManager= new GridLayoutManager(MainActivity.this,2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LibraryNon.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            // call the constructor of UsersAdapter to send the reference and data to Adapter
            ArrayListRecyclerviewAdapter recyclerViewAdapter  = new ArrayListRecyclerviewAdapter(LibraryNon.this, dataArray1,dataArray2, dataArray3,dataArray4,dataArray5,dataArray6);
            recyclerView.setAdapter(recyclerViewAdapter); // set the Adapter to RecyclerView
            /**
             * Updating parsed JSON data into ListView
             * */


//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, dataList,
//                    R.layout.list_item, new String[]{"title", "body",
//                    "login","updated_at"}, new int[]{R.id.title,
//                    R.id.body, R.id.login,R.id.updated_at});
//
//            lv.setAdapter(adapter);
        }

    }

}
