package jp.co.myself.psystem_android.view.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TwoBtnDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_P_BTN_STR = "btn_p_str";
    private static final String ARG_N_BTN_STR = "btn_n_str";

    private String mTitle;
    private String mMessage;
    private String mPBtnStr;
    private String mNBtnStr;

    private Callback mCallback;

    public TwoBtnDialogFragment() {}

    public static TwoBtnDialogFragment newInstance(
            String title,
            String message,
            String pBtnStr,
            String nBtnStr) {
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_P_BTN_STR, pBtnStr);
        args.putString(ARG_N_BTN_STR, nBtnStr);
        fragment.setArguments(args);
        return fragment;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void tapPBtn();
        void tapNBtn();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mMessage = getArguments().getString(ARG_MESSAGE);
            mPBtnStr = getArguments().getString(ARG_P_BTN_STR);
            mNBtnStr = getArguments().getString(ARG_N_BTN_STR);
        }

        // ダイアログビルダを作成する。
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setTitle(this.mTitle);

        // ダイアログに表示するメッセージを設定する。
        dialogBuilder.setMessage(this.mMessage);

        dialogBuilder.setPositiveButton(
                this.mPBtnStr,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCallback != null) {
                            mCallback.tapPBtn();
                        }
                    }
                });
        dialogBuilder.setNegativeButton(
                this.mNBtnStr,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mCallback != null) {
                            mCallback.tapNBtn();
                        }
                    }
                });

        return dialogBuilder.create();
    }
}
