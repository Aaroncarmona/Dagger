package project.hdp.com.dagger.ui.movies;


import io.reactivex.Observable;

public class MoviesMVP {

    public interface View {
        void updateData(ViewModel viewModel);

        void showSnackbar(String s);


    }

    public interface Presenter {
        void loadData();

        void rxJavaUnsuscribe();

        void setView(MoviesMVP.View view);

    }

    public interface Model {
        Observable result();
    }
}
