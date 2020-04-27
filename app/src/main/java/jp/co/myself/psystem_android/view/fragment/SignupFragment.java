package jp.co.myself.psystem_android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jp.co.myself.psystem_android.R;
import jp.co.myself.psystem_android.utils.ViewUtils;
import jp.co.myself.psystem_android.utils.retrofit.Const;
import jp.co.myself.psystem_android.utils.retrofit.api.PSystemWebService;
import jp.co.myself.psystem_android.utils.retrofit.api.RetrofitFactory;
import jp.co.myself.psystem_android.view.fragment.dialog.OneBtnDialogFragment;
import retrofit2.Retrofit;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * サインアップ画面の登録情報入力のフラグメント。
 */
public class SignupFragment extends Fragment {

    private static final int CONSTRAIN_LAYOUT_RES_ID = 1;
    private static final int SIGNUP_TEXTVIEW_RES_ID = 2;
    private static final int USER_TEXTVIEW_RES_ID = 3;
    private static final int USER_EDITVIEW_RES_ID = 4;
    private static final int PASSWORD_TEXTVIEW_RES_ID = 5;
    private static final int PASSWORD_EDITVIEW_RES_ID = 6;
    private static final int USERNAME_TEXTVIEW_RES_ID = 7;
    private static final int USERNAME_EDITVIEW_RES_ID = 8;
    private static final int NEXT_BUTTON_RES_ID = 9;
    private static final int CANCEL_BUTTON_RES_ID = 10;

    private static final String ARG_USER = "user";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_USERNAME = "username";

    private String mUser;
    private String mPassword;
    private String mUserName;

    private OnFragmentInteractionListener mListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(
            String user,
            String password,
            String userName) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, user);
        args.putString(ARG_PASSWORD, password);
        args.putString(ARG_USERNAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = getArguments().getString(ARG_USER, "");
            mPassword = getArguments().getString(ARG_PASSWORD, "");
            mUserName = getArguments().getString(ARG_USERNAME, "");
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return layoutUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.getParentFragment() instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) this.getParentFragment();
        } else {
            throw new RuntimeException(
                    this.getParentFragment().toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // サインアップ画面（登録情報入力）で、次へボタンをタップした時のイベント処理。
        void onTapInputNext(String token, String user, String password, String userName);
        // サインアップ画面（登録情報入力）で、キャンセルボタンをタップした時のイベント処理。
        void onTapInputCancel();
    }

    private View layoutUI() {

        ConstraintLayout cl = new ConstraintLayout(getActivity());
        cl.setId(CONSTRAIN_LAYOUT_RES_ID);

        // タイトルラベルを配置する。
        TextView signupTextView = new TextView(getActivity());
        signupTextView.setText(R.string.signup);
        signupTextView.setId(SIGNUP_TEXTVIEW_RES_ID);
        signupTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(signupTextView);
        ConstraintSet signupTvCs = new ConstraintSet();
        signupTvCs.constrainWidth(
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        signupTvCs.constrainHeight(
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        signupTvCs.connect(
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupTvCs.connect(
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupTvCs.connect(
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupTvCs.applyTo(cl);

        // ユーザ名ラベルを配置する。
        TextView userTextView = new TextView(getActivity());
        userTextView.setId(USER_TEXTVIEW_RES_ID);
        userTextView.setText(R.string.signup_user);
        userTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userTextView);
        ConstraintSet userTvCs = new ConstraintSet();
        userTvCs.constrainWidth(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userTvCs.constrainHeight(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userTvCs.connect(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userTvCs.connect(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userTvCs.applyTo(cl);

        // ユーザ名の入力項目を配置する。
        final EditText userEditText = new EditText(getActivity());
        userEditText.setId(USER_EDITVIEW_RES_ID);
        userEditText.setText(mUser);
        userEditText.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userEditText);
        ConstraintSet userEtCs = new ConstraintSet();
        userEtCs.constrainHeight(
                USER_EDITVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userEtCs.connect(
                USER_EDITVIEW_RES_ID,
                ConstraintSet.TOP,
                SIGNUP_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(10, getActivity()));
        userEtCs.connect(
                USER_EDITVIEW_RES_ID,
                ConstraintSet.LEFT,
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userEtCs.connect(
                USER_EDITVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userEtCs.applyTo(cl);

        // パスワードラベルを配置する。
        TextView passwordTextView = new TextView(getActivity());
        passwordTextView.setId(PASSWORD_TEXTVIEW_RES_ID);
        passwordTextView.setText(R.string.signup_password);
        passwordTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(passwordTextView);
        ConstraintSet passwordTvCs = new ConstraintSet();
        passwordTvCs.constrainWidth(
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        passwordTvCs.constrainHeight(
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        passwordTvCs.connect(
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordTvCs.connect(
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordTvCs.applyTo(cl);

        // パスワードの入力項目を配置する。
        final EditText passwordEditText = new EditText(getActivity());
        passwordEditText.setId(PASSWORD_EDITVIEW_RES_ID);
        passwordEditText.setText(mPassword);
        passwordEditText.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(passwordEditText);
        ConstraintSet passwordEtCs = new ConstraintSet();
        passwordEtCs.constrainHeight(
                PASSWORD_EDITVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        passwordEtCs.connect(
                PASSWORD_EDITVIEW_RES_ID,
                ConstraintSet.TOP,
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(10, getActivity()));
        passwordEtCs.connect(
                PASSWORD_EDITVIEW_RES_ID,
                ConstraintSet.LEFT,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordEtCs.connect(
                PASSWORD_EDITVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordEtCs.applyTo(cl);

        // 利用者名ラベルを配置する。
        TextView userNameTextView = new TextView(getActivity());
        userNameTextView.setId(USERNAME_TEXTVIEW_RES_ID);
        userNameTextView.setText(R.string.signup_username);
        userNameTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameTextView);
        ConstraintSet userNameTvCs = new ConstraintSet();
        userNameTvCs.constrainWidth(
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.constrainHeight(
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.connect(
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.connect(
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.applyTo(cl);

        // 利用者名の入力項目を配置する。
        final EditText userNameEditText = new EditText(getActivity());
        userNameEditText.setId(USERNAME_EDITVIEW_RES_ID);
        userNameEditText.setText(mUserName);
        userNameEditText.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameEditText);
        ConstraintSet userNameEtCs = new ConstraintSet();
        userNameEtCs.constrainHeight(
                USERNAME_EDITVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameEtCs.connect(
                USERNAME_EDITVIEW_RES_ID,
                ConstraintSet.TOP,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(10, getActivity()));
        userNameEtCs.connect(
                USERNAME_EDITVIEW_RES_ID,
                ConstraintSet.LEFT,
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameEtCs.connect(
                USERNAME_EDITVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameEtCs.applyTo(cl);

        // 次へボタンを配置する。
        Button nextButton = new Button(getActivity());
        nextButton.setId(NEXT_BUTTON_RES_ID);
        nextButton.setText(R.string.signup_next);
        nextButton.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(nextButton);
        ConstraintSet nextBtnCs = new ConstraintSet();
        nextBtnCs.constrainWidth(
                NEXT_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(150, getActivity()));
        nextBtnCs.constrainHeight(
                NEXT_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(50, getActivity()));
        nextBtnCs.connect(
                NEXT_BUTTON_RES_ID,
                ConstraintSet.TOP,
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        nextBtnCs.connect(
                NEXT_BUTTON_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        nextBtnCs.applyTo(cl);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = RetrofitFactory.getInstance(
                        Const.BASE_URL,
                        false);
                PSystemWebService service = retrofit.create(PSystemWebService.class);
                service.issueJwtForSignup("param")
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (res) -> {
                                    if (mListener == null) {
                                        return;
                                    }
                                    if (res.result) {
                                        mListener.onTapInputNext(
                                                res.token,
                                                userEditText.getText().toString(),
                                                passwordEditText.getText().toString(),
                                                userNameEditText.getText().toString());
                                    } else {
                                        // トークン発行に失敗(status=200)のダイアログ。画面遷移なし。
                                        OneBtnDialogFragment dialog = OneBtnDialogFragment.newInstance(
                                                "利用者登録",
                                                "利用者登録用のトークン発行に失敗しました。",
                                                "閉じる");
                                        dialog.setCallback(new OneBtnDialogFragment.Callback() {
                                            @Override
                                            public void tapBtn() {
                                                // 処理なし。
                                            }
                                        });
                                        dialog.show(
                                                getFragmentManager(),
                                                OneBtnDialogFragment.class.getSimpleName());
                                    }
                                },
                                (err) -> {
                                    if (mListener == null) {
                                        return;
                                    }
                                    // トークン発行に失敗のダイアログ。(status=200以外)。画面遷移なし。
                                    OneBtnDialogFragment dialog = OneBtnDialogFragment.newInstance(
                                            "利用者登録",
                                            "利用者登録処理中の通信が失敗しました。",
                                            "閉じる");
                                    dialog.setCallback(new OneBtnDialogFragment.Callback() {
                                        @Override
                                        public void tapBtn() {
                                            // 処理なし。
                                        }
                                    });
                                    dialog.show(
                                            getFragmentManager(),
                                            OneBtnDialogFragment.class.getSimpleName());
                                });

            }
        });

        // キャンセルボタンを配置する。
        Button cancelButton = new Button(getActivity());
        cancelButton.setId(CANCEL_BUTTON_RES_ID);
        cancelButton.setText(R.string.signup_cancel);
        cancelButton.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(cancelButton);
        ConstraintSet cancelBtnCs = new ConstraintSet();
        cancelBtnCs.constrainWidth(
                CANCEL_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(150, getActivity()));
        cancelBtnCs.constrainHeight(
                CANCEL_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(50, getActivity()));
        cancelBtnCs.connect(
                CANCEL_BUTTON_RES_ID,
                ConstraintSet.TOP,
                USERNAME_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        cancelBtnCs.connect(
                CANCEL_BUTTON_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        cancelBtnCs.applyTo(cl);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTapInputCancel();
            }
        });

        return cl;
    }

}
