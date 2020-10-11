package com.estebanmancera.tiendadb.servicio;

import java.util.List;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface serivicioInter {


    @GET("fooddata.json")
    Call<List<FoodData>> getAllData();


    // lets make our model class of json data.
}
