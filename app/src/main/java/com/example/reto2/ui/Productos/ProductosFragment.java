package com.example.reto2.ui.Productos;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.reto2.FormActivity;
import com.example.reto2.R;
import com.example.reto2.databinding.FragmentGalleryBinding;
import com.example.reto2.model.BasedatosSQL;
import com.example.reto2.model.Basededatos;
import com.example.reto2.model.Producto;

import java.security.ProtectionDomain;
import java.util.ArrayList;

public class ProductosFragment extends Fragment {

    private ProductosViewModel productosViewModel;
    private FragmentGalleryBinding binding;
    private LinearLayout layoutPadre;
    private LinearLayout layoutHorizontal;
    private LinearLayout layoutVertical;
    private LinearLayout layoutVertical2;
    Basededatos basededatos;
    BasedatosSQL bdatos;
    ArrayList<Producto> carrito,favoritoscar;
    ImageView imag1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productosViewModel =
                new ViewModelProvider(this).get(ProductosViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        basededatos = new Basededatos();

        carrito = new ArrayList<Producto>();
        favoritoscar= new ArrayList<Producto>();
        layoutPadre = (LinearLayout) binding.layaoutpadre;
        int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
        int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
       /**BASE DE DATOS SQLITE
        * INICIALIZAMOS ANTES de empezar para comprobar los favoritos guardados en el cel
        * */
        bdatos = new BasedatosSQL(getContext());
        Cursor cursor = bdatos.getFavoritos();
       // bdatos.deleteTodo();
        cursor.moveToFirst();
        /**
         * Ponemos la informacion en el layout
         * */
        for(Producto producto:basededatos.getProductos()){

            //Log.e("Evento",producto.getName());
            /**
             * creo el layout horizontal para agregar productos
             */
            layoutHorizontal = new LinearLayout(getContext());
            layoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);
            layoutHorizontal.setLayoutParams(new LinearLayout.LayoutParams(matchParent,wrapContent));

            /**
             * creo un layout vertical para agregar los campos de descripción, nombre, etc
             */

            layoutVertical = new LinearLayout(getContext());
            layoutVertical.setOrientation(LinearLayout.VERTICAL);
            layoutVertical.setLayoutParams(new LinearLayout.LayoutParams(0,wrapContent,2));


            layoutVertical2 = new LinearLayout(getContext());
            layoutVertical2.setOrientation(LinearLayout.VERTICAL);
            layoutVertical2.setLayoutParams(new LinearLayout.LayoutParams(0,wrapContent,1));


            /**
             * creo imagen para cada uno de los productos
             */
            ImageView image = new ImageView(getContext());
            /** ojo porque la imagen en la base de datos está como Integer */
            image.setImageResource(producto.getImagen());
            image.setLayoutParams(new LinearLayout.LayoutParams(100,200,1));

            /**
             * Crear los textos para indicar los atributos de los productos
             */
            TextView espacio = new TextView(getContext());
            espacio.setLayoutParams(new LinearLayout.LayoutParams(300,50));
            espacio.setText("");


            TextView texto = new TextView(getContext());
            texto.setLayoutParams(new LinearLayout.LayoutParams(300,wrapContent));
            texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto.setTextColor(Color.BLACK);
            texto.setText(producto.getName());

            TextView texto2 = new TextView(getContext());
            texto2.setLayoutParams(new LinearLayout.LayoutParams(300,wrapContent));
            texto2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto2.setTextColor(Color.BLACK);
            texto2.setText("Precio: $" + producto.getPrice());

            TextView texto3 = new TextView(getContext());
            texto3.setLayoutParams(new LinearLayout.LayoutParams(350,wrapContent));
            texto3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto3.setTextColor(Color.BLACK);
            texto3.setText(producto.getDescription());

            TextView texto4 = new TextView(getContext());
            texto4.setLayoutParams(new LinearLayout.LayoutParams(350,wrapContent));
            texto4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15.f);
            texto4.setTextColor(Color.BLACK);
            texto4.setText("Cant Carrito: 0");

            /**
             * se agregan los botones
             */
            Button button = new Button(getContext());
            button.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,1));
            button.setText("+");
            button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button.setBackgroundColor(getResources().getColor(R.color.amarilloprep));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buscarProducto(carrito,producto)){
                        for(Producto prod:carrito){
                            if(prod.getId() == producto.getId()){
                                prod.setCantidad(prod.getCantidad()+1);
                                texto4.setText("Cant Carrito: " + prod.getCantidad());
                                //Toast.makeText(getContext(), "Otro producto: " + prod.getName() + " Cant: " + prod.getCantidad() + " Agregado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        carrito.add(producto);
                        texto4.setText("Cant Carrito: " + 1);
                        Toast.makeText(getContext(), "Nuevo producto: " + producto.getName() + " Agregado", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
                }
            });

            Button button2 = new Button(getContext());
            button2.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,1));
            button2.setText("-");
            button2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            button2.setBackgroundColor(getResources().getColor(R.color.amarilloprep));

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(buscarProducto(carrito,producto)){
                        for(Producto prod:carrito){
                            if(prod.getId() == producto.getId() && prod.getCantidad()>0){
                                prod.setCantidad(prod.getCantidad()-1);
                                texto4.setText("Cant Carrito: " + prod.getCantidad());
                                //Toast.makeText(getContext(), "Otro producto: " + prod.getName() + " Cant: " + prod.getCantidad() + " Agregado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "Cantidad de " + producto.getName() + "en 0", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "Cantidad de " + producto.getName() + "en 0", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getContext(), "Próximamente se agrega función de compra", Toast.LENGTH_SHORT).show();
                }
            });


        //   bdatos.deleteTodo();
            Button favoritos = new Button(getContext());
            favoritos.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent,3));
            favoritos.setText("Favoritos");
            favoritos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            favoritos.setBackgroundColor(getResources().getColor(R.color.naranjadbz));

            favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /***Agregamos a la base de datos el producto a favoritos**/

               //   Integer item=producto.getId();

                //favoritoscar.add(producto);

                   boolean agregado= agregarfavoritos(producto);
                   if (agregado){
                        Toast.makeText(getContext(), "Agregado" , Toast.LENGTH_SHORT).show();
                       bdatos.agregarFavoritos(producto.getImagen(), producto.getName(), producto.getDescription(), producto.getPrice());


                   }else{

                       Toast.makeText(getContext(), "NO Agregado" , Toast.LENGTH_SHORT).show();

                   }

                }
            });

            /**
             * se muestran los componentes
             */
            layoutVertical.addView(espacio);
            layoutVertical.addView(texto);
            layoutVertical.addView(texto2);
            layoutVertical.addView(texto3);
            layoutVertical.addView(texto4);

            layoutVertical2.addView(button);
            layoutVertical2.addView(button2);
            layoutVertical2.addView(favoritos);


            layoutHorizontal.addView(image);
            layoutHorizontal.addView(layoutVertical);
            layoutHorizontal.addView(layoutVertical2);
            layoutHorizontal.setGravity(Gravity.CENTER);
            layoutPadre.addView(layoutHorizontal);
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private boolean buscarProducto(ArrayList<Producto> carro,Producto producto){

        for(Producto p: carro){
            if(producto.getId() == p.getId()){

                return true;
            }
      //      Log.e("id Producto", "onClick: existe "+p.getId());
        }

        return false;


    }

    private boolean agregarfavoritos(Producto producto) {
        Cursor cursor = bdatos.getFavoritos();
        cursor.moveToFirst();
        Producto itemtemp;
        ArrayList<String> nombreproducto;
        boolean existe;
        nombreproducto= new ArrayList<String>();
      //  String item = cursor.getString (2).toString();
      //  Integer valor =Integer.parseInt(cursor.getString(0));
     //   Log.e("id Producto", "onClick: existe " + item.toString());
        do{
            if (cursor.getCount() == 0){
                existe = true;
            }
            else {
                Log.e("id Producto", "onClick: nombrebasedatos " + cursor.getString(2).toString());
                Log.e("id Producto", "onClick: nombre item " + producto.getName());

                if (producto.getName().equals(cursor.getString(2))) {
                    Log.e("id Producto", "ya existe " + cursor.getString(2).toString());
                    existe= false;break;
                }else{
                    existe = true;
                }

            }

        }while (cursor.moveToNext());

        return existe;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.compras:
                Intent intent = new Intent(getContext(), FormActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("car",carrito);
                intent.putExtras(bundle);

                getActivity().startActivity(intent);
                //Toast.makeText(getContext(), "Hola estoy en productos", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

}