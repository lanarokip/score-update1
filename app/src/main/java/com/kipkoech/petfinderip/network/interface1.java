package com.kipkoech.petfinderip.network;

import com.kipkoech.petfinderip.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface interface1 {
    @GET("animals")
    Call<SearchResponse> getPets(
            @Query(" ") String type
    );
}
