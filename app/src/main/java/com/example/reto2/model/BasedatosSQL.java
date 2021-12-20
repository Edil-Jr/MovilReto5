package com.example.reto2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BasedatosSQL extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static final String dataBaseName = "dbFavoritos";
    private static final int dataVersion = 1;

    public BasedatosSQL(@Nullable Context context) {
        super(context, dataBaseName, null, dataVersion);
        sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE FAVORITOS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IMAGEN INTEGER,"+
              //  "IDC INTEGER," +
                "NAME TEXT,"+
                "DESCRIPTION TEXT," +
                "PRICE INTEGER)");

/*

       db.execSQL("CREATE TABLE favoritos (titulo TEXT, descripcion TEXT)");
        db.execSQL("INSERT INTO favoritos VALUES ('Vegetariana','sssssssssssss')");
        db.execSQL("INSERT INTO favoritos VALUES ('Tres quesos','rrrrrrrrrrrrrrrrr')");
        db.execSQL("INSERT INTO favoritos VALUES ('Pollo Champiñones','sssssssssssss')");

*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE FAVORITOS");
        onCreate(db);
    }

    public Cursor getFavoritos()
    {
        Cursor datosFav = sqLiteDatabase.rawQuery("SELECT * FROM FAVORITOS",null);
        return datosFav;
    }

    public void deleteFavoritos(Integer idc)
    {
        try {
            sqLiteDatabase.execSQL("DELETE FROM FAVORITOS WHERE ID="+idc);
            Log.e("SQL:eliminar","eliminado");
        }catch (Exception e){

            Log.e("Exeption","e"+e.toString());

        }


    }

    public void deleteTodo(){
        sqLiteDatabase.execSQL("DELETE FROM FAVORITOS");
    }

    public void agregarFavoritos(Integer image,String name, String descrip, Integer price )
    {
        ContentValues cValues = new ContentValues();
        // cValues.put("ID",id);
        cValues.put("IMAGEN",image);
        cValues.put("NAME",name);
        cValues.put("DESCRIPTION",descrip);
        cValues.put("PRICE",price);

        sqLiteDatabase.insert("FAVORITOS",null,cValues);
    }



}
