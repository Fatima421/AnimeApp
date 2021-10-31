package com.example.SyedFatima_AnimeApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.SyedFatima_AnimeApp.Model.Anime;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Anime> arrayAnime;
    private Context context;

    public RecyclerViewAdapter(Context c, ArrayList<Anime> arrN){
        this.arrayAnime = arrN;
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTxt.setText(arrayAnime.get(position).getName());
        holder.genreTxt.setText(arrayAnime.get(position).getGenre());
        holder.rankingTxt.setText(Integer.toString(arrayAnime.get(position).getRanking()));

        String imageName = arrayAnime.get(position).getImageName();
        int res = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.animeImage.setImageResource(res);

    }

    @Override
    public int getItemCount() {
        return arrayAnime.size();
    }

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

