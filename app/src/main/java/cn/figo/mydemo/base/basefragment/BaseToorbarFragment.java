package cn.figo.mydemo.base.basefragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import cn.figo.mydemo.R;

/**
 * Created by SlienceDut on 2015/12/12.
 */
public abstract class BaseToorbarFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    protected
    Toolbar mToolBar;


    protected abstract int getTitle();

    @Override
    protected void init() {
        initToolbar();
        setHasOptionsMenu(true);

    }

    protected void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getTitle());
//        mToolBar.setNavigationIcon(R.mipmap.ic_menu_white);
    }

}
