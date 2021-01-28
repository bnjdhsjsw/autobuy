package com.istone.testbuy.ui.adapter;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager cycle</br>
 * banner 循环播放使用
 */
public abstract class AbBaseCyclePagerAdapter<T> extends PagerAdapter {

    protected abstract View getView(T lst, int position);

    protected abstract void setCurrentDot(int position);

    protected List<T> mList = new ArrayList<>();
    // private List<View> views;
    protected ViewPager mViewPager;
    private boolean mIsCycle = false;
    private boolean mIsChanged = false;
    private int mMessageTag = 89757;
    private int DELAYMILLIS = 4000;
    private int mCurrentPagePosition;
    private int dotsSize;
    private boolean mIsTrue = true;

    public AbBaseCyclePagerAdapter(ViewPager vp, List<T> lst) {
        this.mList.clear();
        this.mList.addAll(lst);
        this.mViewPager = vp;
        this.mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        cycleJudge();
    }

    public AbBaseCyclePagerAdapter(ViewPager vp, List<T> lst, boolean mIsTrue) {
        this.mList.clear();
        this.mList.addAll(lst);
        this.mViewPager = vp;
        this.mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        Log.i("mip_tesgg", "AbBaseCyclePagerAdapter: " + mList.size());
        this.mIsTrue = mIsTrue;
        if (mIsTrue)
            cycleJudge();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager v = (ViewPager) container;
        View view = (View) object;
        v.removeView(view);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(null);
        }
        view = null;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager v = (ViewPager) container;
        View view = getView(mList.get(position), position);
        if (null != view)
            v.addView(view, 0);
        return view;
    }

    /**
     * 判断是否满足循环滚动的条件
     */
    private void cycleJudge() {
        dotsSize = null == mList ? 0 : mList.size();
        if (null != mList && mList.size() > 1) {
            T first = mList.get(0);
            T last = mList.get(mList.size() - 1);
            mList.add(0, last);
            mList.add(first);
            mIsCycle = true;
        }
        // views = new ArrayList<View>();
        // for (int i = 0; i < mList.size(); i++) {
        // views.add(getView(mList.get(i), i));
        // }
    }

    public void startCycle() {
        if (mIsCycle) {
            if (null != mViewPager)
                mViewPager.setCurrentItem(1, false);
            startScroll();
        }
    }

    public void stopCycle() {
        if (mIsCycle) {
            if (null != mCycleHandler)
                mCycleHandler.removeMessages(mMessageTag);
        }
    }

    /**
     * 实现ViewPager实例默认自动滚动 + 按键事件控制滚动(手指在屏幕上时候取消自动滚动,手指离开恢复自动滚动)
     */
    private void startScroll() {
        viewPagerAutoScrollDefault();
        viewPagerAutoScrollControl();
    }

    private Handler mCycleHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == mMessageTag) {
                mCycleHandler.removeMessages(mMessageTag);
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                updateCurrentSelected(mViewPager.getCurrentItem() + 1);
                mCycleHandler.sendEmptyMessageDelayed(mMessageTag, DELAYMILLIS);
            }
        }
    };

    private void viewPagerAutoScrollDefault() {
        mCycleHandler.sendEmptyMessageDelayed(mMessageTag, DELAYMILLIS);
    }

    private void viewPagerAutoScrollControl() {
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        mCycleHandler.removeMessages(mMessageTag);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mCycleHandler.removeMessages(mMessageTag);
                        mCycleHandler.sendEmptyMessageDelayed(mMessageTag, DELAYMILLIS);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // SCROLL_STATE_IDLE(0) 停止滚动
            if (ViewPager.SCROLL_STATE_IDLE == arg0) {
                if (mIsChanged) {
                    mIsChanged = false;
                    mViewPager.setCurrentItem(mCurrentPagePosition, false);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * A B C 0 1 2 => C A B C A 0 1 2 3 4
         */
        @Override
        public void onPageSelected(int arg0) {
            if (mIsCycle && arg0 == 0) {
                mCurrentPagePosition = getCount() - 2;
                mIsChanged = true;
            } else if (mIsCycle && arg0 == getCount() - 1) {
                mIsChanged = true;
                mCurrentPagePosition = 1;
            } else {
                mCurrentPagePosition = arg0;
            }
            // if (mIsCycle) {
            // arg0 = arg0 % dotsSize;
            // (arg0 % dotsSize - 1) % (dotsSize - 2)
            // }
//            setCurrentDot((arg0 - 1) % dotsSize);

            if (mIsTrue) {
                setCurrentDot((arg0 - 1) % dotsSize);
            } else {
                setCurrentDot(arg0);
            }
        }
    };

    protected void updateCurrentSelected(int position) {

    }

    ;
}
