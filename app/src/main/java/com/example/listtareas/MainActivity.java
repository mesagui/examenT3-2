package com.example.listtareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listtareas.adapter.TareaAdapter;
import com.example.listtareas.entities.Tarea;
import com.example.listtareas.services.TareaAPIService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TareaAPIService tareaAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //metodo obtener todas las tareas
        getALLTareas();


        //boton nuevo
        FloatingActionButton btnNuevo = findViewById(R.id.btnAddTarea);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormTareaActivity.class);
                startActivity(intent);
            }
        });

    }



    private void getALLTareas(){
        tareaAPIService = retrofit.create(TareaAPIService.class);

        Call<List<Tarea>> tareasCall = tareaAPIService.getAllTareas();

        tareasCall.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {

                List<Tarea> lstTarea = response.body();

                RecyclerView recyclerView = findViewById(R.id.rvTareas);
                recyclerView.setLayoutManager( new LinearLayoutManager( MainActivity.this ));

                TareaAdapter adapter = new TareaAdapter(lstTarea);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Tarea>> call, Throwable t) {

                Log.i("MAIN_APP", "ERROR Conexion" );
            }
        });
    }
}