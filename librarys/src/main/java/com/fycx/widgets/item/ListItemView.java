package com.fycx.widgets.item;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fycx.widgets.R;

/**
 * 作者：Created by fengyechenxi on 2018/7/14.
 * 邮箱：3160744624@qq.com
 * github：https://github.com/fengyechenxi
 */
public class ListItemView extends LinearLayout{

    private ImageView mImageView;
    private TextView mLeftTextView;
    private TextView mRightTextView;
    private ImageView mTipsView;
    private ImageView mAccessoryView;

    public ListItemView(Context context) {
        this(context,null);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
        setOrientation(HORIZONTAL);
    }


    private void init(Context context,@Nullable AttributeSet attrs, int defStyleAttr){
        Resources resources = getResources();
        int defaultTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,resources.getDisplayMetrics());
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ListItemView,defStyleAttr,0);

        //imageView
        Drawable imageViewDrawable = a.getDrawable(R.styleable.ListItemView_imageView);
        int imageViewWidth = a.getDimensionPixelSize(R.styleable.ListItemView_imageViewWidth, LayoutParams.WRAP_CONTENT);
        int imageViewHeight = a.getDimensionPixelSize(R.styleable.ListItemView_imageViewHeight, LayoutParams.WRAP_CONTENT);

        //leftText
        String leftText = a.getString(R.styleable.ListItemView_leftText);
        ColorStateList leftTextColor = a.getColorStateList(R.styleable.ListItemView_leftTextColor);
        int leftTextSize = (int) a.getDimension(R.styleable.ListItemView_leftTextSize, defaultTextSize);

        //rightText
        String rightText = a.getString(R.styleable.ListItemView_rightText);
        ColorStateList rightTextColor = a.getColorStateList(R.styleable.ListItemView_rightTextColor);
        int rightTextSize = (int) a.getDimension(R.styleable.ListItemView_rightTextSize, defaultTextSize);
        //tipsDrawable
        Drawable tipsDrawable = a.getDrawable(R.styleable.ListItemView_tipsDrawable);
        int tipsDrawableWidth = a.getDimensionPixelSize(R.styleable.ListItemView_tipsDrawableWidth, LayoutParams.WRAP_CONTENT);
        int tipsDrawableHeight = a.getDimensionPixelSize(R.styleable.ListItemView_tipsDrawableHeight, LayoutParams.WRAP_CONTENT);


        //accessoryView
        Drawable accessoryViewDrawable = a.getDrawable(R.styleable.ListItemView_accessoryView);
        int accessoryViewWidth = a.getDimensionPixelSize(R.styleable.ListItemView_accessoryViewWidth, LayoutParams.WRAP_CONTENT);
        int accessoryViewHeight = a.getDimensionPixelSize(R.styleable.ListItemView_accessoryViewHeight, LayoutParams.WRAP_CONTENT);

        //background
        Drawable backgroundDrawable = a.getDrawable(R.styleable.ListItemView_itemViewBackground);

        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.fycx_layout_list_item_view,this,true);
        mImageView = (ImageView)findViewById(R.id.itemView_imageView);
        mLeftTextView = (TextView)findViewById(R.id.itemView_leftTextView);
        mRightTextView = (TextView)findViewById(R.id.itemView_rightTextView);
        mTipsView = (ImageView)findViewById(R.id.itemView_tipsView);
        mAccessoryView = (ImageView)findViewById(R.id.itemView_accessoryView);

        if(imageViewDrawable != null){
            mImageView.setImageDrawable(imageViewDrawable);
        }
        LinearLayout.LayoutParams imageViewParams = (LayoutParams) mImageView.getLayoutParams();
        imageViewParams.width = imageViewWidth;
        imageViewParams.height = imageViewHeight;
        mImageView.setLayoutParams(imageViewParams);

        mLeftTextView.setText(leftText);
        mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftTextSize);
        if(leftTextColor != null){
            mLeftTextView.setTextColor(leftTextColor);
        }

        mRightTextView.setText(rightText);
        mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,rightTextSize);
        if(rightTextColor != null){
            mRightTextView.setTextColor(rightTextColor);
        }

        if(tipsDrawable != null){
            mTipsView.setImageDrawable(tipsDrawable);
        }
        LinearLayout.LayoutParams tipsParams = (LayoutParams) mTipsView.getLayoutParams();
        tipsParams.width = tipsDrawableWidth;
        tipsParams.height = tipsDrawableHeight;
        mTipsView.setLayoutParams(tipsParams);

        if(accessoryViewDrawable != null){
            mAccessoryView.setImageDrawable(accessoryViewDrawable);
        }
        LinearLayout.LayoutParams accessoryParams = (LayoutParams) mAccessoryView.getLayoutParams();
        accessoryParams.width = accessoryViewWidth;
        accessoryParams.height = accessoryViewHeight;
        mAccessoryView.setLayoutParams(accessoryParams);


        if(backgroundDrawable != null){
            ViewCompat.setBackground(this,backgroundDrawable);
        }
        else {
            ViewCompat.setBackground(this,ContextCompat.getDrawable(context,R.drawable.fycx_bg_list_item_view));
        }
    }

    public ListItemView setImageView(@DrawableRes int drawable) {
        mImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), drawable));
        return this;
    }

    public ListItemView setImageView(Drawable drawable) {
        mImageView.setImageDrawable(drawable);
        return this;
    }

    public ListItemView setImageView(@DrawableRes int drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mImageView,ContextCompat.getDrawable(getContext(), drawable),width,height);
    }
    public ListItemView setImageView(Drawable drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mImageView,drawable,width,height);
    }

    public ListItemView setTipsView(@DrawableRes int drawable) {
        mTipsView.setImageDrawable(ContextCompat.getDrawable(getContext(), drawable));
        return this;
    }

    public ListItemView setTipsView(Drawable drawable) {
        mTipsView.setImageDrawable(drawable);
        return this;
    }

    public ListItemView setTipsView(@DrawableRes int drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mTipsView,ContextCompat.getDrawable(getContext(), drawable),width,height);
    }

    public ListItemView setTipsView(Drawable drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mTipsView,drawable,width,height);
    }


    public ListItemView setAccessoryView(@DrawableRes int drawable) {
        mAccessoryView.setImageDrawable(ContextCompat.getDrawable(getContext(), drawable));
        return this;
    }

    public ListItemView setAccessoryView(Drawable drawable) {
        mAccessoryView.setImageDrawable(drawable);
        return this;
    }

    public ListItemView setAccessoryView(@DrawableRes int drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mAccessoryView,ContextCompat.getDrawable(getContext(), drawable),width,height);
    }

    public ListItemView setAccessoryView(Drawable drawable, int width, int height) {
        return setImageViewDrawableAndWidth(mAccessoryView,drawable,width,height);
    }

    private ListItemView setImageViewDrawableAndWidth(ImageView imageView, Drawable drawable, int width, int height){
        LinearLayout.LayoutParams params = (LayoutParams) imageView.getLayoutParams();
        params.width = width;
        params.height = height;
        imageView.setLayoutParams(params);
        imageView.setImageDrawable(drawable);
        return this;
    }


    public ListItemView setLeftText(CharSequence text) {
        mLeftTextView.setText(text);
        return this;
    }

    public ListItemView setLeftText(@StringRes int text) {
        mLeftTextView.setText(text);
        return this;
    }

    public ListItemView setLeftTextSize(float size) {
        mLeftTextView.setTextSize(size);
        return this;
    }

    public ListItemView setLeftTextSize(int unit, float size) {
        mLeftTextView.setTextSize(unit,size);
        return this;
    }

    public ListItemView setLeftTextColor(ColorStateList color) {
        mLeftTextView.setTextColor(color);
        return this;
    }

    public ListItemView setLeftTextColor(int color) {
        mLeftTextView.setTextColor(color);
        return this;
    }


    public ListItemView setRightText(CharSequence text) {
        mRightTextView.setText(text);
        return this;
    }
    public ListItemView setRightText(@StringRes int text) {
        mRightTextView.setText(text);
        return this;
    }

    public ListItemView setRightTextSize(float size) {
        mRightTextView.setTextSize(size);
        return this;
    }

    public ListItemView setRightTextSize(int unit, float size) {
        mRightTextView.setTextSize(unit,size);
        return this;
    }

    public ListItemView setRightTextColor(ColorStateList color) {
        mRightTextView.setTextColor(color);
        return this;
    }

    public ListItemView setRightTextColor(int color) {
        mRightTextView.setTextColor(color);
        return this;
    }

}
