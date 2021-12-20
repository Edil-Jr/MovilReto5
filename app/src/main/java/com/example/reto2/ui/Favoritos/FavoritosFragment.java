package com.example.reto2.ui.Favoritos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reto2.Adaptadores.AdaptRviewfavoritos;
import com.example.reto2.FormActivity;
import com.example.reto2.R;
import com.example.reto2.databinding.FragmentSlideshowBinding;
import com.example.reto2.model.BasedatosSQL;
import com.example.reto2.model.Producto;

import java.util.ArrayList;

public class FavoritosFragment extends Fragment implements AdaptRviewfavoritos.ItemClickListener{

    private FavoritosViewModel FavoritosViewModel;
    private FragmentSlideshowBinding binding;
    private BasedatosSQL datosfav;
    private Cursor datos;
    private RecyclerView grillaentrada;
    AdaptRviewfavoritos adapter;
    ViewGroup container;
    LayoutInflater inflater;
    Bundle saveInstanceState;
    Button btBorrar;
    Context context;

    ArrayList<Producto> listaFavoritos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.context = inflater.getContext();
       // this.inflater = inflater;
   //     this.saveInstanceState = savedInstanceState;
        FavoritosViewModel =
                new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
/*
        final TextView textView = binding.textHome;
        FavoritosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
            mostrarfavoritos(root);
            return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
      //  datos.close();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.datosfav = new BasedatosSQL(getContext());





    }

    public void mostrarfavoritos(View root){
       // binding = FragmentSlideshowBinding.inflate(this.inflater, this.container, false);
        //View root = binding.getRoot();

        if (inflater == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
         if (root == null){

             root  = inflater.inflate(R.layout.itemlist,null);
         }

        this.datos = datosfav.getFavoritos();

        grillaentrada = (RecyclerView) root.findViewById(R.id.listaFav);
        listaFavoritos = new ArrayList<Producto>();
 //       ViewGroup ly = container.getContext();

        if (datos.getCount() != 0){
            datos.moveToFirst();
            do{
                int id = datos.getInt(0);
                int img = datos.getInt(1);
                String nombre = datos.getString(2);
                String description = datos.getString(3);
                int precio = datos.getInt(4);

                Producto newproduct= new Producto(id,img,nombre,precio,description,0);
                listaFavoritos.add(newproduct);
            }while (datos.moveToNext());

                //new LinearLayoutManager(getContext())
            grillaentrada.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new AdaptRviewfavoritos(getContext(), listaFavoritos);
            adapter.setClickListener(this);
            grillaentrada.setAdapter(adapter);

        }else{

            Toast.makeText(getContext(), "No hay datos en favoritos", Toast.LENGTH_SHORT).show();

        }



    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.compras:
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("name","Favoritos");
                getActivity().startActivity(intent);
                Toast.makeText(getContext(), "Hola estoy en Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(view.getContext(), "Selecciono item "+position, Toast.LENGTH_SHORT).show();
        //Toast.makeT   ext(getContext(), "precionoboton", Toast.LENGTH_SHORT).show();
        Log.e("View",""+view.getContext());
        Button btborrar = (Button)  view.findViewById(R.id.borrarFav);

        btborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ""+position, Toast.LENGTH_SHORT).show();

                datosfav.deleteFavoritos(position);
              //  setContentView(R.layout.activity_form);
            //    onCreateView(inflater,container,saveInstanceState);
                mostrarfavoritos(v);


            }

        });




    }



}