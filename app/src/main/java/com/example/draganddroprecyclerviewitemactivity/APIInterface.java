package com.example.draganddroprecyclerviewitemactivity;


import com.example.draganddroprecyclerviewitemactivity.model.DashboardResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/bins/dzxgl")
    Call<DashboardResponse> getDashboardItems();

}

//      https://api.myjson.com/bins/dzxgl
