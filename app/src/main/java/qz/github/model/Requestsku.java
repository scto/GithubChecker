package qz.github.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requestsku{
    private String url = "https://api.github.com/";
    private String username;
    private Retrofit retrofit;
    public ProfilInfo result;
    
    public Requestsku(String username) {
        this.username = username;
    }
    
    public void init() {
        this.retrofit =
                new Retrofit.Builder()
                        .baseUrl(this.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        Call<ProfilInfo> call = retrofit.create(Github.class).getInfo(this.username);
        try {
            this.result = call.execute().body();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ProfilInfo getResult(){
        return this.result;
    }
}
