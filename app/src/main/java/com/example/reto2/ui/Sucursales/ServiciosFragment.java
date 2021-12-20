package com.example.reto2.ui.Sucursales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.reto2.BuildConfig;
import com.example.reto2.FormActivity;
import com.example.reto2.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class ServiciosFragment extends Fragment {
    private ServiciosViewModel serviciosViewModel;
    private MapView myOpenMapView;
    private MapController myMapController;
    GeoPoint Geopuntos1,Geopuntos2,Geopuntos3,Geopuntos4;

   /* public ServiciosFragment(MapView myOpenMapView, MapController myMapController) {
        this.myOpenMapView = myOpenMapView;
        this.myMapController = myMapController;
    }
*/
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel =
                new ViewModelProvider(this).get(ServiciosViewModel.class);

        View root = inflater.inflate(R.layout.fragment_sucursales, container, false);

        final TextView textView = root.findViewById(R.id.ServiciosMap);
        serviciosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            //    Toast.makeText(getContext(), "Proximamente nuestras ubicaciones", Toast.LENGTH_SHORT).show();
            }
        });

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        /******PUNTOS DE SUCURSALES*******/
        this.Geopuntos1 = new GeoPoint(4.6351,-74.0703);
        this.Geopuntos2 = new GeoPoint(4.6351, -74.0703);
        this.Geopuntos3 = new GeoPoint(4.6510, -74.0821);
        this.Geopuntos4 = new GeoPoint(4.6410, -74.0921);

        myOpenMapView = (MapView) root.findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(this.Geopuntos1);
        myMapController.setZoom(16);
        myOpenMapView.setMultiTouchControls(true);


        /* -------------------------------------------------------------------------------------------------- */
      final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), myOpenMapView);
        myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
        myLocationoverlay.enableMyLocation();

        myLocationoverlay.runOnFirstFix(new Runnable() {
            public void run() {
                myMapController.animateTo(myLocationoverlay.getMyLocation());
            }
        });
        /* -------------------------------------------------------------------------------------------------- */

        ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();
        puntos.add(new OverlayItem("Bogota","Ciudad de Bogota",this.Geopuntos1));
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<OverlayItem>(getContext(), puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);

        /**-------------------------------------------------**/
        ArrayList<OverlayItem> puntos2 = new ArrayList<OverlayItem>();
        puntos2.add(new OverlayItem("Bogota","Ciudad de Bogota",this.Geopuntos2));
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap2 = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa2 = new ItemizedOverlayWithFocus<OverlayItem>(getContext(), puntos2, tap2);
        capa2.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);


        /**---------------------------------------*/
        ArrayList<OverlayItem> puntos3 = new ArrayList<OverlayItem>();
        puntos3.add(new OverlayItem("Bogota","Ciudad de Bogota",this.Geopuntos1));
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap3 = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa3 = new ItemizedOverlayWithFocus<OverlayItem>(getContext(), puntos3, tap3);
        capa3.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa3);

        /***---------------------------------------------------------------------------------------------------****/
        return root;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
                intent.putExtra("name","servicios");
                getActivity().startActivity(intent);
                Toast.makeText(getContext(), "Hola estoy en Servicios", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }



}
