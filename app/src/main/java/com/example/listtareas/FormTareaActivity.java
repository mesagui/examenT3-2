package com.example.listtareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listtareas.entities.Tarea;
import com.example.listtareas.services.TareaAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tarea);

        EditText editTextDate = findViewById(R.id.edtFechaTarea);
        EditText editTextDescription = findViewById(R.id.edtDescripcionTarea);

        Button btnGuardar = findViewById(R.id.btnSubmitTarea);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TareaAPIService tareaAPIService = retrofit.create(TareaAPIService.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String description = editTextDescription.getText().toString();

                Tarea tareaa = new Tarea(date, description);

                Call <Tarea> tareaCall = tareaAPIService.postTarea(tareaa);

                tareaCall.enqueue(new Callback<Tarea>() {
                    @Override
                    public void onResponse(Call<Tarea> call, Response<Tarea> response) {

                        Log.i("APP", "SE CREO CORRECTAMENTE");

                    }

                    @Override
                    public void onFailure(Call<Tarea> call, Throwable t) {


                        Log.i("APP", "ERROR APP");

                    }
                });

                Intent intent = new Intent(FormTareaActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });



    }
}