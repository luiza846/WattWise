package com.example.wattwise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editNome;
    EditText EditNome;
    Button btnCadasEletrodomestico;

    Button btnCriarConta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    //checar o que o usuario clicou - tela principal

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemEditar) {
            Toast.makeText(MainActivity.this, "Você clicou em editar", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    //acessar a tela principal
    public void telaPrincipal(View view) {
        Intent in = new Intent(MainActivity.this, TelaPrincipal.class);
        startActivity(in);
    }

    //acessar a tela criar conta
    public void criarConta(View view) {
        Intent in = new Intent(MainActivity.this, CriarConta.class);
        startActivity(in);
    }

}