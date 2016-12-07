package it.alessioricco.marvelbrowser.api;

import it.alessioricco.marvelbrowser.models.comics.Comics;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface MarvelComicsAPI {

    @GET("/v1/public/comics")
    Observable<Comics> getComics(@Query("limit") int limit);

    @GET("/v1/public/comics/{comicId}")
    Observable<Comics> getComic(@Path("comicId") String comicId);

}
