package com.fycx.widgets.item;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fycx.widgets.R;

/**
 * 作者：Created by fengyechenxi on 2018/7/14.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public class MenuItemView extends FrameLayout{

    private ImageView mDotView;
    private ImageView mImageView;
    private TextView mTextView;
    private View mLayoutView;
    /**
     * 用于测量的数据
     */
    private int mLayoutViewHeight = -1;
    private int mLayoutViewRight;

    public MenuItemView(@NonNull Context context) {
        this(context,null);
    }

    public MenuItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MenuItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }


    private void init(Context context,@Nullable AttributeSet attrs, int defStyleAttr){
        Resources resources = getResources();
        int defaultTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,resources.getDisplayMetrics());
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MenuItemView,defStyleAttr,0);
        //iconView
        Drawable iconViewDrawable = a.getDrawable(R.styleable.MenuItemView_iconView);
        int iconViewWidth = a.getDimensionPixelSize(R.styleable.MenuItemView_iconViewWidth, LayoutParams.WRAP_CONTENT);
        int iconViewHeight = a.getDimensionPixelSize(R.styleable.MenuItemView_iconViewHeight, LayoutParams.WRAP_CONTENT);
        //title
        String title = a.getString(R.styleable.MenuItemView_title);
        ColorStateList titleColor = a.getColorStateList(R.styleable.MenuItemView_titleColor);
        float titleSize = a.getDimension(R.styleable.MenuItemView_titleSize, defaultTextSize);
        //dot
        int dotWidth = a.getDimensionPixelSize(R.styleable.MenuItemView_dotWidth, 20);
        int dotHeight = a.getDimensionPixelSize(R.styleable.MenuItemView_dotHeight, 20);
        boolean showDot = a.getBoolean(R.styleable.MenuItemView_showDot, false);

        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.fycx_layout_menu_item_view,this,true);
        mDotView = (ImageView)findViewById(R.id.menuItemView_dotView);
        mImageView = (ImageView)findViewById(R.id.menuItemView_imageView);
        mTextView = (TextView)findViewById(R.id.menuItemView_textView);
        mLayoutView = findViewById(R.id.menuItemView_centerView);

        FrameLayout.LayoutParams dotParams = (LayoutParams) mDotView.getLayoutParams();
        dotParams.width = dotWidth;
        dotParams.height = dotHeight;
        mDotView.setLayoutParams(dotParams);
        showDotView(showDot);

        LinearLayout.LayoutParams imageViewParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
        imageViewParams.width = iconViewWidth;
        imageViewParams.height = iconViewHeight;
        mImageView.setLayoutParams(imageViewParams);
        setImageView(iconViewDrawable);

        if (titleColor != null) {
            mTextView.setTextColor(titleColor);
        }
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleSize);
        setTitle(title);

        setClipChildren(false);
        setClipToPadding(false);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //修改DotView的位置
        if(mLayoutViewHeight == -1){
            mLayoutViewHeight = mLayoutView.getHeight();
            mLayoutViewRight = mLayoutView.getRight();

            int tipsViewWidth = mDotView.getWidth();
            int tipsViewHeight = mDotView.getHeight();

            FrameLayout.LayoutParams params = (LayoutParams) mDotView.getLayoutParams();
            params.bottomMargin = mLayoutViewHeight - tipsViewHeight/2;
            params.leftMargin = mLayoutViewRight - tipsViewWidth/2;
            mDotView.setLayoutParams(params);
        }
    }


    public MenuItemView showDotView(boolean show){
        mDotView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public MenuItemView setImageView(Drawable drawable) {
        if(drawable == null){
            mImageView.setVisibility(View.GONE);
        }
        else {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageDrawable(drawable);
        }
        return this;
    }

    public MenuItemView setImageView(@DrawableRes int drawable) {
        Drawable icon = ContextCompat.getDrawable(getContext(),drawable);
        return setImageView(icon);
    }

    public MenuItemView setImageView(@DrawableRes int drawable,int width,int height) {
        Drawable icon = ContextCompat.getDrawable(getContext(),drawable);
        return setImageView(icon,width,height);
    }

    public MenuItemView setImageView(Drawable drawable,int width,int height) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
        params.width = width;
        params.height = height;
        mImageView.setLayoutParams(params);
        return setImageView(drawable);
    }

    public MenuItemView setTitle(CharSequence text){
        mTextView.setText(text);
        mTextView.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this;
    }

    public MenuItemView setTitle(@StringRes int text){
        String str = getResources().getString(text);
        return setTitle(str);
    }

    public MenuItemView setTitleColor(int color){
        mTextView.setTextColor(color);
        return this;
    }

    public MenuItemView setTitleColor(ColorStateList color){
        mTextView.setTextColor(color);
        return this;
    }

    public MenuItemView setTitleTextSize(float size){
        mTextView.setTextSize(size);
        return this;
    }

    public MenuItemView setTitleTextSize(int unit,float size){
        mTextView.setTextSize(unit,size);
        return this;
    }

}
