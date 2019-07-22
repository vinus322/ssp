import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 * @author KANG LEE
 */
public class ScheduleMethods {
  SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
  static int count;
  /*
   * Repeat event by using TimerTask
   */
  public void repeatTimer(int init, int interval) {
    count = 0;
    
    System.out.println("initial delay(ms): "+init+", interval(ms): "+interval);
    
    long now = System.currentTimeMillis();
    System.out.println("Time: "+dayTime.format(new Date(now)));
    
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      
      @Override
      public void run() {
        if(count<5) {
          count++;
          System.out.println("Count: "+count);
          long time = System.currentTimeMillis();
          System.out.println("Time: "+dayTime.format(new Date(time)));
        } else {
          timer.cancel();
        }
      }
    };
    timer.schedule(task,  init, interval);
  }
  /*
   * Repeat event by using Thread
   */
  public void repeatThread(int init, int interval) {
    System.out.println("initial delay(ms): "+init+", interval(ms): "+interval);
    
    long now = System.currentTimeMillis();
    System.out.println("Time: "+dayTime.format(new Date(now)));
    Thread t = new Thread(new Repeat(init, interval));
    t.start();
    synchronized(t) {
      try {
        t.wait();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  private class Repeat implements Runnable {
    private int init;
    private int interval;
    
    private Repeat(int init, int interval) {
      this.init = init;
      this.interval = interval;
    }
    
    @Override
    public void run() {
      try {
        Thread.sleep(init);
        for(int i=1; i<=5; i++) {
          System.out.println("Count: "+i);
          long time = System.currentTimeMillis();
          System.out.println("Time: "+dayTime.format(new Date(time)));
          Thread.sleep(interval);
        }
        notify();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
  }
  
  /*
   * Evoke event at every minute by Timer
   */
  public void repeatEventMinuteTImer() {
    count = 0;
    
    long now = System.currentTimeMillis();
    System.out.println("Time: "+dayTime.format(new Date(now)));
    now = now + 60000 - (now%60000);
    
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      
      @Override
      public void run() {
        if(count<5) {
          count++;
          System.out.println("Count: "+count);
          long time = System.currentTimeMillis();
          System.out.println("Time: "+dayTime.format(new Date(time)));
        } else {
          timer.cancel();
        }
      }
    };
    timer.scheduleAtFixedRate(task, new Date(now), 60000);
  }
}
