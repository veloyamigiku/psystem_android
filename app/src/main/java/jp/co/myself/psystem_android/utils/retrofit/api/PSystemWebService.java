package jp.co.myself.psystem_android.utils.retrofit.api;

import jp.co.myself.psystem_android.utils.retrofit.entity.ResultIssueJWTForSignup;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PSystemWebService {

    @GET("issue_jwt_for_signup")
    Call<ResultIssueJWTForSignup> issueJwtForSignup(@Query("sample") String sample);

}
