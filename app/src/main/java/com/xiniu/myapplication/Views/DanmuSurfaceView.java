package com.xiniu.myapplication.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiniu.myapplication.R;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 创建者：wyz
 * 创建时间：2020/4/14
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class DanmuSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Paint paint;
    private List<Danmu> danmus = new CopyOnWriteArrayList<>();
    private int maxShowText;
    private float width, height;
    private float textSize;
    private int[] colors;
    private Random random;
    private float maxSpeed = 10;
    private Canvas canvas;
    private Thread thread;
    private boolean threadRun = true;
    private LayoutListener layoutListener;
    private float textHeight;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("touchevent", x + "" + y);

        return true;

    }

    public DanmuSurfaceView(Context context) {
        this(context, null);
    }

    public DanmuSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanmuSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DanmuSurfaceView);
        textSize = typedArray.getDimension(R.styleable.DanmuSurfaceView_text_size, 40);
        maxShowText = typedArray.getInteger(R.styleable.DanmuSurfaceView_max_text_num, 5);
        typedArray.recycle();
        init();

    }

    public void addLayoutListener(LayoutListener listener) {
        this.layoutListener = listener;
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        holder = this.getHolder();
        holder.addCallback(this);
        colors = getResources().getIntArray(R.array.color_array);
        random = new Random();
    }

    //    //todo 还没有测量就开始添加数据了。添加数据必须在数据结束后再进行添加。
    public void addText(String text) {
        int color = colors[random.nextInt(colors.length)];
        Danmu danmu = new Danmu(text, (float) (Math.random() * maxSpeed), color, width, paint.measureText(text));
        if (danmus.size() < maxShowText) {
            danmus.add(danmu);
        } else {
            danmus.remove(0);
            danmus.add(danmu);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("surfaceCreated", "des");
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        if (layoutListener!=null){
            layoutListener.onCreateView();
        }
        threadRun = true;
        textHeight = height / (maxShowText + 2);
        draw();

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        threadRun = false;
        danmus.clear();

        Log.e("surfaceDestroyed", "des");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("onlayout", "hello");

    }

    //该方法要循环起来，才可以。
    public void draw() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (threadRun) {
            canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            for (int i = 0; i < danmus.size(); i++) {
                Danmu danmuss = danmus.get(i);
                paint.setColor(danmuss.getClolr());
                canvas.drawText(danmuss.getContent(), danmuss.getStartx(), textHeight * (i + 1), paint);
                danmuss.updata();

            }

            getHolder().unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    //数据参数
    private class Danmu {

        private String content;
        private float speed;
        private int clolr;
        private float startx;
        private float textLength;

        public Danmu() {
        }

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



        public float getStartx() {
            return startx;
        }



        public void setTextLength(float textLength) {
            this.textLength = textLength;
        }

        public float getTextLength() {
            return textLength;
        }

        //优化这里的计算顺序
        public void updata() {
            if (startx + textLength <= 0) {
                startx = getMeasuredWidth();
            }
            startx = startx - speed;
        }

        public Danmu(String content, float speed, int color, float startx, float textLength) {
            this.content = content;
            this.speed = speed;
            this.clolr = color;
            this.startx = startx;
            this.textLength = textLength;
        }
    }


    //增加一接口回掉，当测量完成后再进行获取数据。
    public interface LayoutListener {
        void onCreateView();
    }

}
