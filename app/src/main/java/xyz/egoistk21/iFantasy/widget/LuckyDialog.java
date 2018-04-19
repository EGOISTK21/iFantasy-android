package xyz.egoistk21.iFantasy.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.egoistk21.iFantasy.R;

public class LuckyDialog extends AlertDialog {

    private LuckyDialog(Context context) {
        super(context);
    }

    private LuckyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private LuckyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context mContext;
        private int mThemeResId;
        private LuckyDialog mLuckyDialog;
        private int mTitle, mPositiveButtonText;
        private OnClickListener mPositiveOnClickListener;

        public Builder(Context context, int themeResId) {
            mContext = context;
            mThemeResId = themeResId;
        }

        public Builder setTitle(int title) {
            mTitle = title;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener positiveOnClickListener) {
            mPositiveButtonText = positiveButtonText;
            mPositiveOnClickListener = positiveOnClickListener;
            return this;
        }

        public LuckyDialog create() {
            View rootView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(mThemeResId, null);
            mLuckyDialog = new LuckyDialog(mContext, R.style.dialogTheme);
            mLuckyDialog.setContentView(rootView);
            ((TextView) rootView.findViewById(R.id.tv_dialog_title)).setText(mTitle);
            Button positiveButton = rootView.findViewById(R.id.tv_dialog_positive);
            positiveButton.setText(mPositiveButtonText);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPositiveOnClickListener.onClick(mLuckyDialog, DialogInterface.BUTTON_POSITIVE);
                }
            });
            return mLuckyDialog;
        }
    }
}
