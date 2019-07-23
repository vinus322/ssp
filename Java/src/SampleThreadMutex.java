package thread;

import java.util.concurrent.locks.ReentrantLock;

public class SampleThreadMutex {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// 스레드 생성
		ThreadMutexClass thread1 = new ThreadMutexClass("[Thread1]");
		ThreadMutexClass thread2 = new ThreadMutexClass("[Thread2]");
		thread1.start();
		thread2.start();
		
		ThreadMutexClass.lock.lock();
		System.out.println("[Main]");
		for(int i=0; i<10; i++) {
			System.out.print(i+" ");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
		ThreadMutexClass.lock.unlock();
		
		// 스레드가 끝날 때까지 기다림
		thread1.join();
		thread2.join();
	}

}

class ThreadMutexClass extends Thread{
	
	static ReentrantLock lock = new ReentrantLock();
	
	String threadName;
	public ThreadMutexClass(String name) {
		this.threadName = name;
	}
	
	public void run() {
		lock.lock();
		PrintNums(threadName);
		lock.unlock();
	}
	
	static void PrintNums(String str) {
		System.out.println(str);
		for(int i = 0; i < 10; i++) {
			System.out.print(i+ " ");
		}
		System.out.println();
	}
}
