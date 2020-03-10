package jp.co.myself.psystem_android.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import jp.co.myself.psystem_android.view.fragment.SignupFragment;

// サインアップ画面のアクティビティ。
public class SignupActivity extends AppCompatActivity implements SignupFragment.OnFragmentInteractionListener {

    private static final int FRAME_LAYOUT_RES_ID = 1;

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
                SignupFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
