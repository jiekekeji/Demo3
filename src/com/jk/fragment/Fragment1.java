package com.jk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jk.demo3.R;
import com.jk.view.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class Fragment1 extends Fragment {

	private static final String[] megs = { "测试", "测试", "测试", "测试", "测试", "测试",
			"测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试",
			"测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试", "测试",
			"测试", "测试", "测试" };

	@ViewInject(R.id.listview)
	private MyListView listview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_001, null);
		ViewUtils.inject(this, view);

		initListView();
		return view;
	}

	private void initListView() {
		listview.setAdapter(new MyAdapter());
		listview.setSelectionAfterHeaderView();
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return megs.length;
		}

		@Override
		public Object getItem(int position) {
			return megs[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				LogUtils.i("新建");
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.item_001, null);
				holder.tView = (TextView) convertView.findViewById(R.id.tv);

				convertView.setTag(holder);
			} else {
				LogUtils.i("复用");
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tView.setText(megs[position] + position);

			return convertView;

		}

		public final class ViewHolder {
			public TextView tView;
		}

	}
}
