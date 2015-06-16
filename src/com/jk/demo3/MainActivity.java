package com.jk.demo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jk.fragment.Fragment1;
import com.jk.fragment.Fragment2;
import com.jk.view.MyScrollView;
import com.jk.view.MyScrollView.OnScrollListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements OnScrollListener {

	/** 悬浮的view容器 **/
	@ViewInject(R.id.float_view_1)
	LinearLayout ll1;

	/** 悬浮的view容器 **/
	@ViewInject(R.id.float_view_2)
	LinearLayout ll2;

	/** 顶部内容 **/
	@ViewInject(R.id.top_content)
	LinearLayout topContent;

	/** 顶部内容的上部位置 **/
	private int topContentLoca;

	/** 悬浮view内容 **/
	@ViewInject(R.id.gp)
	RadioGroup floatView;

	/** 自定义ScrollView ***/
	@ViewInject(R.id.myScrollView)
	MyScrollView myScrollView;

	Fragment fragment001;

	Fragment fragment002;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);

		myScrollView.setOnScrollListener(this);
		fragmentManager = getSupportFragmentManager();

		floatView.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				showFragment(checkedId);
			}
		});

		((RadioButton) floatView.getChildAt(0)).setChecked(true);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			topContentLoca = topContent.getBottom();// 获取searchLayout的顶部位置
		}
	}

	// 监听滚动Y值变化，通过addView和removeView来实现悬停效果
	@Override
	public void onScroll(int scrollY) {
		if (scrollY >= topContentLoca) {
			if (floatView.getParent() != ll2) {
				ll1.removeView(floatView);
				ll2.addView(floatView);
			}
		} else {
			if (floatView.getParent() != ll1) {
				ll2.removeView(floatView);
				ll1.addView(floatView);
			}
		}
	}

	// 当fragment已被实例化，就隐藏起来
	public void hideFragments(FragmentTransaction ft) {
		if (fragment001 != null)
			ft.hide(fragment001);
		if (fragment002 != null)
			ft.hide(fragment002);

	}

	/**
	 * 切换fragment
	 * 
	 * @param fragmentId
	 * @param fragment
	 */
	private void showFragment(int checkedId) {
		LogUtils.i("showFragment");
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);

		switch (checkedId) {
		case R.id.rb001:
			if (fragment001 != null) {
				transaction.show(fragment001);
			} else {
				LogUtils.i("rb001");
				fragment001 = new Fragment1();
				transaction.add(R.id.content, fragment001);
			}
			break;

		case R.id.rb002:
			if (fragment002 != null) {
				transaction.show(fragment002);
			} else {
				LogUtils.i("rb002");
				fragment002 = new Fragment2();
				transaction.add(R.id.content, fragment002);
			}
			break;
		}

		transaction.commit();
	}
}
