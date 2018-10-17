package project.hdp.com.dagger.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.hdp.com.dagger.R;
import project.hdp.com.dagger.ui.movies.ViewModel;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder>{

    private List<ViewModel> list;
    private View view;
    public MovieItemAdapter(List<ViewModel> resultList) {
        this.list = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvCountry.setText(list.get(position).getCountry());

        Glide.with(view.getContext())
            .load("https://image.tmdb.org/t/p/w500/" + list.get(position).getImageUrl())
            .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        public TextView tvName;

        @BindView(R.id.tv_country)
        public TextView tvCountry;

        @BindView(R.id.iv_image)
        public ImageView ivImage;

        public ViewHolder(View item) {
            super(item);

            ButterKnife.bind(this , item);
        }
    }
}
