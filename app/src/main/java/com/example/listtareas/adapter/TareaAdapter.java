package com.example.listtareas.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listtareas.DetailTareaActivity;
import com.example.listtareas.R;
import com.example.listtareas.entities.Tarea;
import com.example.listtareas.services.TareaAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    List<Tarea> tareas;

    public TareaAdapter( List<Tarea> tareas ){ this.tareas = tareas; }

    @Override
    public TareaAdapter.TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);

        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder( TareaAdapter.TareaViewHolder holder, int position) {

        View view = holder.itemView;

        Tarea tarea = tareas.get(position);

        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvDescription = view.findViewById(R.id.tvDescription);

        ImageButton imageViewImagen = view.findViewById(R.id.imgIsDoneDetail);

        tvDate.setText(tarea.getDate());
        tvDescription.setText(tarea.getDescription());


        if ( tarea.isDone == 1 ){
            imageViewImagen.setImageResource(R.drawable.ic_baseline_playlist_add_check_24);
        }
        else{
           // imageViewImagen.setImageResource(R.drawable.ic_baseline_playlist_add_check_24);
        }

        /*if (tarea.isDone == 1 ) imageViewImagen.setImageResource(R.drawable.ic_baseline_playlist_add_check_24);
        else imageViewImagen.setImageResource(R.drawable.ic_baseline_access_time_24);
*/
        // para el detalle
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( view.getContext(), DetailTareaActivity.class);
                intent.putExtra("id", tarea.id);

                view.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {        return tareas.size();     }

    public static class TareaViewHolder extends RecyclerView.ViewHolder{
        public TareaViewHolder (View itemView ) { super(itemView); }
    }
}
