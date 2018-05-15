package xyz.egoistk21.iFantasy.main.chat;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.StringUtil;


class FriendPresenter implements FriendContract.Presenter {

    private static final String TAG = FriendPresenter.class.getName();

    private FriendContract.Model mModel;
    private FriendContract.View mView;

    FriendPresenter(FriendContract.View mView) {
        attachMV(mView);
    }


    @Override
    public void attachMV(FriendContract.View view) {
        mView = view;
        mModel = new FriendModel();
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getUsers(String nickname, LifecycleProvider rxLifecycle) {
            mModel.getUsers(nickname, rxLifecycle, new Observer<HttpResult<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onSubscribe");

                        }

                        @Override
                        public void onNext(HttpResult<User> listHttpResult) {
                            Log.d(TAG, "onNext: " + listHttpResult.toString());
                            if (200 == listHttpResult.getState()) {
                                ContactManager.sendInvitationRequest(StringUtil.generateJGName(listHttpResult.getResult().getId()), StringUtil.APP_KEY, "hello", new BasicCallback() {
                                    @Override
                                    public void gotResult(int responseCode, String responseMessage) {
                                        if (0 == responseCode) {
                                            Log.d(TAG, "sendInvitationRequestOK " + responseMessage);
                                        } else {
                                            Log.d(TAG, "sendInvitationRequestERROR " + responseMessage);
                                        }
                                    }
                                });
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
                    }
            );
        }
    }
