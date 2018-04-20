package xyz.egoistk21.iFantasy.main.team;

interface TeamContract {
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
