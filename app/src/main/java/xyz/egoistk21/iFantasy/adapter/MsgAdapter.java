package xyz.egoistk21.iFantasy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import xyz.egoistk21.iFantasy.R;

/**
 * Created by lmj on 2018/4/12.
 */

public class MsgAdapter extends RecyclerView.Adapter <MsgAdapter.ViewHolder>{

    private List<Message> mMsgList;

    static class  ViewHolder extends RecyclerView.ViewHolder{


    LinearLayout leftLayout;

    LinearLayout rightLayout;

    TextView leftMsg;

    TextView rightMsg;

    public ViewHolder(View view){
        super(view);
        leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
        rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
        leftMsg = (TextView) view.findViewById(R.id.left_msg);
        rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
    }


public MsgAdapter(List<Message> msgList){
    mMsgList = msgList;
}

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
            return new ViewHolder(view);
}
@Override
    public void onBindViewHolder(ViewHolder holder, int posittion){
        Message msg = mMsgList.get(posittion);

        if(msg.getDirect() == MessageDirect.receive){
        holder.leftLayout.setVisibility(View.VISIBLE);
        holder.rightLayout.setVisibility(View.GONE);
        holder.leftMsg.setText(((TextContent)msg.getContent()).getText());
        }
        else if(msg.getDirect() == MessageDirect.send){
        holder.rightLayout.setVisibility(View.VISIBLE);
        holder.leftLayout.setVisibility(View.GONE);
        holder.rightMsg.setText(((TextContent)msg.getContent()).getText());
        }
    }

    @Override
    public int getItemCount(){
    return  mMsgList.size();
    }
}