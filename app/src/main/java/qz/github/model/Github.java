package qz.github.model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github {
    @GET("users/{username}")
    
    public Call<ProfilInfo> getInfo(@Path("username") String username);
}
