package com.github.Richard.RxCache;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author Charlie
 * @Date 2022/7/19 14:49
 *
 * 接口管理
 */
public interface ApiService {






    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("passport/accesstokens?grantType=zjCardVerificationCode")
    Observable<BasicResponse<LoginBean>> login(@Field("cellphone") String cellphone,
                                               @Field("verificationCode") String verificationCode);


    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("passport/verificationcodes")
    Observable<BasicResponse> getVerificationCode(@Field("cellphone") String cellphone);


    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("passport/verificationcodes")
    io.reactivex.rxjava3.core.Observable<BasicResponse> getVerification(@Field("cellphone") String cellphone);



}
