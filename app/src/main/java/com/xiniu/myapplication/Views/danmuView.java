package com.xiniu.myapplication.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xiniu.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 创建者：wyz
 * 创建时间：2020/4/13
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class danmuView extends View {

    private float x, y;
    private Paint paint;
    private danmu danmu;
    int[] colors;
    List<danmu> danmus = new ArrayList<>();

    public danmuView(Context context) {
        this(context, null);
    }

    public danmuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public danmuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTextSize(40);
        colors = getResources().getIntArray(R.array.color_array);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (danmu danmuss : danmus) {
            paint.setColor(danmuss.getClolr());
            canvas.drawText(danmuss.getContent(), danmuss.getStartx(), danmuss.getStarty(), paint);
            if (danmuss.getStartx() <= 0) {
                danmuss.setStartx(getMeasuredWidth());
            }
            danmuss.updata();
        }
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.x = MeasureSpec.getSize(widthMeasureSpec);
        this.y = MeasureSpec.getSize(heightMeasureSpec);
        if (danmus.size()==0){
            for (int i = 0; i < colors.length; i++) {
                danmu = new danmu("hello", (float) (Math.random() * 10), colors[i], (float) Math.random() * x, (float) Math.random() * y);
                danmus.add(danmu);
            }
        }
    }

    private class danmu {

        private  String content;
        private float speed;
        private int clolr;
        private  float startx;
        private float starty;

        public danmu() {}

        public void setContent(String content) {
            this.content = content;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getSpeed() {
            return speed;
        }

        public String getContent() {
            return content;
        }

        public void setClolr(int clolr) {
            this.clolr = clolr;
        }

        public int getClolr() {
            return clolr;
        }

        public void setStartx(float startx) {
            this.startx = startx;
        }

        public void setStarty(float starty) {
            this.starty = starty;
        }

        public float getStartx() {
            return startx;
        }

        public float getStarty() {
            return starty;
        }

        public void updata(){
            startx= startx- speed;
        }

        public danmu(String content, float speed,int color,float startx,float starty) {
            this.content = content;
            this.speed = speed;
            this.clolr = color;
            this.startx = startx;
            this.starty = starty;
        }
    }

}
