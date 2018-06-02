package xyz.egoistk21.iFantasy.main.bag;

public interface BagGalleryContract {
    interface Model {

    }

    interface View {
        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(int pos, BagGalleryContract.View view);

        void detachMV(int pos);
    }
}
