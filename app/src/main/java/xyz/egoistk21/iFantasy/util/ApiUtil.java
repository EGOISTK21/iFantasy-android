package xyz.egoistk21.iFantasy.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

public class ApiUtil {
    public static final int FILTER_TIMEOUT = 1;
    private static final int TIMEOUT = 5;
    private static final String ROOT = "http://139.198.14.181/api/v1/";
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

    private ApiUtil() {
    }

    public interface VerificationApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/verification")
        @FormUrlEncoded
        Observable<HttpResult<User>> verify(@Field("phone") String phone,
                                            @Field("zone") String zone,
                                            @Field("code") String code);
    }

    public static VerificationApi getVerifyCodeAPI() {
        return sRetrofit.create(VerificationApi.class);
    }

    public interface RegisterApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/register")
        @FormUrlEncoded
        Observable<HttpResult<User>> register(@Header("Authorization") String temptoken,
                                              @Field("phone") String phone,
                                              @Field("nickname") String nickname);
    }

    public static RegisterApi getRegisterApi() {
        return sRetrofit.create(RegisterApi.class);
    }

    public interface LoginApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("user/login")
        @FormUrlEncoded
        Observable<HttpResult<User>> login(@Header("Authorization") String logintoken,
                                           @Field("phone") String phone);
    }

    public static LoginApi getLoginApi() {
        return sRetrofit.create(LoginApi.class);
    }

//    public interface TestOnlineApi {
//        @Headers("User-Agent:iFantasy-android")
//        @POST("user/online")
//        @FormUrlEncoded
//        Observable<HttpResult<String>> isOnline(@Header("Authorization") String accesstoken,
//                                                @Field("user_id") int id);
//    }
//
//    public static TestOnlineApi getTestOnlineApi() {
//        return sRetrofit.create(TestOnlineApi.class);
//    }

}
