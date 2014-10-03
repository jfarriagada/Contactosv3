package com.example.konarr.contactosv3.util;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.konarr.contactosv3.R;

import java.util.List;

/**
 * Created by konarr on 29-09-14.
 */
public class ContactListAdapter extends ArrayAdapter<Contacto> {
    private Activity ctx;

    public ContactListAdapter(Activity context, List<Contacto> contactos) {
        super(context, R.layout.listview_item, contactos);
        this.ctx = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if ( view == null)
            view = ctx.getLayoutInflater().inflate(R.layout.listview_item,parent, false);

        Contacto actual = this.getItem(position);
        inicializarCamposDeTexto(view, actual);
        return view;
    }

    private void inicializarCamposDeTexto(View view, Contacto actual) {
        TextView txt = (TextView) view.findViewById(R.id.txt_nombre);
        txt.setText(actual.getNombre());
        txt = (TextView) view.findViewById(R.id.txt_telefono);
        txt.setText(actual.getTelefono());
        txt = (TextView) view.findViewById(R.id.txt_email);
        txt.setText(actual.getEmail());
        txt = (TextView) view.findViewById(R.id.txt_direccion);
        txt.setText(actual.getDireccion());
        ImageView img_contacto = (ImageView) view.findViewById(R.id.img_contacto);
        img_contacto.setImageURI(Uri .parse(actual.getImageUri()));
    }

}
