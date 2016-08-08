package com.example.rodrigo.taller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker Marcador;
    private Marker Points;
    double lat = -34.9055186;
    double lng = -54.956311;
    private Marker ultimoMarker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                System.out.println("hiciste click gil");
                ultimoMarker = marker;
                return false;
            }
        });


        cargarPuntos(lat,lng);

    }
    //Funcion para mapear puntos

    public void cargarPuntos(double lat, double lng){


        LatLng coordenadas = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Estoy aqui"));

        cargarCercanos();

        CameraPosition position = this.mMap.getCameraPosition();
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.zoom(17);
        builder.target(coordenadas);

        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(builder.build()));


    }

    public  void cargarCercanos()
    {
        double lat = -34.9055189; //mandamos los parametros a manopla
        double lng = -54.956316;
        final String url = "http://10.0.2.2:3000/api/points/getPuntos?lat="+lat+"&lng="+lng; // aca tenemos que pasar los parametros de donde estamos parados
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
                                String id = a.getString("id");
                                double latAux = a.getDouble("lat");
                                double lngAux = a.getDouble("lng");
                                LatLng coordenadas = new LatLng(latAux,lngAux);

                                Marker miMarcador = mMap.addMarker(new MarkerOptions()
                                        .position(coordenadas)
                                        .title(desc).snippet(id));


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

    public void  checkPunto(View view)
    {
        String idPunto= ultimoMarker.getSnippet();//mandamos los parametros a manopla
        String idUsuario = "1"; //pasar id de usuario logueado
        final String url = "http://10.0.2.2:3000/api/points/check?idUsuario="+idUsuario+"&idPunto="+idPunto; // aca tenemos que pasar los parametros de donde estamos parados
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest checkPunto = new JsonArrayRequest(Request.Method.POST, url, null,


                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {


                        // display response




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
        queue.add(checkPunto);


    }

    // lo que sigue es el codigo de franco, habria que checkear que nos sirve

    private void AgregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (Marcador != null) Marcador.remove();
        Marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            AgregarMarcador(lat, lng);
            cargarPuntos(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void miUbicacion() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion((location));

        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 15000,0,locListener);
    }
}

