package com.example.footballzone.api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import com.example.footballzone.models.MatchResponse;


public interface FootballApi {
    @Headers("X-Auth-Token: 19e70883f1c142b795aaf278204daf3cq")
    @GET("matches")
    Call<MatchResponse> getMatches();
}
