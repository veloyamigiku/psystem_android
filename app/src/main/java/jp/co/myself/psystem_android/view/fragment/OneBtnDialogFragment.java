package jp.co.myself.psystem_android.view.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class OneBtnDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_BTN_STR = "btn_str";

    private String mTitle;
    private String mMessage;
    private String mBtnStr;

    private Callback mCallback;

    public OneBtnDialogFragment() {}

    public static OneBtnDialogFragment newInstance(
            String title,
            String message,
            String btnStr) {
        OneBtnDialogFragment fragment = new OneBtnDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_BTN_STR, btnStr);
        fragment.setArguments(args);
        return fragment;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void tapBtn();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mMessage = getArguments().getString(ARG_MESSAGE);
            mBtnStr = getArguments().getString(ARG_BTN_STR);
        }

        // ダイアログビルダを作成する。
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setTitle(this.mTitle);

        // ダイアログに表示するメッセージを設定する。
        dialogBuilder.setMessage(this.mMessage);

        dialogBuilder.setPositiveButton(this.mBtnStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mCallback != null) {
                    mCallback.tapBtn();
                }
            }
        });

        return dialogBuilder.create();
    }
}
/* 利用例
OneBtnDialogFragment dialog = new OneBtnDialogFragment(
        "利用者登録結果",
        "＝＝＝利用者登録結果のメッセージ＝＝＝",
        "OK");
dialog.setCallback(new OneBtnDialogFragment.Callback() {
    @Override
    public void tapBtn() {
        Log.d(OneBtnDialogFragment.class.getSimpleName(),
        "tapBtn");
    }
});
dialog.show(getFragmentManager(), OneBtnDialogFragment.class.getSimpleName());
*/