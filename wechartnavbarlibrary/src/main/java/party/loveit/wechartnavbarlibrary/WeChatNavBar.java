package party.loveit.wechartnavbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WeChatNavBar extends LinearLayout {
    private List<TabItem> mTabItems = new ArrayList<>();
    private List<NavModel> mModels = new ArrayList<>();
    private int mTextSize = 20;
    private int mTextPaddingTop = 0;
    private int mTextColorSelect;
    private int mTextColorNormal;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private Context mContext;
    public WeChatNavBar(Context context) {
        this(context,null);
    }

    public WeChatNavBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeChatNavBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WeChatNavBar);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.WeChatNavBar_textSize, 28);
        mTextPaddingTop = typedArray.getDimensionPixelSize(R.styleable.WeChatNavBar_textPaddingTop, 0);
        mTextColorSelect = typedArray.getColor(R.styleable.WeChatNavBar_textColorSelect, 0xff45c01a);
        mTextColorNormal = typedArray.getColor(R.styleable.WeChatNavBar_textColorNormal, 0xfffd6800);
        typedArray.recycle();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mOnPageChangeListener = onPageChangeListener;
    }

    public void setViewPager(ViewPager viewPager) {
        setViewPager(viewPager, 0);
    }

    /**
     * before you should call setModels method
     * @param viewPager
     * @param curPage
     */
    public void setViewPager(ViewPager viewPager, int curPage){
        if(viewPager == null)
            throw new RuntimeException("ViewPager can't null");
        if(mModels == null || mModels.size() == 0)
            throw new RuntimeException("you must call setModels method before");
            mViewPager = viewPager;
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(mOnPageChangeListener != null)
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);


                if (positionOffset > 0 && mTabItems.size() > 1 && (position + 1) < mTabItems.size()) {
                    TabItem left = mTabItems.get(position);
                    TabItem right = mTabItems.get(position + 1);
                    left.setTabAlpha(1 - positionOffset);
                    right.setTabAlpha(positionOffset);
                }
                if (positionOffset == 0 && mViewPager.getCurrentItem() < mTabItems.size()) {
                    resetOtherTabs();
                    mTabItems.get(mViewPager.getCurrentItem()).setTabAlpha(1.0f);
                }

            }

            @Override
            public void onPageSelected(int position) {
                if(mOnPageChangeListener != null)
                    mOnPageChangeListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(mOnPageChangeListener != null)
                    mOnPageChangeListener.onPageScrollStateChanged(state);
            }
        });
        mViewPager.setCurrentItem(curPage,false);
    }

    private void resetOtherTabs() {
        for (int i = 0; i < mTabItems.size(); i++) {
            mTabItems.get(i).setTabAlpha(0);
        }
    }

    public void setModels(List<NavModel> models){
        if(models == null || models.size() < 2)
            throw new RuntimeException("models is null or size < 2");
        mModels.clear();
        mModels.addAll(models);
        for (int i = 0;i < models.size(); i ++){
            NavModel model = models.get(i);
           TabItem item = new TabItem(mContext,mTextSize,mTextColorSelect,mTextColorNormal,model.getTextValue(),
                   model.getIconNormalRes(),model.getIconSelectRes(),mTextPaddingTop);

          /* item.setIconNormal(model.getIconNormalRes());
           item.setIconSelect(model.getIconSelectRes());
           item.setTextColorNormal(mTextColorNormal);
           item.setTextColorSelect(mTextColorSelect);
           item.setTextValue(model.getTextValue());
           item.setTextSize(mTextSize);*/
           final int index = i;
           item.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   resetOtherTabs();
                   mTabItems.get(index).setTabAlpha(1.0f);
                   mViewPager.setCurrentItem(index, false);
               }
           });
           mTabItems.add(item);
           addView(item);
         LinearLayout.LayoutParams params = (LayoutParams) item.getLayoutParams();
         params.height = LayoutParams.MATCH_PARENT;
         params.width = 0;
         params.weight = 1;
         item.setLayoutParams(params);
        }
    }

    public TabItem getTabItem(int index){
        TabItem item = null;
        if(mTabItems != null && mTabItems.size() > index)
            item = mTabItems.get(index);
        return item;
    }
}
