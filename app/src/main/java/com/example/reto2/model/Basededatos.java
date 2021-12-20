package com.example.reto2.model;

import com.example.reto2.R;

import java.util.ArrayList;

public class Basededatos {

    private ArrayList<Producto> base;

    public Basededatos() {
        base = new ArrayList<Producto>();
        base.add(new Producto(1, R.drawable.chirizo,"Chorizo", 10000,"Ricos chorizos santarosanos",1));
        base.add(new Producto(2,R.drawable.costillas,"Costillas", 30000,"Costillas a en salsa",1));
        base.add(new Producto(3,R.drawable.hotdog,"Hotdog", 15000,"Perros a su gusto",1));
        base.add(new Producto(4,R.drawable.foim,"Hamburguesa", 15000,"Hamburguesa",1));

    }

    public ArrayList<Producto> getProductos(){
        return base;
    }
}
