package com.example.footballzone;  // <-- Должно совпадать с `package` в AndroidManifest.xml

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.footballzone.api.ApiClient;
import com.example.footballzone.api.FootballApi;
import com.example.footballzone.models.Match;
import com.example.footballzone.models.MatchResponse;
import com.example.footballzone.adapters.MatchAdapter;


import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity {
    private List<Match> matchList = new ArrayList<>();  // Список матчей
    private MatchAdapter matchAdapter;  // Адаптер для RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация RecyclerView и адаптера
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        matchAdapter = new MatchAdapter(matchList);  // Инициализация адаптера
        recyclerView.setAdapter(matchAdapter);  // Установка адаптера

        fetchMatches();  // Загрузка матчей из API
    }

    private void fetchMatches() {
        String date = "2025-02-08";
        String league = "PL";

        FootballApi api = ApiClient.getClient().create(FootballApi.class);
        Call<MatchResponse> call = api.getMatches();

        call.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(@NonNull Call<MatchResponse> call, @NonNull Response<MatchResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("FETCH_MATCHES", "Данные получены: " + response.body().getMatches());

                    if (response.body() != null && response.body().getMatches() != null) {
                        matchList.clear();
                        matchList.addAll(response.body().getMatches());

                        matchAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("FETCH_MATCHES", "Ответ API пустой или пустой список матчей");
                    }
                } else {
                    Log.e("FETCH_MATCHES", "Ошибка API: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MatchResponse> call, @NonNull Throwable t) {
                Log.e("FETCH_MATCHES", "Ошибка загрузки: " + t.getMessage());
            }
        });
    }
}


