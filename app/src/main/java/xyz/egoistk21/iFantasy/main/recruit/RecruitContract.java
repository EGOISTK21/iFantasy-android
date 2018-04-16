package xyz.egoistk21.iFantasy.main.recruit;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;

interface RecruitContract {
    interface Model {
        void getRecruitInfo(int user_id, LifecycleProvider rxLifecycle, Observer<HttpResult<RecruitInfo>> observer);

        void getPlayers(int pos, int type, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<RawPlayer>>> observer);
    }

    interface View {
        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getRecruitInfo(int user_id, LifecycleProvider rxLifecycle);

        void getPlayers(int pos, int type, LifecycleProvider rxLifecycle);
    }
}
