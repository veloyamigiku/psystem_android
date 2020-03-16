package jp.co.myself.psystem_android.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import jp.co.myself.psystem_android.view.fragment.LoginMainFragment;
import jp.co.myself.psystem_android.view.fragment.SignupMainFragment;

public class MainActivity
        extends AppCompatActivity
        implements LoginMainFragment.OnFragmentInteractionListener,
            SignupMainFragment.OnFragmentInteractionListener {

    private static int FRAME_LAYOUT_RES_ID = 1;

    private FragmentManager fragmentManager = null;
    private FrameLayout frameLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout cl = new ConstraintLayout(this);

        frameLayout = new FrameLayout(this);
        frameLayout.setId(FRAME_LAYOUT_RES_ID);
        cl.addView(
                frameLayout,
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT));

        setContentView(cl);

        // ログイン画面のメインフラグメントに切り替える。
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                frameLayout.getId(),
                LoginMainFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();
    }

    @Override
    public void onTapSignupButton() {

        // サインアップ画面のメインフラグメントに切り替える。
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                frameLayout.getId(),
                SignupMainFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();

    }

    @Override
    public void onCancelSignup() {

        // ログイン画面のメインフラグメントに切り替える。
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                frameLayout.getId(),
                LoginMainFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();

    }

    @Override
    public void onCompleteSignup() {

    }

}
