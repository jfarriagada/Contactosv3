package com.example.konarr.contactosv3.util;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.konarr.contactosv3.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Esta clase proporciona CRUD y la clase DAO (Patro de diseño Data Access Object)
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    //Objeto DAO que se utilizan para acceder a la tabla Contacto
    private Dao<Contacto, Integer> contactoDAO = null;
    private RuntimeExceptionDao<Contacto, Integer> contactoRuntimeDAO = null;

    public DatabaseHelper(Context context) {
        //TODO: Al finalizar el helper crear archivo de configuración ORMLite
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    //Metodo invocado cuando la db es creada y se crean las tablas
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cnn) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(cnn, Contacto.class);
        }catch (SQLException ex){
            Log.e(DatabaseHelper.class.getSimpleName(), "Imposible crear la Base de Datos", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cnn, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade()");
            TableUtils.dropTable(cnn, Contacto.class, true);
            //despues de eliminar la tabla volvemos a crearla
            TableUtils.createTable(cnn, Contacto.class);
        }catch (SQLException ex){
            Log.e(DatabaseHelper.class.getSimpleName(), "Imposible eliminar la Base de Datos", ex);
            throw new RuntimeException(ex);
        }
    }

    public RuntimeExceptionDao<Contacto, Integer> getContactoRuntimeDAO() throws SQLException {
        //si el metodo no ha sido inicializado se inicializa ahora
        if(contactoDAO == null) contactoDAO = getDao(Contacto.class);
        return contactoRuntimeDAO;
    }

    public Dao<Contacto, Integer> getContactoDAO() throws SQLException {
        if (contactoRuntimeDAO == null) contactoRuntimeDAO = getRuntimeExceptionDao(Contacto.class);
        return contactoDAO;
    }

    @Override
    public void close() {
        super.close();
        contactoDAO = null;
        contactoRuntimeDAO = null;
    }
}
