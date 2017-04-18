package melius.nathan.employeedbapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void LogIn(View view){



        EditText userEntry = (EditText) findViewById(R.id.usernameEntry);
        EditText passEntry = (EditText) findViewById(R.id.passwordEntry);

        username = userEntry.getText().toString();
        password = passEntry.getText().toString();

        if(username == null || password == null){
            Toast toast = Toast.makeText(getApplicationContext(),"Please enter a username and password",Toast.LENGTH_SHORT);
            toast.show();
        }

        String URL = username+"&password="+password;

        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... URL){

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("(ip address/Login.php?username="+URL)
                        .build();
                try{
                    Response response = client.newCall(request).execute();

                    JSONArray  array = new JSONArray(response.body().string());

                    for(int i = 0; i < array.length();i++){

                        JSONObject object = array.getJSONObject(i);
                        boolean error = object.getBoolean("error");
                        if(!error){
                            Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                            intent.putExtra("Username",username);
                            intent.putExtra("Password",password);
                            startActivity(intent);

                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Incorrect username or Password",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                }catch (IOException e){
                    e.printStackTrace();
                }catch (JSONException e){
                    e.printStackTrace();
                }
                return null;

            }
        };

        asyncTask.execute(URL);

    }//LogIn
}
