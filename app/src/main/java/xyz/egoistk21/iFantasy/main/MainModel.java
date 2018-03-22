package xyz.egoistk21.iFantasy.main;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

class MainModel implements MainContract.Model {
    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public boolean isOnline() {
        return false;
    }
}
