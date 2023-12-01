package qz.github;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import qz.github.databinding.ActivityMainBinding;
import qz.github.model.ProfilInfo;
import qz.github.model.URLParser;

import qz.github.model.Requestsku;
import qz.github.viewmodel.mAdapter;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements Requestsku.ResultData {
    private ActivityMainBinding binding;
    public ArrayList<ProfilInfo> lprofilinfo;
    mAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lprofilinfo = new ArrayList<ProfilInfo>();
        adapter = new mAdapter(lprofilinfo, this);
        binding.listView.setAdapter(adapter);
        binding.listView.setLayoutManager(new LinearLayoutManager(this));
        binding.inputName.setText("https://github.com/QiubyZ");
        binding.start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        lprofilinfo.clear();
                        String user = binding.inputName.getText().toString();
                        URLParser resultList = new URLParser();
                    for(String usr:resultList.parseURLs(user)){
                        ReqApi(usr);
                        Log.d(arg0.getContext().toString(), usr);
                    }
                    
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void ReqApi(String username) {
        Requestsku request = new Requestsku(this, "https://api.github.com/", username);
        request.setOnResultListener(this);
    }

    @Override
    public void ResponseData(ProfilInfo profilinfo) {
        lprofilinfo.add(profilinfo);
        adapter.notifyDataSetChanged();
    }
}
