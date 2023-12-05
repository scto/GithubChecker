package qz.github.viewmodel;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import qz.github.*;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import qz.github.model.ProfilInfo;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    List<ProfilInfo> netebang;

    public mAdapter(List<ProfilInfo> netebang) {
        this.netebang = netebang;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView user, followers, Repos;

        public ViewHolder(View v) {
            super(v);
			
            this.user = v.findViewById(R.id.user);
            this.img = v.findViewById(R.id.avatar);
            this.followers = v.findViewById(R.id.followersCount);
            this.Repos = v.findViewById(R.id.publicreposCount);
        }
    }

    @Override
    public void onBindViewHolder(mAdapter.ViewHolder viewholder, int arg1) {
        ProfilInfo ehe = (ProfilInfo) netebang.get(viewholder.getAdapterPosition());
		Context ctx = viewholder.itemView.getContext();
		
        Glide.with(ctx).load(ehe.getAvatar()).into(viewholder.img);
        viewholder.user.setText(ehe.getUsername());
        viewholder.Repos.setText(ehe.getPublicreposCount());
        viewholder.followers.setText(ehe.getFollowersCount());
		
		viewholder.itemView.setOnLongClickListener((v) -> {
				ClipboardManager cm = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
				cm.setText(ehe.getUsername());
				Toast.makeText(ctx, ehe.getUsername()  , Toast.LENGTH_LONG).show();
				return true;
		});
		
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
