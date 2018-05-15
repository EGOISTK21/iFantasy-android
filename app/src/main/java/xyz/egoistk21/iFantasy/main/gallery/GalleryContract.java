package xyz.egoistk21.iFantasy.main.gallery;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;

public interface GalleryContract {
    interface Model {
        void getRecruitSimplePlayers(int userId, int pos, int order, LifecycleProvider rxLifecycle, Observer<HttpResult<List<SimplePlayer>>> observer);

        void getTeamSimplePlayers(int userId, int pos, int order, LifecycleProvider rxLifecycle, Observer<HttpResult<List<SimplePlayer>>> observer);

    }

    interface View {
        void setSimplePlayers(List<SimplePlayer> simplePlayers);


        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(int pos, GalleryContract.View view);

        void detachMV(int pos);

        void getSimplePlayers(int userId, int pos, int order, LifecycleProvider rxLifecycle);
    }
}
