package xyz.egoistk21.iFantasy.game;

interface GameContract {
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
