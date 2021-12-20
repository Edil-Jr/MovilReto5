package com.example.reto2.ui.Servicios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.reto2.Adaptadores.AdaptRviewfavoritos;
import com.example.reto2.Adaptadores.ServicioAdapter;
import com.example.reto2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmen_Servicios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmen_Servicios extends Fragment {

    Button botonGET;
    TextView mostrarJSON, mostrarLista;

    View v;

    ListView listaServicios;
    ServicioAdapter adaptador;

    // CONEXION A LA BASE DE DATOS: SQLite
     //conectar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmen_Servicios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmen_Servicios.
     */
    // TODO: Rename and change types and number of parameters
    /*
    public static Fragmen_Servicios newInstance(String param1, String param2) {
        Fragmen_Servicios fragment = new Fragmen_Servicios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_servicios, container, false);
        //listaServicios = (ListView) v.findViewById(R.id.mostrarLista);
       // adaptador = new ServicioAdapter(, getContext());

     //   listaServicios.setAdapter(adaptador);

        Button btget = (Button) v.findViewById(R.id.botonGET);
        btget.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 volleyGET();
             }
         });



        return v;
    }

    private void volleyGET() {
      //  String url = "https://reqres.in/api/users?page=2";
        String url = "https://g837928347e906d-dbrestgofood.adb.ca-montreal-1.oraclecloudapps.com/ords/admin/open-api-catalog/sucursal/";
        List<String> jsonResponses = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("item");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        jsonResponses.add(name); //Nombre
                        String description = jsonObject.getString("description");
                        jsonResponses.add(description); //Descripcion

                        String price = jsonObject.getString("price");
                        jsonResponses.add(price); //Nombre

                        mostrarJSON.setText(jsonObject.toString());
                        mostrarLista.setText(name);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }




}



