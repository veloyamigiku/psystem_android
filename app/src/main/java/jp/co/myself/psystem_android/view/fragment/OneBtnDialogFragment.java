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

    private String title;
    private String message;
    private String btnString;

    private Callback callback;

    public OneBtnDialogFragment(String title, String message, String btnString) {
        this.title = title;
        this.message = message;
        this.btnString = btnString;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void tapBtn();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // ダイアログビルダを作成する。
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setTitle(this.title);

        // ダイアログに表示するメッセージを設定する。
        dialogBuilder.setMessage(this.message);

        dialogBuilder.setPositiveButton(this.btnString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null) {
                    callback.tapBtn();
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