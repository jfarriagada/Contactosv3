package com.example.konarr.contactosv3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.konarr.contactosv3.util.ContactReceiver;
import com.example.konarr.contactosv3.util.Contacto;
import com.example.konarr.contactosv3.util.textChangedListener;

public class CrearContacto extends Fragment implements View.OnClickListener {

    private EditText txt_nombre, txt_telefono, txt_email, txt_direccion;
    private ListView contactsListView;
    private Button btn_agregar;
    private int request_code = 1;
    private ImageView img_original;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflater carga el fragment_crear_contacto
        View rootView = inflater.inflate(R.layout.fragment_crear_contacto, container, false);
        inicializarComponentes(rootView);
        return rootView;
    }

    private void inicializarComponentes(final View view) {
        txt_nombre = (EditText) view.findViewById(R.id.txt_nombre);
        txt_telefono = (EditText) view.findViewById(R.id.txt_telefono);
        txt_email = (EditText) view.findViewById(R.id.txt_email);
        txt_direccion = (EditText) view.findViewById(R.id.txt_direccion);
        img_original = (ImageView) view.findViewById(R.id.img_original);
        img_original.setOnClickListener(this);
        //se ejecuta cada vez que el user escribe o realiza algo
        txt_nombre.addTextChangedListener(new textChangedListener(){
            @Override
            public void onTextChanged(CharSequence seq, int i, int i2, int i3) {
                btn_agregar.setEnabled(!seq.toString().trim().isEmpty());
            }
        });
        btn_agregar = (Button) view.findViewById(R.id.btn_agregar);
        btn_agregar.setOnClickListener(this);
    }

    public void onClick(View view) {
        if ( view == btn_agregar){
            agregarContactos(
                    txt_nombre.getText().toString(),
                    txt_telefono.getText().toString(),
                    txt_email.getText().toString(),
                    txt_direccion.getText().toString(),
                    //obtenemos el atributo TAG con la Uri de la imagen
                    //TAG es un object y puede almacenar cualquier cosa
                    String.valueOf(img_original.getTag())
            );
            String msg = String.format("%s ha sido agregado.", txt_nombre.getText());
            Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            btn_agregar.setEnabled(false);
            limpiarCampos();
        }else if ( view == img_original ){
            Intent intent = null;
            //verificando la version de la plataforma
            if(Build.VERSION.SDK_INT < 19){
                //android JellyBean 4.3 y anteriores
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
            }else {
                //android kitkat 4.4 o superior
            }
            intent.setType("image/*");
            startActivityForResult(intent, request_code);
        }
    }

    private void agregarContactos(String nombre, String telefono, String email, String direcccion, String image_uri) {
        Contacto nuevo = new Contacto(nombre, telefono, email, direcccion, image_uri);
        //Intent es un canal de comunicacion entre los diferentes componentes
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion", ContactReceiver.CONTACTO_AGREGADO);
        intent.putExtra("datos", nuevo);
        getActivity().sendBroadcast(intent);
    }

    private void limpiarCampos() {
        txt_nombre.getText().clear();
        txt_telefono.getText().clear();
        txt_email.getText().clear();
        txt_direccion.getText().clear();
        //reestablecer la imagen predeterminada del contacto
        img_original.setImageResource(R.drawable.ic_original);
        txt_nombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == request_code){
            img_original.setImageURI(data.getData());
            //utilizamos el atributo TAG para almacenar la Uri al archivo seleccionado
            img_original.setTag(data.getData());
        }
    }

}
