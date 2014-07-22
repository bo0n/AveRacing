package it.bast.averacing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class CarSprite {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private int x = 0; 
    private int y = 0; 
    private int xSpeed = 5;
    private GameView gameView;
    private Bitmap bmp;
    private int width;
    private int height;
   
    public CarSprite(GameView gameView, Bitmap bmp) {
          this.gameView=gameView;
          this.bmp=bmp;
          this.width = bmp.getWidth() / BMP_COLUMNS;
          this.height = bmp.getHeight() / BMP_ROWS;
    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed) {
               xSpeed = -5;
        }
        if (x + xSpeed < 0) {
               xSpeed = 5;
        }
        x = x + xSpeed;
    }
   
    public void onDraw(Canvas canvas) {
          update();
          int srcX = 1 * width;
          int srcY;
          if (xSpeed > 0) {
        	  srcY = 2 * height;
          } else {
        	  
        	  srcY = 1 * height;
          }
          Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
          Rect dst = new Rect(x, y, x + width, y + height);
          canvas.drawBitmap(bmp, src, dst, null);
    }
}
