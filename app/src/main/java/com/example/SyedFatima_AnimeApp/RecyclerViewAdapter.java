package com.example.SyedFatima_AnimeApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.SyedFatima_AnimeApp.DB.AnimeDBHelper;
import com.example.SyedFatima_AnimeApp.Model.Anime;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Anime> arrayAnime;
    private Context context;
    private AnimeDBHelper dbHelper;
    private SQLiteDatabase db;

    //constructor
    public RecyclerViewAdapter(Context c, ArrayList<Anime> arrN, AnimeDBHelper dbHelper, SQLiteDatabase db){
        this.arrayAnime = arrN;
        this.context = c;
        this.dbHelper = dbHelper;
        this.db = db;
    }

    //Creating a new onCreateViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Setting values to every field item by item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTxt.setText(arrayAnime.get(position).getName());
        holder.genreTxt.setText(arrayAnime.get(position).getGenre());
        holder.rankingTxt.setText(Integer.toString(arrayAnime.get(position).getRanking()));
        //for image
        String imageName = arrayAnime.get(position).getImageName();
        int res = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.animeImage.setImageResource(res);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity app = (AppCompatActivity) v.getContext();
                FragmentItemDetail fragmentItemDetail = new FragmentItemDetail(dbHelper, db);

                Bundle bundle = new Bundle();
                bundle.putSerializable("anime", arrayAnime.get(position));
                fragmentItemDetail.setArguments(bundle);

                //showing the new fragment
                app.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentItemDetail).commit();
            }
        });
    }

    //counting the items in a anime list
    @Override
    public int getItemCount() {
        return arrayAnime.size();
    }

    //Creating properties and finding them in the view
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView genreTxt;
        TextView rankingTxt;
        ImageView animeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.animeNameTxt);
            genreTxt = itemView.findViewById(R.id.animeGenreTxt);
            rankingTxt = itemView.findViewById(R.id.animeRankingTxt);
            animeImage = itemView.findViewById(R.id.animeImage);
        }
    }
}

