package cn.figo.mydemo.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;
import com.mikepenz.actionitembadge.library.utils.BadgeStyle;
import com.mikepenz.actionitembadge.library.utils.NumberUtils;
import com.mikepenz.actionitembadge.library.utils.UIUtil;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.figo.mydemo.R;
import cn.figo.mydemo.app.MyApplication;
import cn.figo.mydemo.base.baseactivity.BaseActivity;
import cn.figo.mydemo.base.basefragment.BaseFragment;
import cn.figo.mydemo.event.RequestSearchEvent;
import cn.figo.mydemo.ui.fragment.BangumiFragment;
import cn.figo.mydemo.ui.fragment.CategoryFragment;
import cn.figo.mydemo.ui.fragment.DiscoverFragment;
import cn.figo.mydemo.ui.fragment.RecommendFragment;

public class IndexActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,Toolbar.OnMenuItemClickListener {

    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.home_tablayout)
    TabLayout homeTablayout;
    @Bind(R.id.home_viewpager)
    ViewPager homeViewpager;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.home_drawerlayout)
    DrawerLayout homeDrawerlayout;

    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_name)
    TextView tvName;

    @Bind(R.id.searchbox)
    SearchBox searchbox;

    @OnClick(R.id.btn_left_opt)
    public void showNavMenu() {
        homeDrawerlayout.openDrawer(GravityCompat.START);
    }

    List<BaseFragment> mFragments;

//    ActionBarDrawerToggle mToggle;

    private BadgeStyle style;
    private int badgeGame = 1;
    private int badgeDownload = 2;

    private static long DOUBLE_CLICK_TIME = 0L;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        EventBus.getDefault().register(this);

        initToolbar();
        initDrawerLayout();
        //获得tabs的标题
        mTabsArray = getResources().getStringArray(R.array.home_tabs);
        tvName.setText("Ligx");

        //添加fragment
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new BangumiFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new DiscoverFragment());


        //tab和viewpager绑定
        //给viewpager设置适配器
        homeViewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        homeTablayout.setupWithViewPager(homeViewpager);

        homeViewpager.setCurrentItem(2);

    }
    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle("BiliPlayer");
        homeToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        homeToolbar.setOnMenuItemClickListener(this);


        style = new BadgeStyle(BadgeStyle.Style.DEFAULT, com.mikepenz.actionitembadge.library.R.layout.menu_badge, Color.parseColor("#FFBB33"), Color.parseColor("#FFBB33"), Color.WHITE);
    }

    /**
     * 初始化抽屉
     */
    private void initDrawerLayout() {
//        mToggle = new ActionBarDrawerToggle(this, homeDrawerlayout, homeToolbar, R.string.open, R.string.close);
//        homeDrawerlayout.setDrawerListener(mToggle);
//        mToggle.syncState();
//        homeToolbar.setNavigationIcon(R.drawable.ic_drawer_home);

        navView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (homeDrawerlayout.isDrawerOpen(GravityCompat.START)) {
                homeDrawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
            if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                toast(getString(R.string.double_click_exit));
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            } else {
//                    getBaseApplication().exitApp();
                ((MyApplication) getApplication()).exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        if (badgeGame == 0) {
            ActionItemBadge.hide(menu.findItem(R.id.action_game));
        } else {
            ActionItemBadge.update(this, menu.findItem(R.id.action_game), UIUtil.getCompatDrawable(this, R.drawable.ic_menu_top_game_center), style, NumberUtils.formatNumber(badgeGame));
        }

        if (badgeDownload == 0) {
            ActionItemBadge.hide(menu.findItem(R.id.action_download));
        } else {
            ActionItemBadge.update(this, menu.findItem(R.id.action_download), UIUtil.getCompatDrawable(this, R.drawable.ic_toolbar_menu_download), style, NumberUtils.formatNumber(badgeDownload));
        }

        new ActionItemBadgeAdder().act(this).menu(menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_download) {

        } else if (id == R.id.nav_start) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_people) {

        } else if (id == R.id.nav_shop) {

        } else if (id == R.id.nav_color) {

        } else if (id == R.id.nav_app) {

        } else if (id == R.id.nav_settings) {

        }

        homeDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_game:
                Toast.makeText(this, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_download:
                Toast.makeText(this, "下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                openSearch();
                break;
        }

        return true;
    }
    private String[] mTabsArray;
    /**
     * viewpager的适配器
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsArray[position];
        }
    }


    public void openSearch() {
        searchbox.revealFromMenuItem(R.id.action_search, this);

        for (int x = 0; x < 10; x++) {
            SearchResult option = new SearchResult("Result "
                    + x, getResources().getDrawable(
                    R.drawable.ic_history));
            searchbox.addSearchable(option);
        }
        searchbox.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
                toast("Menu click");
            }

        });

        searchbox.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                // Use this to tint the screen

            }

            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                searchbox.hideCircularlyToMenuItem(R.id.action_search,IndexActivity.this);
            }

            @Override
            public void onSearchTermChanged(String term) {
                // React to the search term changing
                // Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                toast(searchTerm + " Searched");
                setTitle(searchTerm);

            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to result being clicked
            }

            @Override
            public void onSearchCleared() {

            }

        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(RequestSearchEvent event) {
        openSearch();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}