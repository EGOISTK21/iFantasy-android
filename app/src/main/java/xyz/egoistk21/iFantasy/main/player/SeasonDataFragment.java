package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.PlayerSeasonDataAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.PlayerSeasonData;
import xyz.egoistk21.iFantasy.util.ApiUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

public class SeasonDataFragment extends BaseFragment {

    @BindView(R.id.rv_player_season_data)
    RecyclerView rvPlayerSeasonData;

    private int mPlayerId;
    private int mBagPlayerId;
    private PlayerSeasonDataAdapter mSeasonDataAdapter;

    public static SeasonDataFragment newInstance() {
        return new SeasonDataFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_player_season_data;
    }

    @Override
    protected void initView() {
        if (mSeasonDataAdapter == null) {
            mSeasonDataAdapter = new PlayerSeasonDataAdapter();
            rvPlayerSeasonData.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPlayerId = bundle.getInt("player_id");
            mBagPlayerId = bundle.getInt("bag_player_id");
        }
    }

    @Override
    protected void lazyFetchData() {
        ApiUtil.getPlayerSeasonDataApi().query(mPlayerId, mBagPlayerId, 1)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(this.<HttpResult<List<PlayerSeasonData>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<HttpResult<List<PlayerSeasonData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(HttpResult<List<PlayerSeasonData>> listHttpResult) {
                        Log.d(TAG, "onNext: " + listHttpResult);
                        if (0 == listHttpResult.getState()) {
                            mSeasonDataAdapter.setPlayerSeasonDatas(listHttpResult.getResult());
                            rvPlayerSeasonData.setAdapter(mSeasonDataAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    @Override
    protected void onDetachP() {

    }
}
