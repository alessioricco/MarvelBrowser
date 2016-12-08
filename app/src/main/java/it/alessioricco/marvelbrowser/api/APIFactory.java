package it.alessioricco.marvelbrowser.api;


import retrofit2.Retrofit;

/**
 * Factory for (all) the APIs called in the app
 */
public class APIFactory {

    public static MarvelComicsAPI createMarvelAPI(Retrofit retrofit) {
        return retrofit.create(MarvelComicsAPI.class);
    }

}
