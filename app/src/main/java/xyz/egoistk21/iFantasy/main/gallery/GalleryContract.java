package xyz.egoistk21.iFantasy.main.gallery;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;

interface GalleryContract {
    interface Model {
        void getRawPlayers(int pos, int type, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<RawPlayer>>> observer);
    }

    interface View {
        void setRawPlayers(ArrayList<RawPlayer> rawPlayers);

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(int pos, GalleryContract.View view);

        void detachMV(int pos);

        void getRawPlayers(int pos, int type, LifecycleProvider rxLifecycle);
    }
}
