package Helpers;

import java.util.Timer;
import java.util.TimerTask;

public class PausableTimer {
    private Timer timer;
    private long delay;
    private boolean isPaused;
    private boolean isRunning;
    private int executions;
    private int currentExecution;
    private Runnable onFinishTask;
    private Runnable onExecution;

    public PausableTimer(long delay, int executions, Runnable onFinishTask, Runnable onExecution) {
        this.delay = delay;
        this.timer = new Timer();
        this.isPaused = false;
        this.isRunning = false;
        this.executions = executions;
        this.currentExecution = 0;
        this.onFinishTask = onFinishTask;
        this.onExecution = onExecution;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!isPaused) {
                        // Do whatever you want the timer to do
                        System.out.println("Timer task executed.");
                        onExecution.run();
                        currentExecution++;
                        if (currentExecution == executions) {
                            stop();
                        }
                    }
                }
            }, 0, delay);
        }
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void stop() {
        timer.cancel();
        isRunning = false;
        if (onFinishTask != null) {
            onFinishTask.run();
        }
    }

    public void forceStop() {
        timer.cancel();
        isRunning = false;
    }

    public long getTimeRemaining() {
        if (!isRunning) {
            return 0; // Timer is not running
        }
        long currentTime = System.currentTimeMillis();
        long nextExecutionTime = currentTime + (executions - currentExecution - 1) * delay;
        return nextExecutionTime - currentTime;
    }

    // public static void main(String[] args) {
    //     Runnable onFinishTask = () -> System.out.println("Timer finished.");
    //     PausableTimer pausableTimer = new PausableTimer(1000, 5, onFinishTask); // Timer with 1 second delay, 5 executions
    //     pausableTimer.start();
    // }
}
