package jp.co.myself.psystem_android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * ログイン画面のメインフラグメント。
 */
public class LoginMainFragment
        extends Fragment
        implements LoginFragment.OnFragmentInteractionListener {

    private static final int FRAMELAYOUT_RES_ID = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginMainFragment() {
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
    public static LoginMainFragment newInstance(String param1, String param2) {
        LoginMainFragment fragment = new LoginMainFragment();
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

        ConstraintLayout cl = new ConstraintLayout(getActivity());

        FrameLayout fl = new FrameLayout(getActivity());
        fl.setId(FRAMELAYOUT_RES_ID);
        cl.addView(
                fl,
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT));

        FragmentManager fm = getChildFragmentManager();
        if (savedInstanceState != null) {
            return cl;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(
                fl.getId(),
                LoginFragment.newInstance("param1", "param2"));
        ft.commit();

        return cl;

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

    @Override
    public void onTapLoginButton() {
        Log.d(
                "LoginMainFragment",
                "LoginMainFragment onTapLoginButton");
    }

    @Override
    public void onTapSignupButton() {
        Log.d(
                "LoginMainFragment",
                "LoginMainFragment onTapSignupButton");
        mListener.onTapSignupButton();
    }

    public interface OnFragmentInteractionListener {
        // 本フラグメントのサインアップボタンをタップした時のイベント処理。
        void onTapSignupButton();

    }

}
