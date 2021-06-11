package com.example.listtareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listtareas.entities.Tarea;
import com.example.listtareas.services.TareaAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailTareaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tarea);

        String id = getIntent().getStringExtra("id");

        TextView tvFecha = findViewById(R.id.tvDateDetail);
        TextView tvDescripcion = findViewById(R.id.tvDescriptionDetail);
        TextView tvId = findViewById(R.id.tvIdDetail);

        ImageView imageViewDone = findViewById(R.id.imgIsDoneDetail);

        TextView tvAssigned = findViewById(R.id.tvAsignedDetail);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        TareaAPIService tareaAPIService = retrofit.create(TareaAPIService.class);

        Call<Tarea> tareaDetailCall = tareaAPIService.getFindTarea(id);
        Call<Tarea> tareaDoneCall = tareaAPIService.postDoneTarea(id);

        tareaDetailCall.enqueue(new Callback<Tarea>() {
            @Override
            public void onResponse(Call<Tarea> call, Response<Tarea> response) {
                Tarea tarea = response.body();

                tvId.setText(tarea.id);
                tvFecha.setText(tarea.date);
                tvAssigned.setText(tarea.assigned);
                tvDescripcion.setText(tarea.description);

            }

            @Override
            public void onFailure(Call<Tarea> call, Throwable t) {

            }
        });


        imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareaDoneCall.enqueue(new Callback<Tarea>() {
                    @Override
                    public void onResponse(Call<Tarea> call, Response<Tarea> response) {
                        Intent intent = new Intent( DetailTareaActivity.this, MainActivity.class );

                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Tarea> call, Throwable t) {

                    }
                });
            }
        });


    }

}