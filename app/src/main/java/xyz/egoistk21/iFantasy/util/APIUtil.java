package xyz.egoistk21.iFantasy.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.egoistk21.iFantasy.bean.HttpResult;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

public class APIUtil {
    public static final int FILTER_TIMEOUT = 1;
    private static final int TIMEOUT = 5;
    private static final String ROOT = "http://ifantasy.ml:5000/api/v1/";
    private static OkHttpClient sOkHttpClient =
            new OkHttpClient
                    .Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
    private static Retrofit sRetrofit =
            new Retrofit
                    .Builder()
                    .client(sOkHttpClient)
                    .baseUrl(ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

    private APIUtil() {
    }

    public interface verifyCodeAPI {
        @Headers("User-Agent:iFantasy-android")
        @POST("login")
        @FormUrlEncoded
        Observable<HttpResult> verify(@Field("phone") String phone,
                                      @Field("zone") String zone,
                                      @Field("code") String code);
    }

    public static verifyCodeAPI getVerifyCodeAPI() {
        return sRetrofit.create(verifyCodeAPI.class);
    }

}
