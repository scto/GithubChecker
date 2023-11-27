package qz.github.model;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requestsku implements Callback<ProfilInfo> {
    private String url;
    private String username;
    private Retrofit retrofit;
    public ProfilInfo profilInfo;
    private Context ctx;
    private ResultData mresult;
    public ProfilInfo result;
    public Requestsku(Context ctx, String url, String username) {
        this.url = url;
        this.ctx = ctx;
        this.username = username;
        init();
    }

    private void init() {
        this.retrofit =
                new Retrofit.Builder()
                        .baseUrl(this.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        this.retrofit.create(Github.class).getInfo(this.username)
        .enqueue(this);
    }

    public ProfilInfo getProfilInfo() {
        return this.profilInfo;
    }

    public void setProfilInfo(ProfilInfo profilInfo) {
        this.profilInfo = profilInfo;
    }

    @Override
    public void onResponse(Call<ProfilInfo> arg0, Response<ProfilInfo> responses) {
        if(!responses.isSuccessful()){
            return;
        }
        mresult.ResponseData(responses.body());
    }

    @Override
    public void onFailure(Call<ProfilInfo> arg0, Throwable arg1) {
    }
    public interface ResultData {
        void ResponseData(ProfilInfo profilinfo);
    }
    public void setOnResultListener(ResultData resultData){
        mresult = resultData;
    }
}
