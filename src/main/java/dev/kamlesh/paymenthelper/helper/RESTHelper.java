package dev.kamlesh.paymenthelper.helper;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class RESTHelper {

    private final String token;
    private final String endpoint;

    public RESTHelper(@Value("${paysafe.endpoint}") String endpoint, @Value("${paysafe.token}") String token) {
        this.token = token;
        this.endpoint = endpoint;
    }

    @Bean
    Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("authorization", token)
                        .build();
                return chain.proceed(request);
            }
        };
    }


    @Bean
    @Autowired
    OkHttpClient provideOkHttpClient(final Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    @Autowired
    public Retrofit provideRESTClient(final OkHttpClient client) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
