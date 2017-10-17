package app.game.com.mygame;

public class GameThread extends Thread {
    static final long FPS = 30;
    private GameView view;
    private boolean running = false;

    public GameThread(GameView view) {
        this.view = view;
    }
    public void setRunning(boolean run) {
        running = run;
    }
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            startTime = System.currentTimeMillis();
            view.postInvalidate();
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
            } catch (Exception e) {}
        }
    }
}