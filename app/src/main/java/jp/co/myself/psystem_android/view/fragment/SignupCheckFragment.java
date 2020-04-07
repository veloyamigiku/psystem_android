package jp.co.myself.psystem_android.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import jp.co.myself.psystem_android.R;
import jp.co.myself.psystem_android.utils.ViewUtils;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * サインアップ画面_利用者登録情報の表示フラグメント。
 */
public class SignupCheckFragment extends Fragment {

    private static final int CONSTRAINT_LAYOUT_RES_ID = 1;
    private static final int SIGNUP_CHECK_TEXTVIEW_RES_ID = 2;
    private static final int USER_TEXTVIEW_RES_ID = 3;


    private static final String ARG_USER = "user";
    private static final String ARG_PASSWORD = "password";
    private static final String ARG_USERNAME = "username";

    private static final int USER_VALUE_TEXTVIEW_RES_ID = 4;
    private static final int PASSWORD_TEXTVIEW_RES_ID = 5;
    private static final int PASSWORD_VALUE_TEXTVIEW_RES_ID = 6;
    private static final int USER_NAME_TEXTVIEW_RES_ID = 7;
    private static final int USER_NAME_VALUE_TEXTVIEW_RES_ID = 8;
    private static final int REGISTER_BUTTON_RES_ID = 9;
    private static final int BACK_BUTTON_RES_ID = 10;

    private String mUser;
    private String mPassword;
    private String mUserName;

    private OnFragmentInterractionListener mListener = null;

    public SignupCheckFragment() {
        // Required empty public constructor
    }

    public static SignupCheckFragment newInstance(String user, String password, String userName) {
        SignupCheckFragment fragment = new SignupCheckFragment();
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
            mUser = getArguments().getString(ARG_USER);
            mPassword = getArguments().getString(ARG_PASSWORD);
            mUserName = getArguments().getString(ARG_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return layoutUI();
    }

    private View layoutUI() {
        ConstraintLayout cl = new ConstraintLayout(getActivity());
        cl.setId(CONSTRAINT_LAYOUT_RES_ID);

        // タイトルラベルを配置する。
        TextView signupCheckTextView = new TextView(getActivity());
        signupCheckTextView.setId(SIGNUP_CHECK_TEXTVIEW_RES_ID);
        signupCheckTextView.setText(R.string.signup_check_title);
        signupCheckTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(signupCheckTextView);
        ConstraintSet signupCheckTvCs = new ConstraintSet();
        signupCheckTvCs.constrainWidth(
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        signupCheckTvCs.constrainHeight(
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        signupCheckTvCs.connect(
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupCheckTvCs.connect(
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupCheckTvCs.connect(
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupCheckTvCs.applyTo(cl);

        // ユーザ名ラベルを配置する。
        final TextView userTextView = new TextView(getActivity());
        userTextView.setId(USER_TEXTVIEW_RES_ID);
        userTextView.setText(R.string.signup_check_user);
        userTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userTextView);
        ConstraintSet userTvCs = new ConstraintSet();
        userTvCs.constrainHeight(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userTvCs.constrainWidth(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userTvCs.connect(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userTvCs.connect(
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userTvCs.applyTo(cl);

        // ユーザ名ラベル（値）を配置する。
        final TextView userValueTextView = new TextView(getActivity());
        userValueTextView.setId(USER_VALUE_TEXTVIEW_RES_ID);
        userValueTextView.setText(mUser);
        userValueTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userValueTextView);
        ConstraintSet userValueTvCs = new ConstraintSet();
        userValueTvCs.constrainWidth(
                USER_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userValueTvCs.constrainHeight(
                USER_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userValueTvCs.connect(
                USER_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                SIGNUP_CHECK_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userValueTvCs.connect(
                USER_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userValueTvCs.applyTo(cl);

        // パスワードラベルを配置する。
        final TextView passwordTextView = new TextView(getActivity());
        passwordTextView.setId(PASSWORD_TEXTVIEW_RES_ID);
        passwordTextView.setText(R.string.signup_check_password);
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

        // パスワードラベル（値）を配置する。
        final TextView passwordValueTextView = new TextView(getActivity());
        passwordValueTextView.setId(PASSWORD_VALUE_TEXTVIEW_RES_ID);
        passwordValueTextView.setText(R.string.signup_check_password_dummy);
        passwordValueTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(passwordValueTextView);
        ConstraintSet passwordValueTvCs = new ConstraintSet();
        passwordValueTvCs.constrainWidth(
                PASSWORD_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        passwordValueTvCs.constrainHeight(
                PASSWORD_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        passwordValueTvCs.connect(
                PASSWORD_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                USER_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordValueTvCs.connect(
                PASSWORD_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordValueTvCs.applyTo(cl);

        // 利用者名ラベルを配置する。
        final TextView userNameTextView = new TextView(getActivity());
        userNameTextView.setId(USER_NAME_TEXTVIEW_RES_ID);
        userNameTextView.setText(R.string.signup_check_username);
        userNameTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameTextView);
        ConstraintSet userNameTvCs = new ConstraintSet();
        userNameTvCs.constrainWidth(
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.constrainHeight(
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.connect(
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.connect(
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.applyTo(cl);

        // 利用者名ラベル（値）を配置する。
        final TextView userNameValueTextView = new TextView(getActivity());
        userNameValueTextView.setId(USER_NAME_VALUE_TEXTVIEW_RES_ID);
        userNameValueTextView.setText(mUserName);
        userNameValueTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameValueTextView);
        ConstraintSet userNameValueTvCs = new ConstraintSet();
        userNameValueTvCs.constrainWidth(
                USER_NAME_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameValueTvCs.constrainHeight(
                USER_NAME_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        userNameValueTvCs.connect(
                USER_NAME_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.TOP,
                PASSWORD_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameValueTvCs.connect(
                USER_NAME_VALUE_TEXTVIEW_RES_ID,
                ConstraintSet.LEFT,
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameValueTvCs.applyTo(cl);

        // 登録ボタンを配置する。
        Button registerButton = new Button(getActivity());
        registerButton.setId(REGISTER_BUTTON_RES_ID);
        registerButton.setText(R.string.signup_check_register);
        registerButton.setTextSize(COMPLEX_UNIT_SP, 18);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTapRegister();
            }
        });
        cl.addView(registerButton);
        ConstraintSet registerBtnCs = new ConstraintSet();
        registerBtnCs.constrainWidth(
                REGISTER_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(150, getActivity()));
        registerBtnCs.constrainHeight(
                REGISTER_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(50, getActivity()));
        registerBtnCs.connect(
                REGISTER_BUTTON_RES_ID,
                ConstraintSet.TOP,
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        registerBtnCs.connect(
                REGISTER_BUTTON_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        registerBtnCs.applyTo(cl);

        // 戻るボタンを配置する。
        Button backButton = new Button(getActivity());
        backButton.setId(BACK_BUTTON_RES_ID);
        backButton.setText(R.string.singup_check_back);
        backButton.setTextSize(COMPLEX_UNIT_SP, 18);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTapBack(
                        mUser,
                        mPassword,
                        mUserName);
            }
        });
        cl.addView(backButton);
        ConstraintSet backBtnCs = new ConstraintSet();
        backBtnCs.constrainWidth(
                BACK_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(150, getActivity()));
        backBtnCs.constrainHeight(
                BACK_BUTTON_RES_ID,
                ViewUtils.fromDipToPixel(50, getActivity()));
        backBtnCs.connect(
                BACK_BUTTON_RES_ID,
                ConstraintSet.TOP,
                USER_NAME_TEXTVIEW_RES_ID,
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        backBtnCs.connect(
                BACK_BUTTON_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        backBtnCs.applyTo(cl);

        return cl;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.getParentFragment() instanceof OnFragmentInterractionListener) {
            mListener = (OnFragmentInterractionListener) this.getParentFragment();
        } else {
            throw new RuntimeException(
                    this.getParentFragment().toString() + " must implement OnFragmentInterractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInterractionListener {
        // サインアップ画面（登録情報表示）で、登録ボタンをタップした時のイベント処理。
        void onTapRegister();
        // サインアップ画面（登録情報表示）で、戻るボタンをタップした時のイベント処理。
        void onTapBack(
                String user,
                String password,
                String userName);
    }
}
