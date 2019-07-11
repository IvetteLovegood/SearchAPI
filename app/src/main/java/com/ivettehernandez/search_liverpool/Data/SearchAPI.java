package com.ivettehernandez.search_liverpool.Data;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivettelovegood on 2019-07-10.
 */


public interface SearchAPI {

    @GET("/tienda?")
    Call<JsonElement> requestTienda(@Query("s") String id, @Query("d3106047a194921c01969dfdec083925") String format);

}
