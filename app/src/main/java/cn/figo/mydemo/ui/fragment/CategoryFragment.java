package cn.figo.mydemo.ui.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.CategoryAdapter;
import cn.figo.mydemo.base.basefragment.BaseFragment;
import cn.figo.mydemo.bean.CategoryBean;

/**
 * User: Ligx
 * Date: 2016-02-25
 * Time: 11:25
 * 分区
 */
public class CategoryFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    private String[] mFenquArray;
    private int[] mIconArray;
    private List<CategoryBean> categoryBeans;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_category;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));

        mFenquArray = getResources().getStringArray(R.array.fenqu);
        mIconArray = new int[]{R.mipmap.ic_category_01, R.mipmap.ic_category_02, R.mipmap.ic_category_03,
                R.mipmap.ic_category_04, R.mipmap.ic_category_05, R.mipmap.ic_category_06,
                R.mipmap.ic_category_07, R.mipmap.ic_category_08, R.mipmap.ic_category_09, R.mipmap.ic_category_10,
                R.mipmap.ic_category_11, R.mipmap.ic_category_12, R.mipmap.ic_category_13};

        categoryBeans = new ArrayList<>();
        for(int i = 0;i<mFenquArray.length;i++){
            CategoryBean bean = new CategoryBean();
            bean.icon = mIconArray[i];
            bean.title = mFenquArray[i];
            categoryBeans.add(bean);
        }

        categoryAdapter = new CategoryAdapter(mContext);
        categoryAdapter.addAll(categoryBeans);
        recyclerView.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toast(categoryBeans.get(position).getTitle());
            }
        });
    }
}
