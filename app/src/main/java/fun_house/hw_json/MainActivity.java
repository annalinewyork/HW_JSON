package fun_house.hw_json;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we will using AsyncTask during parsing
        new AsyncTaskParseJson().execute();
    }

    // you can make this class as another java file so it will be separated from your main activity.
    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        TextView nameText = (TextView) findViewById(R.id.name);
        TextView attrText = (TextView) findViewById(R.id.attribution);
        TextView categoryText = (TextView) findViewById(R.id.category);

        String API_KEY = "1DYC3weaEW7pA0QijG3fmgZNaNas4Kxm9Zu6yTcb";
        // set your json string url here
        String yourJsonStringUrl = "https://data.cityofnewyork.us/api/views/25th-nujf/rows.json?&api_key="+API_KEY;

        // contacts JSONArray
        JSONObject dataJsonArr = null;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
                if (json == null)
                    Log.d("null", "json is null");
                else
                    Log.d("not null", json.toString());

                // get the array of users
                JSONObject metaObject = json.getJSONObject("meta");

                JSONObject viewObject = metaObject.getJSONObject("view");

                nameText.setText(viewObject.getString("name"));
                attrText.setText(viewObject.getString("attribution"));
                categoryText.setText(viewObject.getString("category"));


//                // loop through all users
//                for (int i = 0; i < viewObject.length(); i++) {
//
//                    JSONObject c = dataJsonArr.getJSONObject(i);
//
//                    // Storing each json item in variable
//                    String firstname = c.getString("firstname");
//                    String lastname = c.getString("lastname");
//                    String username = c.getString("username");

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {
        }
    }
}
