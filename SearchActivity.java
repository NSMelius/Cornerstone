package melius.nathan.employeedbapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
    }


    public void goToAddItem(){
        Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);

        intent.putExtra("username",username);
        intent.putExtra("password",password);
        startActivity(intent);
    }


    public void getEmployees{

        EditText keyword = (EditText) findViewById(R.id.keyWordEntry);


        String URL = username+"&password="+password+"&keyword="+keyword;


        AsyncTask<String, Void, ArrayList> asyncTask = new AsyncTask<String, Void, ArrayList>(){
            @Override
            protected ArrayList<String> doInBackground(String... URL){

                ArrayList<String> list = new ArrayList();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("(ip address)/SearchDB.php?username="+URL)
                        .build();
                try{
                    Response response = client.newCall(request).execute();

                    JSONArray  array = new JSONArray(response.body().string());

                    for(int i = 0; i < array.length();i++){

                        JSONObject object = array.getJSONObject(i);

                        String item = object.getString("EmployeeFirstName")+object.getString("EmployeeLastName")+ object.getString("Phone")+
                                object.getString("Email")+ object.getString("Project")+object.getString("Employer");
                        list.add(item);

                    }

                }catch (IOException e){
                    e.printStackTrace();
                }catch (JSONException e){
                    e.printStackTrace();
                }
                return list;

            }

            @Override
            protected void onPostExecute(ArrayList list) {


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, list);
                ListView lView = (ListView) findViewById(R.id.employeeList);
                lView.setAdapter(adapter);

                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    }
                });



            }


        };

        asyncTask.execute(URL);
    }
}
