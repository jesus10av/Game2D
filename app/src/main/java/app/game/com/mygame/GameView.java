package app.game.com.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

    private Paint paint = new Paint();
    private GameThread gameThread;
    private int playerSize = 40;
    private int x=0,y=0;
    private int xSpeed, ySpeed;
    private int xMax, yMax;
    private int speed=4;

    public GameView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                setWillNotDraw(false);

                xMax = getWidth();
                yMax = getHeight();

                xSpeed = speed;
                ySpeed = speed;

                gameThread = new GameThread(GameView.this);
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        paint.setColor(Color.BLUE);

        if(x+playerSize >= xMax)
            xSpeed = -speed;
        if(x <= 0)
            xSpeed = speed;
        if(y+playerSize >= yMax)
            ySpeed = -speed;
        if(y <= 0)
            ySpeed = speed;

        x = x + xSpeed;
        y = y + ySpeed;

        canvas.drawRect(x, y, x+playerSize, y+playerSize, paint);
    }
}
