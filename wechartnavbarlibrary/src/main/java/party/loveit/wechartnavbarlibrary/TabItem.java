package party.loveit.wechartnavbarlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by wangyunpeng on 2016/3/10.
 */
 class TabItem extends View {

    private int mTextSize = 12;
    private int mTextColorSelect;
    private int mTextColorNormal;
    private int mTextPaddingTop;
    private boolean mTextNormalIsBold;
    private boolean mTextSelectIsBold;
    private Paint mTextPaintNormal;
    private Paint mTextPaintSelect;
    private int mViewHeight, mViewWidth;
    private String mTextValue ;
    private Bitmap mIconNormal;
    private Bitmap mIconSelect;
    private Rect mBoundText;
    private Paint mIconPaintSelect;
    private Paint mIconPaintNormal;

    TabItem(Context context) {
        this(context, null);
    }

    TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initView();

    }
    TabItem(Context context,int textSize, int textColorSelect, int textColorNormal,
            String textValue, int iconNormalRes, int iconSelectRes, int textPaddingTop, boolean isNormalTextBold, boolean isSelectTextBold){
        this(context);
        mBoundText = new Rect();
        this.mTextSize = textSize;
        this.mTextColorSelect = textColorSelect;
        this.mTextColorNormal = textColorNormal;
        this.mTextValue = textValue;
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), iconNormalRes);
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), iconSelectRes);
        this.mTextPaddingTop = textPaddingTop;
        this.mTextNormalIsBold = isNormalTextBold;
        this.mTextSelectIsBold = isSelectTextBold;
        initText();
    }

    private void initView() {
        //mBoundText = new Rect();
       // TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TabItem);
       // mTextSize = 14;
       //// mTextColorSelect = Color.RED;
       // mTextColorNormal = Color.WHITE;
        //mTextValue = typedArray.getString(R.styleable.TabItem_textValue);
        /*BitmapDrawable drawableIconNormal = (BitmapDrawable) typedArray.getDrawable(R.styleable.TabItem_iconNormal);
        mIconNormal = drawableIconNormal.getBitmap();
        BitmapDrawable drawableIconSelect = (BitmapDrawable) typedArray.getDrawable(R.styleable.TabItem_iconSelect);
        mIconSelect = drawableIconSelect.getBitmap();*/
       // typedArray.recycle();
    }

    private void initText() {
        mTextPaintNormal = new Paint();
        mTextPaintNormal.setTextSize(mTextSize);
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAntiAlias(true);
        mTextPaintNormal.setAlpha(0xff);
        mTextPaintNormal.setFakeBoldText(mTextNormalIsBold);

        mTextPaintSelect = new Paint();
        mTextPaintSelect.setTextSize(mTextSize);
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAntiAlias(true);
        mTextPaintSelect.setAlpha(0);
        mTextPaintSelect.setFakeBoldText(mTextSelectIsBold);

        mIconPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mIconPaintSelect.setAlpha(0);

        mIconPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mIconPaintNormal.setAlpha(0xff);
    }

    private void measureText() {
        if(mTextValue != null)
          mTextPaintNormal.getTextBounds(mTextValue, 0, mTextValue.length(), mBoundText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mIconNormal == null || mIconSelect == null)
            return;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

        measureText();
        int contentWidth = Math.max(mBoundText.width(), mIconNormal.getWidth());
        int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(widthSize, desiredWidth);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidth;
                break;
        }
        int contentHeight = mBoundText.height() + mIconNormal.getHeight() + mTextPaddingTop;
        int desiredHeight = getPaddingTop() + getPaddingBottom() + contentHeight;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(heightSize, desiredHeight);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = contentHeight;
                break;
        }
        setMeasuredDimension(width, height);
        mViewWidth = getMeasuredWidth() ;
        mViewHeight = getMeasuredHeight() ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mIconNormal != null && mIconSelect != null)
           drawBitmap(canvas) ;
        if(mTextValue != null)
           drawText(canvas) ;
    }

    private void drawBitmap(Canvas canvas) {
        int left = (mViewWidth - mIconNormal.getWidth())/2 ;
        int top = (mViewHeight - mIconNormal.getHeight() - mBoundText.height() - mTextPaddingTop) /2 ;
        canvas.drawBitmap(mIconNormal, left, top ,mIconPaintNormal);
        canvas.drawBitmap(mIconSelect, left, top , mIconPaintSelect);
    }
    private void drawText(Canvas canvas) {
        float x = (mViewWidth - mBoundText.width())/2.0f ;
        float y = (mViewHeight + mIconNormal.getHeight() + mBoundText.height() + mTextPaddingTop) /2.0F ;
        canvas.drawText(mTextValue,x,y, mTextPaintNormal);
        canvas.drawText(mTextValue,x,y, mTextPaintSelect);
    }

    void setTextSize(int textSize) {
        this.mTextSize = textSize;
        mTextPaintNormal.setTextSize(textSize);
        mTextPaintSelect.setTextSize(textSize);
    }

    void setTextColorSelect(int mTextColorSelect) {
        this.mTextColorSelect = mTextColorSelect;
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAlpha(0);
    }

    void setTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAlpha(0xff);
    }

    void setTextValue(String TextValue) {
        this.mTextValue = TextValue;
    }
    void setIconText(int[] iconSelId,String TextValue) {
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), iconSelId[0]);
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), iconSelId[1]);
        this.mTextValue = TextValue;
    }

    void setIconNormal(int resId){
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), resId);
    }
    void setIconSelect(int resId){
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), resId);
    }
    void setTabAlpha(float alpha){
        int paintAlpha = (int)(alpha*255) ;
        mIconPaintSelect.setAlpha(paintAlpha);
        mIconPaintNormal.setAlpha(255-paintAlpha);
        mTextPaintSelect.setAlpha(paintAlpha);
        mTextPaintNormal.setAlpha(255-paintAlpha);
        invalidate();
    }
}
