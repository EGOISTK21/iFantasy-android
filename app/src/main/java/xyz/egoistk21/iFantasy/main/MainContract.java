package xyz.egoistk21.iFantasy.main;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

interface MainContract {
    interface Model {
        boolean isLogined();

        boolean isOnline();
    }

    interface View {
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        boolean isLogined();
    }
}
