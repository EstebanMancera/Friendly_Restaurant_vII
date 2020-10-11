package com.estebanmancera.tiendadb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class inicio extends AppCompatActivity implements View.OnClickListener {
    Button btneditarus, btneliminarus, btnmostarus, btnsalir, btncomidas;
    TextView nombre;
    int id = 0;
    usuario u;
    daousuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        nombre = (TextView)findViewById(R.id.txtnomusu);
        btneditarus=(Button)findViewById(R.id.btneditarusu);
        btneliminarus=(Button)findViewById(R.id.btneliminarusu);
        btnmostarus=(Button)findViewById(R.id.btnmostrarusu);
        btnsalir=(Button)findViewById(R.id.btnsalirusu);
        btncomidas=(Button)findViewById(R.id.btncomidas);


        btneditarus.setOnClickListener(this);
        btneliminarus.setOnClickListener(this);
        btnmostarus.setOnClickListener(this);
        btnsalir.setOnClickListener(this);



        btncomidas.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        dao= new daousuario(this);
        u=dao.getUsuarioById(id);
        nombre.setText(u.getNombre()+" "+u.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btneditarusu:
                Intent a= new Intent(inicio.this,editar.class);
                a.putExtra("id", id);
                startActivity(a);
                break;
            case R.id.btneliminarusu:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                b.setMessage("Estas seguro de eliminar tu cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteUsuario(id)){
                            Toast.makeText(inicio.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                            Intent a= new Intent(inicio.this,MainActivity.class);
                            startActivity(a);
                            finish();
                        }else{
                            Toast.makeText(inicio.this, "Se cancelo la eliminacion", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btnmostrarusu:
                Intent i3= new Intent(inicio.this,mostrar.class);
                startActivity(i3);
                break;
            case R.id.btnsalirusu:
                Intent i4= new Intent(inicio.this,MainActivity.class);
                startActivity(i4);
                finish();
                break;
            case R.id.btncomidas:
                Intent i5= new Intent(inicio.this,comidas.class);
                startActivity(i5);
                break;

        }
    }
}