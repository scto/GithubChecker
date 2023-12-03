package qz.github;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import qz.github.databinding.ActivityMainBinding;
import qz.github.model.ProfilInfo;


import qz.github.model.Requestsku;
import qz.github.myUtils.UserParse;
import qz.github.viewmodel.mAdapter;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public ArrayList<ProfilInfo> lprofilinfo;
    public mAdapter adapter;
    public String TAGS = MainActivity.this.toString();

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
                        Context cyx = arg0.getRootView().getContext();
                        for (String url : binding.inputName.getText().toString().split("\n")) {
                            executors(UserParse.parseUrl(url));
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    void executors(String username) {
        Handler hand = new Handler(Looper.getMainLooper());
        Thread t =
                new Thread(
                        new Runnable() {
                            Requestsku req = new Requestsku(username);

                            @Override
                            public void run() {
                                synchronized (this) {
                                    req.init();
                                    req.getResult();
                                }

                                // TODO: Implement this method
                                hand.post(
                                        new Runnable() {

                                            @Override
                                            public void run() {
                                                lprofilinfo.add(req.getResult());
                                                adapter.notifyDataSetChanged();
                                            }
                                        });
                            }
                        });
        t.start();
    }
}
