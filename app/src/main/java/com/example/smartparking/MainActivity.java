package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button registerBtn;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRegister();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

//        Call<List<GetUserResponse>> call = apiInterface.getUsers();
//        call.enqueue(new Callback<List<GetUserResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetUserResponse>> call, Response<List<GetUserResponse>> response) {
//                Log.e("MainActivity<>", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<GetUserResponse>> call, Throwable t) {
//
//            }
//        });
//    }

//        GetUserByNameRequest user = new GetUserByNameRequest("Karan Dabas");
//    Call<GetUserResponse> call = apiInterface.getUserByName(user);
//        call.enqueue(new Callback<GetUserResponse>() {
//            @Override
//            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
//                GetUserResponse user1 = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<GetUserResponse> call, Throwable t) {
//
//            }
//        });

}

    private void setupRegister(){
        loginBtn = (Button) findViewById(R.id.button1);
        registerBtn = (Button) findViewById(R.id.button2);
    }
}
