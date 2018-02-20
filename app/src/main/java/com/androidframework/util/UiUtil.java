package com.androidframework.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidframework.App;


/**
 * Created by yendang on 13/02/2017.
 */
public class UiUtil {

    public static int getPixelFromDp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, App.getContext().getResources().getDisplayMetrics());
    }

    public static int getPixelFromSp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, App.getContext().getResources().getDisplayMetrics());
    }


    public static Point getDisplayPoint() {
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static int getDisplayWidth() {
        return getDisplayPoint().x;
    }

    public static int getDisplayHeight() {
        return getDisplayPoint().y;
    }

    public static int getNumberColumnGrid(int paddingInDp, int itemSizeInDp) {
        int width = UiUtil.getDisplayWidth();
        int padding = UiUtil.getPixelFromDp(paddingInDp);
        int itemSize = UiUtil.getPixelFromDp(itemSizeInDp);
        return (int) Math.floor((width - padding) / itemSize);
    }

    public static GradientDrawable getRectangleBorder(int color, int radius) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(UiUtil.getPixelFromDp(1), color);
        drawable.setColor(Color.TRANSPARENT);
        drawable.setCornerRadius(UiUtil.getPixelFromDp(radius));

        return drawable;

    }

    public static GradientDrawable getRectangleBackground(int color) {
        return getRectangleBackground(color, 2);
    }


    public static GradientDrawable getRectangleBackground(int color, int round) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);
        drawable.setCornerRadius(UiUtil.getPixelFromDp(round));

        return drawable;

    }


    public static GradientDrawable getRectangleBackground(int color, float round) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);

        float[] radius = new float[]{0, 0, 0, 0, round, round, round, round};

        drawable.setCornerRadii(radius);

        return drawable;

    }

    public static GradientDrawable getCircleBackground(int color) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color);
        return drawable;

    }

    public static int getWidthTextView(TextView textView, String stringContent) {

        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(stringContent, 0, stringContent.length(), bounds);

        return bounds.width();
    }

    public static int getHeightTextView(TextView textView) {

        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(textView.getText().toString(), 0, textView.getText().toString().length(), bounds);

        return bounds.height();
    }


    public static void closeKeyboard(AppCompatActivity activity) {
        if (activity == null) {
            return;
        }

        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void disableTabTrip(TabLayout tabLayout, boolean isEnable) {
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        tabStrip.setEnabled(isEnable);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(isEnable);
        }
    }

    public static Bitmap getBitmapFromView(View view) {

        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap

        return returnedBitmap;

    }

    public static Drawable setDrawableSelector(Context context, int normal, int selected) {


        Drawable state_normal = ContextCompat.getDrawable(context, normal);

        Drawable state_pressed = ContextCompat.getDrawable(context, selected);


        Bitmap state_normal_bitmap = ((BitmapDrawable) state_normal).getBitmap();

        // Setting alpha directly just didn't work, so we draw a new bitmap!
        Bitmap disabledBitmap = Bitmap.createBitmap(
                state_normal.getIntrinsicWidth(),
                state_normal.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(disabledBitmap);

        Paint paint = new Paint();
        //paint.setColor(126);
        canvas.drawBitmap(state_normal_bitmap, 0, 0, paint);

        BitmapDrawable state_normal_drawable = new BitmapDrawable(context.getResources(), disabledBitmap);


        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_selected},
                state_pressed);
        drawable.addState(new int[]{android.R.attr.state_enabled},
                state_normal_drawable);

        return drawable;
    }


}
