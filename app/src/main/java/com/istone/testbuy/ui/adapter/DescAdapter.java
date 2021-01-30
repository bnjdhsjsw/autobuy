package com.istone.testbuy.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseRecyclerAdapter;
import com.istone.testbuy.base.BaseViewHolder;
import com.istone.testbuy.databinding.SearchDescBinding;

import java.util.List;


public class DescAdapter extends BaseRecyclerAdapter<String, DescAdapter.SearchAutoViewHolder> {
    public DescAdapter(List<String> list) {
        super(list);
    }

    public void addString(String str)
    {
        list.add(0,str);
        if (list.size()>300)
            list.remove(299);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchDescBinding binding = getHolderBinding(parent, R.layout.search_desc);
        SearchAutoViewHolder viewHolder = new SearchAutoViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAutoViewHolder holder, int position) {
        holder.setData(list.get(position), position);
    }

    public class SearchAutoViewHolder extends BaseViewHolder<String, SearchDescBinding> {
        public SearchAutoViewHolder(@NonNull SearchDescBinding searchListAutoNewBinding) {
            super(searchListAutoNewBinding);
        }

        @Override
        public void setData(String searchWordBean, int position) {
            super.setData(searchWordBean, position);
            binding.searchLeftView.setText(searchWordBean);
            binding.searchLeftView.setTextColor(context.getResources().getColor(R.color.ysf_green_61e19b));
        }
    }
}
