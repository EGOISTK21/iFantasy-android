package xyz.egoistk21.iFantasy.util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;
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

    public interface RecruitInfoApi {
        @Headers("User-Agent:iFantasy-android")
        @GET("recruit/get_recruit_info")
        Observable<HttpResult<RecruitInfo>> getRecruitInfo(@Query("user_id") int user_id);
    }

    public static RecruitInfoApi getRecruitInfoApi() {
        return sRetrofit.create(RecruitInfoApi.class);
    }

    public interface OneRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/one_recruit")
        @FormUrlEncoded
        Observable<HttpResult<RecruitResult>> recruitOne(@Field("user_id") int user_id);
    }

    public static OneRecruitApi getOneRecruitApi() {
        return sRetrofit.create(OneRecruitApi.class);
    }

    public interface FiveRecruitApi {
        @Headers("User-Agent:iFantasy-android")
        @POST("recruit/five_recruit")
        @FormUrlEncoded
        Observable<HttpResult<ArrayList<RecruitResult>>> recruitFive(@Field("user_id") int user_id);
    }

    public static FiveRecruitApi getFiveRecruitApi() {
        return sRetrofit.create(FiveRecruitApi.class);
    }

    public interface RecruitShowPlayerApi {
        @Headers("User-Agent:iFantasy-android")
        @GET("recruit/show_all_payer")
        Observable<HttpResult<ArrayList<RawPlayer>>> showPlayer(@Query("pos") int position,
                                                                @Query("type") int type);
    }

    public static RecruitShowPlayerApi getRecruitShowPlayerApi() {
        return sRetrofit.create(RecruitShowPlayerApi.class);
    }

}
