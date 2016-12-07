package it.alessioricco.marvelbrowser.api;

import android.util.Log;

import java.io.IOException;

import it.alessioricco.marvelbrowser.injection.ObjectGraphSingleton;
import it.alessioricco.marvelbrowser.utils.MD5Utils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


/**
 * factory for calling the public flickr endpoint
 * the adapter will return an Observable
 * the request will be logged on the console
 *
 * for testing this class with a mock server
 * we should override getBaseUrl()
 *
 * todo: make it more abstract (make it indipendent from the base url)
 */
public class RestAdapterFactory {

    protected String getBaseUrl() {
        return "http://gateway.marvel.com";
    }

    public RestAdapterFactory() {
        ObjectGraphSingleton.getInstance().inject(this);
    }

    /**
     * build an OkHttp client
     * mostly used for logging the correct endpoint url
     *
     * todo: we can inject it
     * @return okhttp3.OkHttpClient
     */
    private okhttp3.OkHttpClient createClient() {
        return new okhttp3.OkHttpClient()
                .newBuilder()
                .addInterceptor(new okhttp3.Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Request request = chain.request();
                        Log.i("rest", request.url().toString());
                        return chain.proceed(chain.request());
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final String timeStamp = String.valueOf(System.currentTimeMillis());
                        final HttpUrl url = chain.request()
                                .url()
                                .newBuilder()
                                .addQueryParameter("ts", timeStamp)
                                .addQueryParameter("apikey", APIKeys.PUBLIC)
                                .addQueryParameter("hash", MD5Utils.convertToMd5(timeStamp + APIKeys.PRIVATE + APIKeys.PUBLIC))
                                .build();
                        return chain.proceed(chain.request().newBuilder().url(url).build());
                    }
                })
                .build();
    }

    // todo: refactor to be a class and not a method

    /**
     *  build a REST Json adapter
     *  able to return Observable results
     *
     * @return
     */
    public Retrofit createJSONRestAdapter() {

        final RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(createClient())
                .build();

    }

}
