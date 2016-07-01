package gardeshgary.khozestan.service;

import java.io.IOException;

import gardeshgary.khozestan.StaticFields;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ServiceGenerator {
	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
	private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

	private static Retrofit.Builder builder =
			new Retrofit.Builder()
					.baseUrl(StaticFields.SERVER_URL)
					.addConverterFactory(SimpleXmlConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().clear();
		httpClient.addInterceptor(logging);
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

		OkHttpClient client = httpClient.build();
		Retrofit retrofit = builder.client(client).build();
		return retrofit.create(serviceClass);
    }
}
