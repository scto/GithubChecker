package qz.github.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import qz.github.*;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import qz.github.model.ProfilInfo;
import qz.github.viewmodel.mAdapter;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    List<ProfilInfo> netebang;
    Context ctx;

    public mAdapter(List<ProfilInfo> netebang, Context ctx) {
        this.netebang = netebang;
        this.ctx = ctx;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView user;

        public ViewHolder(View v) {
            super(v);
            this.user = v.findViewById(R.id.user);
            this.img = v.findViewById(R.id.avatar);
        }
    }

    @Override
    public void onBindViewHolder(mAdapter.ViewHolder viewholder, int arg1) {
        ProfilInfo ehe = (ProfilInfo) netebang.get(arg1);
        Glide.with(ctx).load(ehe.getAvatar()).into(viewholder.img);
        viewholder.user.setText(ehe.getUsername());
    }

    @Override
    public qz.github.viewmodel.mAdapter.ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        View v = LayoutInflater.from(arg0.getContext()).inflate(R.layout.costum_list, arg0, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return netebang.size();
    }
}
