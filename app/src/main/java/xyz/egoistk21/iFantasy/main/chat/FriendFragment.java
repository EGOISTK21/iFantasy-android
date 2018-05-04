package xyz.egoistk21.iFantasy.main.chat;

import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;
import xyz.egoistk21.iFantasy.R;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import xyz.egoistk21.iFantasy.adapter.MsgAdapter;
import xyz.egoistk21.iFantasy.adapter.UserAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.User;

import static cn.jpush.im.android.api.JMessageClient.createSingleTextMessage;

/**
 * Created by lmj on 2018/4/28.
 */

public class FriendFragment extends BaseFragment implements FriendContract.View {

    private Button send;
    private EditText inputText;
    private MsgAdapter adapter;
    private UserAdapter adapter1;
    private EditText FriendIDText;
    private List<Message> msgList = new ArrayList<>();
    private RecyclerView msgView;
    private List<UserInfo> userInfoList = new ArrayList<>();
    private ListView userView;
    private Button btn_addFriend;
    private FriendContract.Presenter mPresenter;

    //收到好友邀请
    public void onEvent(ContactNotifyEvent event) {
        String reason = event.getReason();
        String fromUsername = event.getFromUsername();
        String appkey = event.getfromUserAppKey();

        switch (event.getType()) {
            case invite_received://收到好友邀请
                ContactManager.acceptInvitation(fromUsername, appkey, new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage) {
                        if (0 == responseCode) {
                            Log.d(TAG, "responseCodeOK " +responseMessage);
                        } else {
                            Log.d(TAG, "responseCodeERROR " +responseMessage);
                        }
                    }
                });
                break;
            case invite_accepted://对方接收了你的好友邀请
                //...
                break;
            case invite_declined://对方拒绝了你的好友邀请
                //...
                break;
            case contact_deleted://对方将你从好友中删除
                //...
                break;
            default:
                break;
        }
    }

    //收到信息
    public void onEventMainThread(MessageEvent event){
        final Message message = event.getMessage();
        switch (message.getContentType()){
            case text:
                TextContent textContent = (TextContent) message.getContent();
                msgList.add(message);
                adapter.notifyItemInserted(msgList.size() - 1);
                msgView.scrollToPosition(msgList.size() - 1);
                Log.d(TAG, "onEvent: "+textContent.getText());
        }
    }

    public static FriendFragment newInstance() {
        return new FriendFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.friend_channel;
    }

    @Override
    protected void initView() {
        send = rootView.findViewById(R.id.send_friend);
        inputText = rootView.findViewById(R.id.textInputEditText_friend);
        //消息适配
        adapter = new MsgAdapter(msgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        msgView = rootView.findViewById(R.id.msg_recycler_view_friend);
        msgView.setLayoutManager(linearLayoutManager);
        msgView.setAdapter(adapter);
        //好友适配
        userView = rootView.findViewById(R.id.List1);
        adapter1 = new UserAdapter(getParentFragment().getContext(),R.layout.user,userInfoList);
        userView.setAdapter(adapter1);

        btn_addFriend = rootView.findViewById(R.id.btn_addFriend);
        FriendIDText = rootView.findViewById(R.id.FriendIDText);
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    protected void initEvent() {

        Conversation conv = Conversation.createSingleConversation("iFantasy_android_28");
        Log.d(TAG, "initEvent: " + "id: " + conv.getId() + " title: " + conv.getTitle() + " extra: " + conv.getExtra());
        final Message msg = createSingleTextMessage("iFantasy_android_28", "Hello");
        JMessageClient.sendMessage(msg);
        //增加好友
        btn_addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+FriendIDText.getText());
                 mPresenter.getUsers(FriendIDText.getText().toString(), FriendFragment.this);
            }
        });

        //单聊对话
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Message msg = JMessageClient.createSingleTextMessage("iFantasy_android_28", content);
                    JMessageClient.sendMessage(msg);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
        //好友列表
        ContactManager.getFriendList(new GetUserInfoListCallback() {

            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                for(UserInfo userInfo: list){
                    userInfoList.add(userInfo);
                }
                if (i == 0) Log.d(TAG, "gotFriendListOK: "+ s + list.toString());
                else  Log.d(TAG, "gotFriendListERROR: "+ s +list.toString());
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter = new FriendPresenter(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}