package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.jpush.im.android.api.model.UserInfo;
import xyz.egoistk21.iFantasy.R;

/**
 * Created by lmj on 2018/4/11.
 */

public class UserAdapter extends ArrayAdapter<UserInfo> {
    private int resourceId;
    private ImageView userImage;
    private TextView userName;
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
                       List<UserInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    class ViewHolder{
        ImageView userImage;
        TextView name;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        UserInfo user = getItem(position);
        View view;
        ViewHolder viewholder;
        if (convertView == null) {
             view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewholder = new ViewHolder();
            viewholder.userImage = view.findViewById(R.id.user_image);
            viewholder.name = view.findViewById(R.id.user_name);
            view.setTag(viewholder);
        }
        else {
            view = convertView;
            viewholder = (ViewHolder) view.getTag();
        }
//        viewholder.userImage.setImageResource( ););
        viewholder.name.setText(user.getUserName());
        return view;
    }

}
