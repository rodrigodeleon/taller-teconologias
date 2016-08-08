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
                Usuario logueado = loginUser(usuario);
                if(logueado.getNombre()!=null)
                {
                    Intent nuevoform = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(nuevoform);
                }
                else
                {
                    Toast.makeText(boton.getContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public Usuario loginUser(String usuario) {


        final String url = "http://10.0.2.2:3000/api/users/getUsuario?nombre=" + usuario; // aca tenemos que pasar los parametros de donde estamos parados
        RequestQueue queue = Volley.newRequestQueue(this);
        final Usuario miUser = new Usuario();

        JsonArrayRequest getUsuario = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response

                        try {

                            //intentar que esta mierda asincrona se ejecute antes del return
                            System.out.println(response);
                            miUser.setId(response.getJSONObject(0).getJSONObject("usuario").getInt("id"));
                            miUser.setNombre(response.getJSONObject(0).getJSONObject("usuario").getString("nombre"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }


        );
        queue.add(getUsuario);


        return  miUser;


    }

}
