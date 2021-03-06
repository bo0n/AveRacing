package it.bast.averacing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private CarSprite sprite;

    public GameView(Context context) {
          super(context);
          gameLoopThread = new GameLoopThread(this);
          holder = getHolder();
          holder.addCallback(new SurfaceHolder.Callback() {

                 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) {
                     boolean retry = true;
                     gameLoopThread.setRunning(false);
                     while (retry) {
                            try {
                                  gameLoopThread.join();
                                  retry = false;
                            } catch (InterruptedException e) {
                            }
                     }
                 }

                 @SuppressLint("WrongCall") @Override
                 public void surfaceCreated(SurfaceHolder holder) {
                     gameLoopThread.setRunning(true);
                     gameLoopThread.start();
                 }

                 @Override
                 public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
                 }
          });
          bmp = BitmapFactory.decodeResource(getResources(), R.drawable.redcar);
          sprite = new CarSprite(this, bmp);
    }

    @SuppressLint("WrongCall") @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    	sprite.onDraw(canvas);
    }
}
