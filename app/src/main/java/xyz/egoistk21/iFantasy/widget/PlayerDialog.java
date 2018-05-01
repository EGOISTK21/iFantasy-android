package xyz.egoistk21.iFantasy.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

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
        private Point mPoint;
        private ViewGroup.LayoutParams params;
        private ImageView mImageView;
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
            mPlayerDialog.getWindow().getWindowManager().getDefaultDisplay().getSize(mPoint = new Point());
            mImageView = rootView.findViewById(R.id.image_view);
            params = mImageView.getLayoutParams();
            Glide.with(mContext)
                    .asBitmap()
                    .load(mImageUrl)
                    .into(new ViewTarget<ImageView, Bitmap>(mImageView) {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            int bWidth = resource.getWidth();
                            int bHeight = resource.getHeight();
                            params.height = mPoint.y;
                            params.width = mPoint.y * bWidth / bHeight;
                            mImageView.setLayoutParams(params);
                            mImageView.setImageBitmap(resource);
                        }
                    });
            return mPlayerDialog;
        }
    }
}
