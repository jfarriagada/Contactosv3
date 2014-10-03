package com.example.konarr.contactosv3;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.konarr.contactosv3.util.ContactListAdapter;
import com.example.konarr.contactosv3.util.ContactReceiver;
import com.example.konarr.contactosv3.util.Contacto;

import java.util.ArrayList;


public class ListaContacto extends Fragment {

    private ArrayAdapter<Contacto> adapter;
    private ListView contact_list;
    private ContactReceiver receiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista__contacto, container, false);
        inicializarComponenter(rootView);
        //habilita el Actionbar en este fragment para tener botones
        setHasOptionsMenu(true);
        return rootView;
    }

    private void inicializarComponenter(View view) {
        contact_list = (ListView) view.findViewById(R.id.listView);
        adapter = new ContactListAdapter(getActivity(), new ArrayList<Contacto>());
        //se configura para que el adapter notifique cambios en el dataset automaticamente
        adapter.setNotifyOnChange(true);
        contact_list.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        //se ejecuta cada vez que aparece el fragment
        super.onResume();
        receiver = new ContactReceiver(adapter);
        getActivity().registerReceiver(receiver, new IntentFilter("listacontactos"));

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_eliminar_contacto: eliminarContacto(item); return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void eliminarContacto(MenuItem item) {
        SparseBooleanArray array = contact_list.getCheckedItemPositions();
        ArrayList<Contacto> seleccion = new ArrayList<Contacto>();
        for (int i=0; i < array.size(); i++){
            int posicion = array.keyAt(i);
            if (array.valueAt(i)) seleccion.add(adapter.getItem(posicion));
            Intent intent = new Intent("listacontactos");
            intent.putExtra("operacion", ContactReceiver.CONTACTO_ELIMINADO);
            intent.putExtra("datos", seleccion);
            getActivity().sendBroadcast(intent);
            contact_list.clearChoices();
        }
    }

}
