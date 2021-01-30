package com.istone.testbuy.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseRecyclerAdapter;
import com.istone.testbuy.base.BaseViewHolder;
import com.istone.testbuy.databinding.SearchListAutoNewBinding;
import com.istone.testbuy.ui.entity.GoodsDetailResponse;
import com.istone.testbuy.ui.entity.GoodsSkInfolResponse;
import com.istone.testbuy.ui.listener.OnSearchSelectListener;

import java.util.List;


public class SearchAutoAdapterNew extends BaseRecyclerAdapter<GoodsSkInfolResponse.SkuItemsBean, SearchAutoAdapterNew.SearchAutoViewHolder> {
	private int selecPosition = 0;
	public SearchAutoAdapterNew(List<GoodsSkInfolResponse.SkuItemsBean> list, OnSearchSelectListener onAutoClickListener) {
		super(list);
		this.onAutoClickListener = onAutoClickListener;
	}

	private OnSearchSelectListener onAutoClickListener;

	public int getSelecPosition() {
		return selecPosition;
	}

	public void setSelecPosition(int selecPosition) {
		this.selecPosition = selecPosition;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public SearchAutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		SearchListAutoNewBinding binding = getHolderBinding(parent, R.layout.search_list_auto_new);
		SearchAutoViewHolder viewHolder = new SearchAutoViewHolder(binding);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull SearchAutoViewHolder holder, int position) {
		holder.setData(list.get(position),position);
	}

	public class SearchAutoViewHolder extends BaseViewHolder<GoodsSkInfolResponse.SkuItemsBean, SearchListAutoNewBinding> {
		public SearchAutoViewHolder(@NonNull SearchListAutoNewBinding searchListAutoNewBinding) {
			super(searchListAutoNewBinding);
		}

		@Override
		public void setData(GoodsSkInfolResponse.SkuItemsBean searchWordBean, int position) {
			super.setData(searchWordBean, position);
			binding.searchLeftView.setText("尺码 "+searchWordBean.getSize()+" 库存 "+searchWordBean.getStock());
			if (selecPosition == position)
				binding.searchLeftView.setTextColor(context.getResources().getColor(R.color.ysf_red_9d3b39));
			else
				binding.searchLeftView.setTextColor(context.getResources().getColor(R.color.black));
			binding.getRoot().setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (onAutoClickListener!=null)
						onAutoClickListener.click(searchWordBean.getSize(),position);
				}
			});
		}
	}
}
