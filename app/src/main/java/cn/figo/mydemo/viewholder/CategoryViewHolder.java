package cn.figo.mydemo.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.CategoryBean;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 11:28
 */
public class CategoryViewHolder extends BaseViewHolder<CategoryBean>{
    @Bind(R.id.item_category_icon)
    ImageView itemCategoryIcon;
    @Bind(R.id.item_category_title)
    TextView itemCategoryTitle;

    public CategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_category);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(CategoryBean categoryBean) {
        itemCategoryIcon.setImageResource(categoryBean.icon);
        itemCategoryTitle.setText(categoryBean.title);

    }
}
