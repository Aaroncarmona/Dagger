package project.hdp.com.dagger.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.hdp.com.dagger.R;
import project.hdp.com.dagger.root.App;
import project.hdp.com.dagger.ui.adapter.MovieItemAdapter;
import project.hdp.com.dagger.ui.movies.MoviesMVP;
import project.hdp.com.dagger.ui.movies.ViewModel;

public class MainActivity extends AppCompatActivity implements MoviesMVP.View {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.ll_root)
    ViewGroup rootView;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    @Inject
    MoviesMVP.Presenter presenter;

    private MovieItemAdapter movieAdapter;
    private List<ViewModel> resultList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        movieAdapter = new MovieItemAdapter(resultList);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.HORIZONTAL));

        rvMovies.setItemAnimator(new DefaultItemAnimator());
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager( new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        resultList.clear();
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);
        movieAdapter.notifyItemInserted(resultList.size()-1);
        Log.d(TAG , "informacion nueva : " + viewModel.getName() );
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(rootView , message , Snackbar.LENGTH_SHORT).show();
    }
}