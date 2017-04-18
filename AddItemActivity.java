package melius.nathan.employeedbapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddItemActivity extends AppCompatActivity {

    EditText firstName, lastName, phone, email, project, employer;

    String username, password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        firstName = (EditText) findViewById(R.id.firstNameEntry);
        lastName =(EditText) findViewById(R.id.lastNameEntry);
        phone = (EditText) findViewById(R.id.phoneEntry);
        email = (EditText) findViewById(R.id.emailEntry);
        project = (EditText) findViewById(R.id.projectEntry);
        employer = (EditText) findViewById(R.id.employerEntry);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
    }



    public void addToDB() {
        String fName, lName, phoneString, emailString, projectString, employerString;

        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        phoneString = phone.getText().toString();
        emailString = email.getText().toString();
        projectString = project.getText().toString();
        employerString = employer.getText().toString();

        String URL = username+"&password="+password+"&firstName="+fName+"&lastName="+lName+"&phone="+phoneString+
                "&email="+emailString+"&project="+projectString+"&employer="+employerString;

        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... URL) {


                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("10.0.0.6/SearchDB.php?username=" + URL)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());
                    for(int i = 0; i < array.length();i++) {

                        JSONObject object = array.getJSONObject(i);
                        boolean error = object.getBoolean("error");


                        if (!error) {
                            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                            startActivity(intent);
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
    }//adToDB
}
