package com.example.smartparking.data.api;

import com.example.smartparking.data.model.AddUserResponse;

import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;
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
    Call<AddUserResponse> createUser(@Body UsersPojo user);

    @POST("/user/login")
    Call<GetUserResponse> getUserByName(@Body GetUserByNameRequest user);

    @PUT("/user/update")
    Call<AddUserResponse> updateUser(@Body GetUserResponse user);
//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
