package jp.co.myself.psystem_android.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import jp.co.myself.psystem_android.view.fragment.LoginFragment;
import jp.co.myself.psystem_android.view.fragment.SignupFragment;

public class MainActivity
        extends AppCompatActivity
        implements LoginFragment.OnFragmentInteractionListener,
            SignupFragment.OnFragmentInteractionListener {

    private static int FRAME_LAYOUT_RES_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout cl = new ConstraintLayout(this);

        FrameLayout fl = new FrameLayout(this);
        fl.setId(FRAME_LAYOUT_RES_ID);
        cl.addView(
                fl,
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT));

        setContentView(cl);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                fl.getId(),
                LoginFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();
    }

    @Override
    public void onTapSignupButton() {

        // サインアップ画面に遷移する。
        /*Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);*/
        FrameLayout fl = findViewById(FRAME_LAYOUT_RES_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(
                fl.getId(),
                SignupFragment.newInstance("param1", "param2"));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
