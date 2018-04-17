package xyz.egoistk21.iFantasy.main.recruit;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;

interface RecruitContract {
    interface Model {
        void getRecruitInfo(int user_id, LifecycleProvider rxLifecycle, Observer<HttpResult<RecruitInfo>> observer);

        void getRawPlayers(int pos, int type, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<RawPlayer>>> observer);

        void luckyRecruit(int userId, LifecycleProvider rxLifecycle, Observer<HttpResult<RecruitResult>> observer);

        void pentaLuckyRecruit(int userId, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<RecruitResult>>> observer);
    }

    interface View {
        void setRecruitInfo(RecruitInfo recruitInfo);

        void setRawPlayers(ArrayList<RawPlayer> rawPlayers);

        void showLuckyRecruitResult(RecruitResult recruitResult);

        void showPentaLuckyRecruitResult(ArrayList<RecruitResult> recruitResults);

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getRecruitInfo(int userId, LifecycleProvider rxLifecycle);

        void getPlayers(int pos, int type, LifecycleProvider rxLifecycle);

        void luckyRecruit(int userId, LifecycleProvider rxLifecycle);

        void pentaLuckyRecruit(int userId, LifecycleProvider rxLifecycle);
    }
}
