package com.example.SyedFatima_AnimeApp.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Anime {
    private static Anime anime;
    String name;
    String genre;
    int ranking;
    String imageName = "userdefaultimage";

    public Anime(String name, String genre, int ranking) {
        this.name = name;
        this.genre = genre;
        this.ranking = ranking;
    }

    public Anime(String name, String genre, int ranking, String imageName) {
        this.name = name;
        this.genre = genre;
        this.ranking = ranking;
        this.imageName = imageName;
    }

    public static Anime getAnime() {
       return anime;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getRanking() {
        return ranking;
    }

    public String getImageName() { return imageName; }
}
