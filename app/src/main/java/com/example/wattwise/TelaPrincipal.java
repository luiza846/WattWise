package com.example.wattwise;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TelaPrincipal extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    public ListView listViewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_principal);

        setContentView(R.layout.activity_tela_principal);
        listViewDados = findViewById(R.id.listViewDados);

        criarBancoDados();
        inserirDadosTemp();
        listarDados();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void criarBancoDados(){
        try {
            bancoDados = openOrCreateDatabase("crudapp", MODE_PRIVATE, null);
            bancoDados.execSQL(
                    "CREATE TABLE IF NOT EXISTS usuario (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "nome TEXT, " +
                            "email TEXT, " +
                            "senha TEXT" +
                            ")"
            );
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarDados(){
        try {
            bancoDados = openOrCreateDatabase("crudapp", MODE_PRIVATE, null);
            Cursor meuCursor = bancoDados.rawQuery("SELECT id, nome FROM usuario", null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter meuAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    linhas
            );

            listViewDados.setAdapter(meuAdapter);
            meuCursor.moveToFirst();
            while(meuCursor!=null){
                linhas.add(meuCursor.getString(1));
                meuCursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirDadosTemp(){
        try {
            bancoDados = openOrCreateDatabase("crudapp", MODE_PRIVATE, null);
            String sql = "INSERT INTO usuario (nome) VALUES (?)";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);

            // so para teste
            stmt.bindString(1,"Usuario 1");
            stmt.executeInsert();

            stmt.bindString(1,"Usuario 2");
            stmt.executeInsert();

            stmt.bindString(1,"Usuario 3");
            stmt.executeInsert();

            bancoDados.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //adcionar o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }
}