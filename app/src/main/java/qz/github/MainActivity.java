package qz.github;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import qz.github.databinding.ActivityMainBinding;
import qz.github.model.ProfilInfo;
import qz.github.model.Requestsku;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        
        setContentView(binding.getRoot());

        try {

            Requestsku request = new Requestsku(this, "https://api.github.com/", "QiubyZ");
            request.setOnResultListener(
                    new Requestsku.ResultData() {
                        @Override
                        public void ResponseData(ProfilInfo profilinfo) {
                            String user = profilinfo.getUsername();
                            binding.result.setText(user);
                        }
                    });
        } catch (Exception err) {
            binding.result.setText(err.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
