package xyz.egoistk21.iFantasy.main.chat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.jpush.im.android.api.ChatRoomManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.event.ChatRoomMessageEvent;
import cn.jpush.im.android.api.model.ChatRoomInfo;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.MsgAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by lmj on 2018/4/28.
 */

public class WorldFragment extends BaseFragment {
    private Button send;
    private EditText inputText;
    private MsgAdapter adapter;
    private List<Message> msgList;
    private RecyclerView msgView;
    private TextView textView;
    private Conversation conv;
    private long WorldChannelNum=12535499;

    public static WorldFragment newInstance() {
        return new WorldFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.world_channel;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        msgView = rootView.findViewById(R.id.msg_recycler_view_world);
        msgView.setLayoutManager(linearLayoutManager);
        adapter = new MsgAdapter(msgList = new ArrayList<>());
        msgView.setAdapter(adapter);
        send =  rootView.findViewById(R.id.send_world);
        inputText = rootView.findViewById(R.id.textInputEditText_world);
        textView = rootView.findViewById(R.id.Channel);
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initEvent() {
        JMessageClient.registerEventReceiver(this);
        ChatRoomManager.enterChatRoom(WorldChannelNum, new RequestCallback<Conversation>() {
            @Override
            public void gotResult(int i, String s, Conversation conversation) {
                if(i==0)
                {
                    Log.d(TAG, "gotResultChatroom: "+s);
                    Toast.makeText(getContext(),"欢迎进入世界频道",LENGTH_SHORT);
                }
                else {
                    Toast.makeText(getContext(),"进入世界频道失败~~"+s,LENGTH_SHORT);
                    Log.d(TAG, "gotResultChatroomERROR: "+s);
                }
            }
        });
        conv = JMessageClient.getChatRoomConversation(WorldChannelNum);
        if (null == conv) {
            conv = Conversation.createChatRoomConversation(WorldChannelNum);
        }
        Log.d(TAG, "initEvent: " + String.valueOf(conv == null));
        ChatRoomManager.getChatRoomInfos(Collections.singleton((long)12535499), new RequestCallback<List<ChatRoomInfo>>() {
            @Override
            public void gotResult(int responseCode, String responseMessage, List<ChatRoomInfo> chatRoomInfos) {
                String result = null != chatRoomInfos ? chatRoomInfos.toString() : null;
                ChatRoomInfo chatRoomInfo = chatRoomInfos.get(0);
                textView.setText("世界频道在线人数:"+chatRoomInfo.getTotalMemberCount());

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Message msg = conv.createSendTextMessage(content);
                    JMessageClient.sendMessage(msg);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
                Log.i("lige",content+conv.getId());
            }
        });
    }

    public void onEventMainThread(ChatRoomMessageEvent event) {
        Log.d("tag", "ChatRoomMessageEvent received .");
        List<Message> msgs = event.getMessages();
        for (Message msg : msgs) {
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);
            msgView.scrollToPosition(msgList.size() - 1);
            Log.d(TAG, "onEvent: "+msg.getContent());
        }
    }


    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {
        JMessageClient.deleteChatRoomConversation(WorldChannelNum);
        ChatRoomManager.leaveChatRoom(WorldChannelNum, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                Log.d(TAG, "gotleaveChatRoomResult: "+ responseCode+responseMessage);
            }
        });

    }
}