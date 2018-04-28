package xyz.egoistk21.iFantasy.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class PlayerDialog extends Dialog {

    private PlayerDialog(Context context) {
        super(context);
    }

    private PlayerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private PlayerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        if (UIUtil.checkDeviceHasNavigationBar(getContext())) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            super.show();
            window.getDecorView().setSystemUiVisibility(UIUtil.IMMERSIVE_STICKY);
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        } else {
            super.show();
        }
    }

    public static class Builder {

        private Context mContext;
        private int mThemeResId;
        private String mImageUrl;
        private PlayerDialog mPlayerDialog;

        public Builder(Context context, int themeResId) {
            mContext = context;
            mThemeResId = themeResId;
        }

        public Builder setImageUrl(String imageUrl) {
            mImageUrl = imageUrl;
            return this;
        }

        public PlayerDialog create() {
            LayoutInflater inflater = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            View rootView = inflater.inflate(mThemeResId, null);
            mPlayerDialog = new PlayerDialog(mContext, R.style.playerDialogTheme);
            mPlayerDialog.setContentView(rootView);
//            ImageView imageView = rootView.findViewById(R.id.image_view);
//            Glide.with(mContext)
//                    .load(mImageUrl)
//                    .into(imageView);
            return mPlayerDialog;
        }
    }
}
