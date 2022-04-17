package com.example.smartparking.data.api;

import com.example.smartparking.data.model.GenericResponse;

import com.example.smartparking.data.model.GetParkingByLotIdRequest;
import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;
import com.example.smartparking.data.model.ParkingLotPojo;
import com.example.smartparking.data.model.UsersPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {

    @GET("/user")
    Call<List<GetUserResponse>> getUsers();

    @POST("/user/add")
    Call<GenericResponse> createUser(@Body UsersPojo user);

    @POST("/user/login")
    Call<GetUserResponse> getUserByName(@Body GetUserByNameRequest user);

    @PUT("/user/update")
    Call<GenericResponse> updateUser(@Body GetUserResponse user);



    @GET("/parkinglot")
    Call<List<ParkingLotPojo>> getParkingLots();

    @POST("/parkinglot/add")
    Call<GenericResponse> createParkingLot(@Body ParkingLotPojo parking);

    @POST("/parkinglot/find")
    Call<ParkingLotPojo> getParkingByLotIde(@Body GetParkingByLotIdRequest lotId);

    @PUT("/parkinglot/update")
    Call<GenericResponse> updateParkingLot(@Body ParkingLotPojo parking);


}
