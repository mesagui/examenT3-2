package com.example.listtareas.services;

import com.example.listtareas.entities.Tarea;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TareaAPIService {


    @GET("tareas/00039842")
    Call<List<Tarea>> getAllTareas ();


    @POST("tareas/00039842/crear")
    Call<Tarea> postTarea(@Body Tarea tarea);


    @GET("tareas/{id}/mostrar")
    Call<Tarea> getFindTarea ( @Path("id") String id );


    @POST("tareas/{id}/done")
    Call<Tarea> postDoneTarea( @Path("id") String id);


}
