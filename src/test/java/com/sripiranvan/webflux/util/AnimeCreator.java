package com.sripiranvan.webflux.util;

import com.sripiranvan.webflux.model.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Tensei Shitars Slime Data Ken")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .id(1)
                .name("Tensei Shitars Slime Data Ken")
                .build();
    }

    public static Anime createAnimeValidUpdateAnime(){
        return Anime.builder()
                .id(1)
                .name("Tensei Shitars Slime Data Ken")
                .build();
    }
}
