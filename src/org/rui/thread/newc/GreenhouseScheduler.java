package org.rui.thread.newc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  温室 控制器
 * @author lenovo
 *
 */
public class GreenhouseScheduler
{
	private volatile boolean light = false;// 光
	private volatile boolean water = false;// 水
	private String thermostat = "Day";// 自动调温器

	public synchronized String getThermostat()
	{
		return thermostat;
	}

	public synchronized void setThermostat(String thermostat)
	{
		this.thermostat = thermostat;
	}

	// 调度程序
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

	/**
	 * 
	 * @param event  
	 * @param delay 延迟
	 */
	public void scheduler(Runnable event, long delay)
	{
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * 重复
	 * @param envent
	 * @param initialDelay
	 * @param period 周期
	 */
	public void repeat(Runnable envent, long initialDelay, long period)
	{
		scheduler.scheduleAtFixedRate(envent, initialDelay, period,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * inner class 
	 * 打开 灯
	 */
	class LightOn implements Runnable
	{

		// put hardware control code here to把硬件控制代码在这里
		// physically turn on the light. 身体开灯。
		@Override
		public void run()
		{
			// 供货上的灯
			System.out.println("Rurning on lights");
			light = true;

		}

	}

	/**
	 * 关
	 * @author lenovo
	 *
	 */
	class LightOff implements Runnable
	{

		// put hardware control code here to 把硬件控制代码在这里
		// physically turn off the light. 身体 关灯。
		@Override
		public void run()
		{
			System.out.println("turning greenhouse water on");
			water = true;
		}
	}

	class WaterOn implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Turning greenhouse water on");
			water = true;

		}

	}

	class WaterOff implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Turning greenhouse water off");
			water = false;

		}

	}

	/**
	 * 控温器  夜晚
	 * @author lenovo
	 *
	 */
	class ThernostatNight implements Runnable
	{

		@Override
		public void run()
		{
			// put hardware control code here
			System.out.println("thermostat to night setting");
			setThermostat("Night");

		}
	}

	/**
	 * 白天
	 * @author lenovo
	 *
	 */
	class ThernostatDay implements Runnable
	{

		@Override
		public void run()
		{
			// put hardware control code here
			System.out.println("thermostat to day setting");
			setThermostat("Day");

		}
	}

	/**
	 * 钟
	 * @author lenovo
	 *
	 */
	class Bell implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Bing!");
		}

	}

	/**
	 * 终止
	 * @author lenovo
	 *
	 */
	class Terminate implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Terminate");
			scheduler.shutdown();
			// must start a separate task to do this job 必须启动一个单独的任务来做这份工作
			// since the scheduler has been shut down 自调度器已经关闭
			new Thread()
			{
				public void run()
				{
					for (DataPoint d : data)
					{
						System.out.println(d);
					}
				};
			}.start();

		}
	}

	// inner class
	static class DataPoint
	{
		final Calendar time;
		final float temperature;
		final float humidity;

		/**
		 * @param time
		 * @param temperature
		 * @param humidity
		 */
		public DataPoint(Calendar time, float temperature, float humidity)
		{

			this.time = time;
			this.temperature = temperature;
			this.humidity = humidity;
		}

		public String toString()
		{
			return time.getTime()
					+ String.format("temperature:%1$.1f humidity:%2$.2f",
							temperature, humidity);
		}

	}

	// //
	private Calendar lastTime = Calendar.getInstance();
	{
		// adjust data to the half hour 调整数据到半个小时
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}

	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection = +1;
	private Random rand = new Random(47);
	List<DataPoint> data = Collections
			.synchronizedList(new ArrayList<DataPoint>());

	// ineer class
	class CollectData implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("CollectData");
			synchronized (GreenhouseScheduler.this)
			{
				// pretend the interval is longer than it is: 假装间隔时间比是:
				lastTime.set(Calendar.MINUTE,
						lastTime.get(Calendar.MINUTE) + 30);
				// one in 5 chances of reversing the direction:一个在5 扭转方向的机会:
				if (rand.nextInt(5) == 4)
				{
					tempDirection = -tempDirection;// 方向
				}
				// store previous value: 店前一个值:
				lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
				if (rand.nextInt(5) == 4)
				{
					humidityDirection = -humidityDirection;

				}
				lastHumidity = lastHumidity + humidityDirection
						* rand.nextFloat();
				// calendar must be cloned , otherwise all
				// dataPoints hold references to the same lastTime.
				// for a basic object like calendar,clone() is ok.
				data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp,
						lastHumidity));

			}

		}

	}

	// //////////////main
	public static void main(String[] args)
	{
		GreenhouseScheduler gh = new GreenhouseScheduler();
		gh.scheduler(gh.new Terminate(), 5000);
		// former restart class not necessary:前重启类没有必要:
		gh.repeat(gh.new Bell(), 0, 1000);
		gh.repeat(gh.new ThernostatNight(), 0, 2000);
		gh.repeat(gh.new LightOn(), 0, 200);
		gh.repeat(gh.new LightOff(), 0, 400);

		gh.repeat(gh.new WaterOn(), 0, 600);

		gh.repeat(gh.new WaterOff(), 0, 800);

		gh.repeat(gh.new ThernostatDay(), 0, 1400);
		gh.repeat(gh.new CollectData(), 500, 500);

	}
}


