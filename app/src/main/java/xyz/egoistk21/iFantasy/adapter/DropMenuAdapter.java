package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.view.FilterCheckedTextView;

import java.util.Arrays;

import static android.view.Gravity.CENTER;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        return 0;
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        return createSingleListView();
    }

    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setGravity(CENTER);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        onFilterDone(item);
                    }
                });
        singleListView.setList(Arrays.asList("位置", "评分", "薪资"), -1);
        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
        singleListView.setLayoutParams(mParam);
        return singleListView;
    }


    private void onFilterDone(String item) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }

}
