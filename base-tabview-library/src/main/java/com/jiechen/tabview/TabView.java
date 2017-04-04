package com.jiechen.tabview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * TabView
 * Created by JieChen on 2017/4/1.
 */

public class TabView extends RelativeLayout {

    private int mTextSelectColor;
    private int mTextUnSelectColor;

    private int mBackGroundColor;
    private int mHeight;

    private int mImgToTextGap;// 图片和文字之间的间隙
    private int mTextSize;

    private int mImgWidth;
    private int mImgHeight;

    private List<TabViewChild> mTabViewChildList;

    private int mTabViewGravity = Gravity.BOTTOM;// 默认在底部
    private int mTabViewPosition = 0;


    private LinearLayout tabView;
    private FrameLayout mFragmentContainer;
    private List<Integer> unSelectIconList;

    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;

    private int mFlagIndex = 0;
    private int mCurrentTabIndex;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultAttrs(context);
        initCustomAttrs(context, attrs);
        initView(context);
    }

    /**
     * 初始化一些默认的属性
     *
     * @param context 上下文
     */
    private void initDefaultAttrs(Context context) {
        mTextSelectColor = Color.rgb(252, 88, 17);// orange
        mTextUnSelectColor = Color.rgb(129, 130, 149);// black
        mBackGroundColor = Color.rgb(255, 255, 255);// white

        mHeight = dp2px(context, 52);
        mImgToTextGap = dp2px(context, 2);
        mTextSize = sp2px(context, 10);

        mImgWidth = dp2px(context, 30);
        mImgHeight = dp2px(context, 30);
    }

    /**
     * 初始化用户自定义属性
     *
     * @param context 上下文
     * @param attrs   属性
     */
    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int index, TypedArray typedArray) {
        if (index == R.styleable.TabView_tab_text_select_color) {
            mTextSelectColor = typedArray.getColor(index, mTextSelectColor);
        } else if (index == R.styleable.TabView_tab_text_un_select_color) {
            mTextUnSelectColor = typedArray.getColor(index, mTextUnSelectColor);
        } else if (index == R.styleable.TabView_tab_back_ground_color) {
            mBackGroundColor = typedArray.getColor(index, mBackGroundColor);
        } else if (index == R.styleable.TabView_tab_height) {
            mHeight = typedArray.getDimensionPixelSize(index, mHeight);
        } else if (index == R.styleable.TabView_tab_img_to_text_gap) {
            mImgToTextGap = typedArray.getDimensionPixelSize(index, mImgToTextGap);
        } else if (index == R.styleable.TabView_tab_text_size) {
            mTextSize = typedArray.getDimensionPixelSize(index, mTextSize);
        } else if (index == R.styleable.TabView_tab_img_height) {
            mImgHeight = typedArray.getDimensionPixelSize(index, mImgHeight);
        } else if (index == R.styleable.TabView_tab_img_width) {
            mImgWidth = typedArray.getDimensionPixelSize(index, mImgWidth);
        } else if (index == R.styleable.TabView_tab_gravity) {
            mTabViewGravity = typedArray.getInt(index, mTabViewGravity);
        } else if (index == R.styleable.TabView_tab_position) {
            mTabViewPosition = typedArray.getInteger(index, mTabViewPosition);
            mCurrentTabIndex = mTabViewPosition;
        }
    }

    /**
     * 初始化View
     *
     * @param context 上下文
     */
    private void initView(Context context) {
        tabView = new LinearLayout(context);
        tabView.setId(R.id.tabview_id);

        mFragmentContainer = new FrameLayout(context);
        mFragmentContainer.setId(R.id.tabview_fragment_container);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams tabViewParams = null;

        if (mTabViewGravity == Gravity.BOTTOM) {
            tabViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
            tabView.setOrientation(LinearLayout.HORIZONTAL);
            tabViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.ABOVE, R.id.tabview_id);
        } else if (mTabViewGravity == Gravity.START || mTabViewGravity == Gravity.LEFT) {
            tabViewParams = new RelativeLayout.LayoutParams(mHeight, RelativeLayout.LayoutParams.MATCH_PARENT);
            tabView.setOrientation(LinearLayout.VERTICAL);
            tabViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.tabview_id);
        } else if (mTabViewGravity == Gravity.TOP) {
            tabViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mHeight);
            tabView.setOrientation(LinearLayout.HORIZONTAL);
            tabViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.BELOW, R.id.tabview_id);
        } else if (mTabViewGravity == Gravity.END || mTabViewGravity == Gravity.RIGHT) {
            tabViewParams = new RelativeLayout.LayoutParams(mHeight, RelativeLayout.LayoutParams.MATCH_PARENT);
            tabView.setOrientation(LinearLayout.VERTICAL);
            tabViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.LEFT_OF, R.id.tabview_id);
        }

        tabView.setLayoutParams(tabViewParams);
        tabView.setBackgroundColor(mBackGroundColor);

        mFragmentContainer.setLayoutParams(params);
        this.addView(tabView);
        this.addView(mFragmentContainer);
    }

    /**
     * 设置TabViewChild
     *
     * @param tabViewChild    tabViewChild集合
     * @param fragmentManager v4包的FragmentManager
     */
    public void setTabViewChild(List<TabViewChild> tabViewChild, FragmentManager fragmentManager) {
        mTabViewChildList = tabViewChild;
        mFragmentManager = fragmentManager;
        if (mTabViewPosition >= tabViewChild.size()) {
            mFlagIndex = 0;
            mCurrentTabIndex = 0;
            mTabViewPosition = 0;
        }
        initTabChildView();
        invalidate();
    }

    private void initTabChildView() {
        tabView.removeAllViews();
        unSelectIconList = new ArrayList<>();
        mFragments = new Fragment[mTabViewChildList.size()];

        for (int i = 0; i < mFragments.length; i++) {
            final TabViewChild tabViewChild = mTabViewChildList.get(i);
            mFragments[i] = tabViewChild.getFragment();
        }

        if (mTabViewPosition >= mTabViewChildList.size()) {
            mFragmentManager.beginTransaction().add(R.id.tabview_fragment_container, mFragments[0]).show(mFragments[0]).commit();
        } else {
            mFragmentManager.beginTransaction().add(R.id.tabview_fragment_container, mFragments[mTabViewPosition]).show(mFragments[mTabViewPosition]).commit();
        }

        for (int i = 0; i < mTabViewChildList.size(); i++) {
            final TabViewChild tabViewChild = mTabViewChildList.get(i);

            LinearLayout tabChild = new LinearLayout(getContext());
            tabChild.setGravity(Gravity.CENTER);
            tabChild.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams tabChildParams = null;

            if (mTabViewGravity == Gravity.BOTTOM || mTabViewGravity == Gravity.TOP) {
                tabChildParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                tabChildParams.gravity = Gravity.CENTER_HORIZONTAL;
            } else if (mTabViewGravity == Gravity.START || mTabViewGravity == Gravity.END || mTabViewGravity == Gravity.LEFT || mTabViewGravity == Gravity.RIGHT) {
                tabChildParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f);
                tabChildParams.gravity = Gravity.CENTER_VERTICAL;
            }

            tabChild.setLayoutParams(tabChildParams);

            final ImageView imgView = new ImageView(getContext());
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(mImgWidth, mImgHeight);
            imgParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;

            imgView.setLayoutParams(imgParams);
            imgView.setImageResource(tabViewChild.getNoSelectIcon());
            unSelectIconList.add(tabViewChild.getNoSelectIcon());
            tabChild.addView(imgView);

            final TextView textView = new TextView(getContext());
            textView.setText(tabViewChild.getText());
            textView.setTextColor(mTextUnSelectColor);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);

            final LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(textParams);
            tabChild.addView(textView);

            tabView.addView(tabChild);

            final int currentPosition = i;
            if (mTabViewPosition >= mTabViewChildList.size()) {
                if (i == 0) {
                    imgView.setImageResource(tabViewChild.getSelectIcon());
                    textView.setText(tabViewChild.getText());
                    textView.setTextColor(mTextSelectColor);
                }
            } else {
                if (mTabViewPosition == i) {
                    imgView.setImageResource(tabViewChild.getSelectIcon());
                    textView.setText(tabViewChild.getText());
                    textView.setTextColor(mTextSelectColor);
                }
            }

            tabChild.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 重置为默认
                    resetAll();

                    imgView.setImageResource(tabViewChild.getSelectIcon());
                    textView.setText(tabViewChild.getText());
                    textView.setTextColor(mTextSelectColor);
                    mFlagIndex = currentPosition;

                    showOrHide();

                    // 回调监听
                    if (mOnTabChildClickListener != null) {
                        mOnTabChildClickListener.onTabChildClick(currentPosition, imgView, textView);
                    }
                }
            });
        }
    }

    /**
     * 切换显示状态
     */
    private void showOrHide() {
        if (mCurrentTabIndex != mFlagIndex) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.hide(mFragments[mCurrentTabIndex]);

            if (!mFragments[mFlagIndex].isAdded()) {
                transaction.add(R.id.tabview_fragment_container, mFragments[mFlagIndex]);
            }
            transaction.show(mFragments[mFlagIndex]).commitAllowingStateLoss();
        }
        mCurrentTabIndex = mFlagIndex;
    }

    /**
     * 恢复所有的状态
     */
    private void resetAll() {
        for (int i = 0; i < tabView.getChildCount(); i++) {
            LinearLayout tabChild = (LinearLayout) tabView.getChildAt(i);


            ImageView imgView = (ImageView) tabChild.getChildAt(0);
            TextView textView = (TextView) tabChild.getChildAt(1);
            imgView.setImageResource(unSelectIconList.get(i));
            textView.setTextColor(mTextUnSelectColor);
        }
    }

    /**
     * 设置TabView的高度
     *
     * @param height 高度单位为dp
     */
    public void setTabViewHeight(int height) {
        mHeight = dp2px(getContext(), height);
    }

    /**
     * 设置图片和文字之前的距离
     *
     * @param gap 距离单位为dp
     */
    public void setTabViewImgToTextGap(int gap) {
        mImgToTextGap = dp2px(getContext(), gap);
    }

    /**
     * 设置文字的大小
     *
     * @param textSize 大小单位为sp
     */
    public void setTabViewTextSize(int textSize) {
        mTextSize = sp2px(getContext(), textSize);
    }

    /**
     * 设置图片的宽度
     *
     * @param width 宽度单位为dp
     */
    public void setTabViewImgWidth(int width) {
        mImgWidth = dp2px(getContext(), width);
    }

    /**
     * 设置图片的高度
     *
     * @param height 高度单位为dp
     */
    public void setTabViewImgHeight(int height) {
        mImgHeight = dp2px(getContext(), height);
    }

    /**
     * 设置tabView的Gravity
     *
     * @param gravity Gravity
     */
    public void setTabViewGravity(int gravity) {
        mTabViewGravity = gravity;
    }

    /**
     * 设置当前默认选中的位置
     *
     * @param position 位置
     */
    public void setTabViewPosition(int position) {
        mTabViewPosition = position;
        mCurrentTabIndex = position;
    }

    /**
     * 设置背景颜色
     * @param color 背景颜色
     */
    public void setBackGroundColor(int color) {
        mBackGroundColor = color;
        tabView.setBackgroundColor(mBackGroundColor);
    }


    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    private int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    // 接口回调
    public interface OnTabChildClickListener {
        void onTabChildClick(int position, ImageView imgView, TextView textView);
    }

    private OnTabChildClickListener mOnTabChildClickListener;

    /**
     * 设置tabChild点击事件
     *
     * @param listener 事件
     */
    public void setOnTabChildClickListener(OnTabChildClickListener listener) {
        mOnTabChildClickListener = listener;
    }

}
