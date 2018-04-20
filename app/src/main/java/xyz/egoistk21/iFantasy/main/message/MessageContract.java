package xyz.egoistk21.iFantasy.main.message;

interface MessageContract {
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
