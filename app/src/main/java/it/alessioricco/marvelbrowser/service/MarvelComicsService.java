package it.alessioricco.marvelbrowser.service;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import it.alessioricco.marvelbrowser.api.APIFactory;
import it.alessioricco.marvelbrowser.api.MarvelComicsAPI;
import it.alessioricco.marvelbrowser.api.RestAdapterFactory;
import it.alessioricco.marvelbrowser.injection.ObjectGraphSingleton;
import it.alessioricco.marvelbrowser.models.comics.Comics;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import st.lowlevel.storo.Storo;

public class MarvelComicsService {

    private static final int LIMIT = 100;

    @Inject
    RestAdapterFactory restAdapterFactory;

    public MarvelComicsService() {

        ObjectGraphSingleton.getInstance().inject(this);
    }

    public Observable<Comics> getComicsList() {

        final MarvelComicsAPI api = APIFactory.createMarvelAPI(restAdapterFactory.createJSONRestAdapter());

        return api.getComics(LIMIT);

    }
}
