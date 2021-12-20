package com.example.reto2.model;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

public class ApiOracle {
    private RequestQueue queue;

    public ApiOracle(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public void  insertarSucursal(String name, String descripcion, String location, ImageView imageView){



    }
}
