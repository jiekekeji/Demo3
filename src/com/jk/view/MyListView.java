package com.jk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	//
	// @Override
	// public boolean onInterceptTouchEvent(MotionEvent ev) {
	//
	// switch (ev.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// // 当手指触到listview的时候，让父ScrollView交出ontouch权限，也就是让父scrollview停住不能滚动
	// setParentScrollAble(false);
	// LogUtils.d("onInterceptTouchEventdown");
	// case MotionEvent.ACTION_MOVE:
	//
	// LogUtils.d("onInterceptTouchEvent move");
	// break;
	//
	// case MotionEvent.ACTION_UP:
	//
	// LogUtils.d("onInterceptTouchEvent up");
	// case MotionEvent.ACTION_CANCEL:
	// LogUtils.d("onInterceptTouchEvent cancel");
	// // 当手指松开时，让父ScrollView重新拿到onTouch权限
	// setParentScrollAble(true);
	//
	// break;
	// default:
	// break;
	//
	// }
	// return super.onInterceptTouchEvent(ev);
	// }
	//
	// /**
	// * 是否把滚动事件交给父scrollview
	// *
	// * @param flag
	// */
	// private void setParentScrollAble(boolean flag) {
	// // 这里的parentScrollView就是listview外面的那个scrollview
	// getParent().requestDisallowInterceptTouchEvent(!flag);
	//
	// }
}
