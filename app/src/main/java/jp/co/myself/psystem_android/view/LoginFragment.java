package jp.co.myself.psystem_android.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import jp.co.myself.psystem_android.R;
import jp.co.myself.psystem_android.utils.ViewUtils;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * ログイン画面のフラグメント。
 */
public class LoginFragment extends Fragment {

    private static final int CONSTRAINT_LAYOUT_RED_ID = 1;
    private static final int LOGIN_TEXT_VIEW_RES_ID = 2;
    private static final int USER_NAME_TEXT_VIEW_RES_ID = 3;
    private static final int USER_NAME_EDIT_VIEW_RES_ID = 4;
    private static final int PASSWORD_TEXT_VIEW_RES_ID = 5;
    private static final int PASSWORD_EDIT_VIEW_RES_ID = 6;
    private static final int LOGIN_BUTTON_RES_ID = 7;
    private static final int SIGNUP_BUTTON_RES_ID = 8;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return layoutUI();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private View layoutUI() {

        ConstraintLayout cl = new ConstraintLayout(getActivity());
        cl.setId(CONSTRAINT_LAYOUT_RED_ID);

        // ログインラベルを配置する。
        TextView loginTextView = new TextView(getActivity());
        loginTextView.setText(R.string.login);
        loginTextView.setId(LOGIN_TEXT_VIEW_RES_ID);
        loginTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(loginTextView);
        // ログインラベルの制約を作成する。
        ConstraintSet loginTextViewCs = new ConstraintSet();
        loginTextViewCs.constrainWidth(
                LOGIN_TEXT_VIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        loginTextViewCs.constrainHeight(
                LOGIN_TEXT_VIEW_RES_ID,
                ConstraintSet.WRAP_CONTENT);
        loginTextViewCs.connect(
                LOGIN_TEXT_VIEW_RES_ID,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                ViewUtils.fromDipToPixel(18, getActivity()));
        loginTextViewCs.connect(
                LOGIN_TEXT_VIEW_RES_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        loginTextViewCs.connect(
                LOGIN_TEXT_VIEW_RES_ID,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                18);
        loginTextViewCs.applyTo(cl);

        // ユーザ名ラベルを配置する。
        TextView userNameTextView = new TextView(getActivity());
        userNameTextView.setId(USER_NAME_TEXT_VIEW_RES_ID);
        userNameTextView.setText(R.string.user_name);
        userNameTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameTextView);
        ConstraintSet userNameTvCs = new ConstraintSet();
        userNameTvCs.constrainWidth(
                userNameTextView.getId(),
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.constrainHeight(
                userNameTextView.getId(),
                ConstraintSet.WRAP_CONTENT);
        userNameTvCs.connect(
                userNameTextView.getId(),
                ConstraintSet.TOP,
                loginTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.connect(
                userNameTextView.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameTvCs.applyTo(cl);

        // ユーザ名入力項目を配置する。
        EditText userNameEditText = new EditText(getActivity());
        userNameEditText.setId(USER_NAME_EDIT_VIEW_RES_ID);
        userNameEditText.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(userNameEditText);
        ConstraintSet userNameEtCs = new ConstraintSet();
        userNameEtCs.constrainHeight(
                userNameEditText.getId(),
                ConstraintSet.WRAP_CONTENT);
        userNameEtCs.connect(
                userNameEditText.getId(),
                ConstraintSet.TOP,
                loginTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(10, getActivity()));
        userNameEtCs.connect(
                userNameEditText.getId(),
                ConstraintSet.LEFT,
                userNameTextView.getId(),
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameEtCs.connect(
                userNameEditText.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        userNameEtCs.applyTo(cl);

        // パスワードラベルを配置する。
        TextView passwordTextView = new TextView(getActivity());
        passwordTextView.setId(PASSWORD_TEXT_VIEW_RES_ID);
        passwordTextView.setText(R.string.password);
        passwordTextView.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(passwordTextView);
        ConstraintSet passwordTvCs = new ConstraintSet();
        passwordTvCs.constrainWidth(
                passwordTextView.getId(),
                ConstraintSet.WRAP_CONTENT);
        passwordTvCs.constrainHeight(
                passwordTextView.getId(),
                ConstraintSet.WRAP_CONTENT);
        passwordTvCs.connect(
                passwordTextView.getId(),
                ConstraintSet.TOP,
                userNameTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordTvCs.connect(
                passwordTextView.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordTvCs.applyTo(cl);

        // パスワード入力項目を配置する。
        EditText passwordEditText = new EditText(getActivity());
        passwordEditText.setId(PASSWORD_EDIT_VIEW_RES_ID);
        passwordEditText.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(passwordEditText);
        ConstraintSet passwordEtCs = new ConstraintSet();
        passwordEtCs.constrainHeight(
                passwordEditText.getId(),
                ConstraintSet.WRAP_CONTENT);
        passwordEtCs.connect(
                passwordEditText.getId(),
                ConstraintSet.TOP,
                userNameTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(10, getActivity()));
        passwordEtCs.connect(
                passwordEditText.getId(),
                ConstraintSet.LEFT,
                passwordTextView.getId(),
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordEtCs.connect(
                passwordEditText.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        passwordEtCs.applyTo(cl);

        // ログインボタンを配置する。
        Button loginButton = new Button(getActivity());
        loginButton.setId(LOGIN_BUTTON_RES_ID);
        loginButton.setText(R.string.login);
        loginButton.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(loginButton);
        ConstraintSet loginBtnCs = new ConstraintSet();
        loginBtnCs.constrainWidth(
                loginButton.getId(),
                ViewUtils.fromDipToPixel(150, getActivity()));
        loginBtnCs.constrainHeight(
                loginButton.getId(),
                ViewUtils.fromDipToPixel(50, getActivity()));
        loginBtnCs.connect(
                loginButton.getId(),
                ConstraintSet.TOP,
                passwordTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        loginBtnCs.connect(
                loginButton.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        loginBtnCs.applyTo(cl);

        // サインインボタンを配置する。
        Button signupButton = new Button(getActivity());
        signupButton.setId(SIGNUP_BUTTON_RES_ID);
        signupButton.setText(R.string.signup);
        signupButton.setTextSize(COMPLEX_UNIT_SP, 18);
        cl.addView(signupButton);
        ConstraintSet signupBtnCs = new ConstraintSet();
        signupBtnCs.constrainWidth(
                signupButton.getId(),
                ViewUtils.fromDipToPixel(150, getActivity()));
        signupBtnCs.constrainHeight(
                signupButton.getId(),
                ViewUtils.fromDipToPixel(50, getActivity()));
        signupBtnCs.connect(
                signupButton.getId(),
                ConstraintSet.TOP,
                passwordTextView.getId(),
                ConstraintSet.BOTTOM,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupBtnCs.connect(
                signupButton.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                ViewUtils.fromDipToPixel(18, getActivity()));
        signupBtnCs.applyTo(cl);


        return cl;
    }
}
