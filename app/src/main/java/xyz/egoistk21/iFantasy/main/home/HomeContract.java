package xyz.egoistk21.iFantasy.main.home;

interface HomeContract {
    interface Model {
    }

    interface View {
        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();
    }
}
