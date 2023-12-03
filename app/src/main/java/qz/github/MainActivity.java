package qz.github;

import android.content.Context;
import android.graphics.Insets;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qz.github.databinding.ActivityMainBinding;
import qz.github.model.ProfilInfo;

import android.widget.Toast;

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
        binding.inputName.setText("https://github.com/QiubyZ\niqbal");

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
