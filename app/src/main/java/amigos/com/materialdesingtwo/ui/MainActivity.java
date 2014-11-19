package amigos.com.materialdesingtwo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;

import java.util.List;

import amigos.com.materialdesingtwo.ApplicationController;
import amigos.com.materialdesingtwo.R;
import amigos.com.materialdesingtwo.adapter.MyAdapter;
import amigos.com.materialdesingtwo.model.ModelFood;

import static amigos.com.materialdesingtwo.R.id.fab;


public class MainActivity extends ActionBarActivity {

    private static String URL = "https://script.googleusercontent.com/macros/echo?user_content_key=zuOy0OOavg5k2PVwE1XjycGoflAh1o72lVkbqgUSoDc9tPmANyTRg9_V6Xz8z9IIQNx9sq_rS4HgMXAMG37DObTozBhtFGHUm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnB_xQQmkGLa7SMN6weBBkM3nJbTLYBEcheaRdOPGmKHXtu7vm5nL2OqJS1wsmqOSRqk9antgdnPz&lib=McQxdDJ7APCeQiPrVGIlRW3N83SZ-dHky";
    private JsonArrayRequest jsonArrayRequest;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(fab);
        floatingActionButton.attachToRecyclerView(recyclerView);

        obtainDataFoods();


    }

    private void obtainDataFoods() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setTitle("Meterial Design");
        pDialog.setIcon(R.drawable.ic_launcher);
        pDialog.setMessage("Loading...");
        pDialog.show();

        jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new Gson();
                List<ModelFood> foods = gson.fromJson(response.toString(), new TypeToken<List<ModelFood>>() {
                }.getType());

                recyclerView.setAdapter(new MyAdapter(foods,getApplicationContext()));

                pDialog.dismiss();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        });

        ApplicationController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!jsonArrayRequest.isCanceled())
        {
            ApplicationController.getInstance().cancelPendingRequests();
        }
    }
}
