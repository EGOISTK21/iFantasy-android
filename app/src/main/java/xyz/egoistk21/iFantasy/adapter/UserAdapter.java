package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.jpush.im.android.api.model.UserInfo;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.util.DBUtil;

/**
 * Created by lmj on 2018/4/11.
 */

public class UserAdapter extends ArrayAdapter<UserInfo> {
    private int resourceId;
    private ImageView userImage;
    private TextView userName;
    private int defaultSelection = -1;
    private List<UserInfo> List;
//    @BindView(R.id.user_image)
//    ImageView userImage;
//
//    @BindView(R.id.user_name)
//    TextView userName;

    public String getName()
    {
        if(userName!=null) return userName.toString();
        else return "admin";
    }

    public UserAdapter(Context context, int textViewResourceId,
                       List<UserInfo> List) {
        super(context, textViewResourceId, List);
        resourceId = textViewResourceId;
        this.List = List;
    }
    class ViewHolder{
        ImageView userImage;
        TextView name;
        LinearLayout linerlayout;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        UserInfo user = getItem(position);
        View view;
        ViewHolder viewholder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewholder = new ViewHolder();
            viewholder.linerlayout =  view.findViewById(R.id.linerlayout);
            viewholder.userImage = view.findViewById(R.id.user_image);
            viewholder.name = view.findViewById(R.id.user_name);
            view.setTag(viewholder);
        }
        else {
            view = convertView;
            viewholder = (ViewHolder) view.getTag();
        }
        if (position == defaultSelection) {// 选中时设置单纯颜色
            viewholder.linerlayout.setBackgroundColor((Color.rgb(255,0,0)));
        }
//        viewholder.userImage.setImageResource( ););
        viewholder.name.setText(DBUtil.getUser().getNickname());

        return view;
    }
    public void setSelectPosition(int position) {
        if (!(position < 0 || position > List.size())) {
            defaultSelection = position;
            notifyDataSetChanged();
        }
    }

}
