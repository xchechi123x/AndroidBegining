package com.xiaolaogong.test.common.navigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xiaolaogong.test.R;
import com.xiaolaogong.test.common.tools.Tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chechi on 2017/7/5.
 */

public class BottomLayout extends FrameLayout {

    /*MODE_DEFAULT:默认模式*/
    public static final int MODE_DEFAULT = 0;

    /*MODE_FIXED:按钮固定模式,其他按钮不会有动态效果*/
    public static final int MODE_FIXED = 1;

    /*MODE_SHIFTING:所有的按钮都有动画效果*/
    public static final int MODE_SHIFTING = 2;

    /*默认背景样式*/
    public static final int BACKGROUND_STYLE_DEFAULT = 0;

    /*静态背景样式*/
    public static final int BACKGROUND_STYLE_STATIC = 1;

    /*脉动水波纹背景样式*/
    public static final int BACKGROUND_STYLE_RIPPLE = 2;

    /*默认动画持续时间 200 毫秒*/
    private static final int DEFAULT_ANIMATION_DURATION = 200;

    private static final int MIN_SIZE = 3;

    private static final int MAX_SIZE = 5;

    private static final int DEFAULT_SELECTED_POSITION = -1;

    /*动画持续时间*/
    private int animationDuration = DEFAULT_ANIMATION_DURATION;

    /*脉动动画持续时间 500 毫秒*/
    private int rippleAnimationDuration = (int) (DEFAULT_ANIMATION_DURATION * 2.5);

    /*是否隐藏navigation*/
    private boolean isHidden = false;

    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();

    private ViewPropertyAnimatorCompat translationAnimator;

    private boolean scrollable = false;

    private int selectedPosition = DEFAULT_SELECTED_POSITION;

    private int firstSelectedPosition = 0;

    @Mode
    private int mode = MODE_DEFAULT;

    @BgStyle
    private int bgStyle = BACKGROUND_STYLE_DEFAULT;

    /*item list*/
    List<Item> itemList = new ArrayList();

    /*tab list*/
    List<Tab> tabList = new ArrayList();

    /*tab 选择监听器*/
    private TabSelectedListener tabSelectedListener;

    /*激活颜色*/
    private int activeColor;

    /*激活后的颜色*/
    private int inActiveColor;

    /*背景颜色*/
    private int bgColor;

    private boolean autoHideEnabled;

    private float elevation;

    /*背景容器*/
    private FrameLayout bgOverlay;


    private FrameLayout container;

    /*tab容器*/
    private LinearLayout tabContainer;

    /*定义类似枚举的注解,用于tab切换model模式*/
    @IntDef({MODE_DEFAULT, MODE_FIXED, MODE_SHIFTING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    /*定义类似枚举的注解,背景样式定义*/
    @IntDef({BACKGROUND_STYLE_DEFAULT, BACKGROUND_STYLE_STATIC, BACKGROUND_STYLE_RIPPLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BgStyle {
    }

    public BottomLayout(Context context) {
        this(context, null);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttrs(context, attrs);
        init();
    }

    /**
     * 设置动画持续时间,并且设置脉动动画时间
     *
     * @param animationDuration
     * @return BottomLayout
     */
    public BottomLayout setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
        this.rippleAnimationDuration = (int) (animationDuration * 2.5);
        return this;
    }

    /**
     * 初始化组件
     */
    private void init() {

        setLayoutParams(new ViewGroup.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View parentView = inflater.inflate(R.layout.navigation_bottom_bar_container, this, true);

        bgOverlay = (FrameLayout) parentView.findViewById(R.id.bottom_bar_overLay);

        container = (FrameLayout) parentView.findViewById(R.id.bottom_bar_container);

        tabContainer = (LinearLayout) parentView.findViewById(R.id.bottom_bar_item_container);

        //样式版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }

        ViewCompat.setElevation(this, elevation);

        setClipToPadding(false);
    }

    /**
     * 解析自定义样式参数
     *
     * @param context
     * @param attrs
     */
    private void parseAttrs(Context context, AttributeSet attrs) {
        //解析配置文件配置参数
        if (attrs != null) {

            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.navigation_bottom, 0, 0);

            activeColor = typedArray.getColor(R.styleable.navigation_bottom_active_color, Tools.fetchContextColor(context, R.attr.colorAccent));

            inActiveColor = typedArray.getColor(R.styleable.navigation_bottom_in_active_color, Color.LTGRAY);

            bgColor = typedArray.getColor(R.styleable.navigation_bottom_bg_color, Color.WHITE);

            autoHideEnabled = typedArray.getBoolean(R.styleable.navigation_bottom_auto_hide_enabled, true);

            elevation = typedArray.getDimension(R.styleable.navigation_bottom_elevation, getResources().getDimension(R.dimen.bottom_navigation_elevation));
            //设置动画持续时间
            setAnimationDuration(typedArray.getInt(R.styleable.navigation_bottom_animation_duration, DEFAULT_ANIMATION_DURATION));

            //设置tab动画模式
            switch (typedArray.getInt(R.styleable.navigation_bottom_mode, MODE_DEFAULT)) {

                case MODE_FIXED:
                    mode = MODE_FIXED;
                    break;

                case MODE_SHIFTING:
                    mode = MODE_SHIFTING;
                    break;

                case MODE_DEFAULT:
                default:
                    mode = MODE_DEFAULT;
                    break;
            }

            switch (typedArray.getInt(R.styleable.navigation_bottom_bg_style, BACKGROUND_STYLE_DEFAULT)) {
                case BACKGROUND_STYLE_STATIC:
                    bgStyle = BACKGROUND_STYLE_STATIC;
                    break;

                case BACKGROUND_STYLE_RIPPLE:
                    bgStyle = BACKGROUND_STYLE_RIPPLE;
                    break;

                case BACKGROUND_STYLE_DEFAULT:
                default:
                    bgStyle = BACKGROUND_STYLE_DEFAULT;
                    break;
            }

        } else {
            //配置默认的参数
            activeColor = Tools.fetchContextColor(context, R.attr.colorAccent);
            inActiveColor = Color.LTGRAY;
            bgColor = Color.WHITE;
            elevation = getResources().getDimension(R.dimen.bottom_navigation_elevation);
        }
    }

    public BottomLayout addItem(Item item) {
        itemList.add(item);
        return this;
    }

    public BottomLayout removeItem(Item item) {
        itemList.remove(item);
        return this;
    }

    public BottomLayout setMode(@Mode int mode) {
        this.mode = mode;
        return this;
    }

    public BottomLayout setBgStyle(@BgStyle int bgStyle) {
        this.bgStyle = bgStyle;
        return this;
    }

    public BottomLayout setActiveColor(@ColorRes int activeColor) {
        this.activeColor = ContextCompat.getColor(getContext(), activeColor);
        return this;
    }

    public BottomLayout setActiveColor(String activeColorCode) {
        this.activeColor = Color.parseColor(activeColorCode);
        return this;
    }

    public BottomLayout setInActiveColor(@ColorRes int inActiveColor) {
        this.inActiveColor = ContextCompat.getColor(getContext(), inActiveColor);
        return this;
    }

    public BottomLayout setInActiveColor(String inActiveColorCode) {
        this.inActiveColor = Color.parseColor(inActiveColorCode);
        return this;
    }

    public BottomLayout setBarBackgroundColor(@ColorRes int backgroundColor) {
        this.bgColor = ContextCompat.getColor(getContext(), backgroundColor);
        return this;
    }


    public BottomLayout setBarBackgroundColor(String backgroundColorCode) {
        this.bgColor = Color.parseColor(backgroundColorCode);
        return this;
    }

    public BottomLayout setFirstSelectedPosition(int firstSelectedPosition) {
        this.firstSelectedPosition = firstSelectedPosition;
        return this;
    }

    private BottomLayout setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
        return this;
    }

    /**
     * 重绘方法,自定义属性配置完成后,最后调用的方式,重绘界面,
     * 用于界面添加完tab item 重绘
     */
    public void redraws() {

        this.selectedPosition = DEFAULT_SELECTED_POSITION;

        tabList.clear();

        if ((!itemList.isEmpty())) {

            tabContainer.removeAllViews();

            if ((mode == MODE_DEFAULT)) {

                if (itemList.size() <= MIN_SIZE) {
                    mode = MODE_FIXED;
                } else {
                    mode = MODE_SHIFTING;
                }

            }

            if (bgStyle == BACKGROUND_STYLE_DEFAULT) {

                if (mode == MODE_FIXED) {
                    bgStyle = BACKGROUND_STYLE_STATIC;
                } else {
                    bgStyle = BACKGROUND_STYLE_RIPPLE;
                }
            }

            if (bgStyle == BACKGROUND_STYLE_STATIC) {
                bgOverlay.setVisibility(View.GONE);
                container.setBackgroundColor(bgColor);
            }

            int screenWidth = Tools.getScreenWidth(getContext());

            if (mode == MODE_FIXED) {

            } else if (mode == MODE_SHIFTING) {

            }

        }


    }

    /**
     * tab 选择接口
     */
    public interface OnTabSelectedListener {

        void onTabSelected(int position);

        void onTabUnselected(int position);

        void onTabReselected(int position);
    }

    public interface TabSelectedListener {

        void onTabSelected(int position);

        void onTabUnselected(int position);

        void onTabReselected(int position);
    }
}
