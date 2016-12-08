package it.alessioricco.marvelbrowser.mock;

import android.content.Context;

import org.robolectric.RuntimeEnvironment;

import java.io.IOException;

import it.alessioricco.marvelbrowser.utils.JsonUtils;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class MockAppWebServer {

    final MockWebServer mockWebServer;

    MockResponse newResponse;
    public void setMockResponse(final MockResponse mockResponse) {
        newResponse = mockResponse;
    }

    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            if (newResponse != null) {
                return newResponse;
            }

            Context context = RuntimeEnvironment.application;

            if (request.getPath().equals("/v1/public/comics")){
                final String response = JsonUtils.loadJSONFromAsset(context,"");
                return new MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .addHeader("Cache-Control", "no-cache")
                        .setBody(response);
            }

            //HTTP/1.1 404 Client Error
            return new MockResponse().setResponseCode(404);

        }
    };

    public MockAppWebServer() {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(dispatcher);
    }

    public void start() throws IOException {
        mockWebServer.start();
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    public void shutdown(){
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}