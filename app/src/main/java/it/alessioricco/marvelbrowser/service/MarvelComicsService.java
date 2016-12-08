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
private final String CACHE_KEY = "COMICS";

    @Inject
    RestAdapterFactory restAdapterFactory;

    public MarvelComicsService() {

        ObjectGraphSingleton.getInstance().inject(this);
    }

    public Observable<Comics> getComicsList() {

        final MarvelComicsAPI api = APIFactory.createMarvelAPI(restAdapterFactory.createJSONRestAdapter());

        return api.getComics(LIMIT);

//        return Observable.create(new Observable.OnSubscribe<Comics>() {
//            @Override
//            public void call(final Subscriber<? super Comics> subscriber) {
//
//                final Boolean expired = Storo.hasExpired(CACHE_KEY).execute();
//                if (expired != null) {
//
//                    if (!expired) {
//                        Storo.get(CACHE_KEY, Comics.class).async(new st.lowlevel.storo.model.Callback<Comics>() {
//                            @Override
//                            public void onResult(Comics comics) {
//                                //Log.i(TAG, String.format("received cached value for index %d ", sample.getIndex()));
//                                subscriber.onNext(comics);
//                                subscriber.onCompleted();
//                            }
//                        });
//                        return;
//                    }
//                    // expired, so we'll delete the key
//                    Storo.delete(CACHE_KEY);
//                }
//
//                // is expired
//                //return api.getComics(LIMIT);
//                final Observable<Comics> comic = api.getComics(LIMIT);
//                comic.doOnNext(new Action1<Comics>() {
//                    @Override
//                    public void call(Comics comics) {
//                        Storo.put(CACHE_KEY, comics)
//                                .setExpiry(5, TimeUnit.MINUTES)
//                                .execute();
//                        subscriber.onNext(comics);
//                        subscriber.onCompleted();
//                    }
//                }).doOnCompleted(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                });
//
//            }
//        });



    }
}
