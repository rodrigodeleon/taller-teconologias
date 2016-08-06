package com.example.rodrigo.taller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button boton=(Button) findViewById(R.id.btnIngresar);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String usuario=((EditText)findViewById(R.id.txtUsuario)).getText().toString();

                if(usuario.equals("admin"))
                {
                    Intent nuevoform=new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(nuevoform);
                }
                else
                {
                    Toast.makeText(boton.getContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void miJson(View view)
    {
        double lat = -34.9055189;
        double lng = -54.956316;
        final String url = "http://10.0.2.2:3000/api/users/check?lat="+lat+"&lng="+lng; // aca tenemos que pasar los parametros de donde estamos parados
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest getPuntos = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response

                        for(int i = 0; i < response.length(); i++)
                            try {

                                //Este bloque me trae las latitudes y longitudes de todos los puntos, aca adentro habria que mandarlos al mapa
                                JSONObject a = response.getJSONObject(i).getJSONObject("coordenadas");
                                String desc = a.getString("descripcion");
                                String lat = a.getString("lat");
                                String lng = a.getString("lng");
                                System.out.println(desc);
                                System.out.println(lat);
                                System.out.println(lng);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
/*
            este bloque sirve cuando precisas solo un objeto
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );*/
        // add it to the RequestQueue
        queue.add(getPuntos);


    }
}
