package it.alessioricco.marvelbrowser.mock;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;

import javax.inject.Inject;

import it.alessioricco.marvelbrowser.api.RestAdapterFactory;
import it.alessioricco.marvelbrowser.injection.TestObjectGraphSingleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class ShadowRestAdapterFactory extends RestAdapterFactory {

    @Inject
    MockAppWebServer mockWebServer;

    public ShadowRestAdapterFactory(Context context){
        super(context);
        TestObjectGraphSingleton.getInstance().inject(this);
    }

    @Override
    protected String getBaseUrl() {
        return mockWebServer.getMockWebServer().url("").toString();
    }

    @Override
    public Retrofit createJSONRestAdapter() {

        final RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

    }
}
