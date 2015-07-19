package org.rui.thread.newc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		
		/**
		 * 建并执行在给定延迟后启用的一次性操作。
		 */
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * 重复
	 * @param envent
	 * @param initialDelay
	 * @param period 连续执行之间的周期    时间越少 执行的越快
	 */
	public void repeat(Runnable envent, long initialDelay, long period)
	{
		
		/**
		 *  创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay
		 *   后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
		 */
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
			//System.out.println("Turning on lights");
			System.out.println("打开电灯");

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
		// physically turn off the light. 身关灯。
		@Override
		public void run()
		{
			System.out.println("旋转 关灯 ");
			//			System.out.println("Turning off  light");

			water = true;
		}
	}

	class WaterOn implements Runnable
	{

		@Override
		public void run()
		{
			//System.out.println("Turning greenhouse water on");
			System.out.println("温室水开");

			water = true;

		}

	}

	class WaterOff implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("温室水关");

			//System.out.println("Turning greenhouse water off");
			water = false;

		}

	}

	/**
	 * 控温器  夜晚
	 * @author lenovo
	 *
	 */
	class ThermostatNight implements Runnable
	{

		@Override
		public void run()
		{
			// put hardware control code here 把硬件控制代码在这里
			//System.out.println("thermostat to night setting");
			System.out.println("自动控温器  夜晚设置");
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
			System.out.println("温室白天 设置");
			//			System.out.println("thermostat to day setting");

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
			System.out.println("Bing!响铃>>");
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
			System.out.println("Terminate》》结束");
			scheduler.shutdown();
			// must start a separate task to do this job 必须启动一个单独的任务来做这份工作
			// since the scheduler has been shut down 自调度器已经关闭
			new Thread()
			{
				public void run()
				{
					for (DataPoint d : data)
					{
						System.out.println("DataPoint:"+d);
					}
				};
			}.start();

		}
	}

	
	
	/**
	 * 可以持有并显示单个的数据段
	 * @author lenovo
	 *
	 */
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
			DateFormat fd=new SimpleDateFormat("yyyy/MM/dd hh:mm ss");
			return fd.format(time.getTime())
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

	private float lastTemp = 65.0f;//
	private int tempDirection = +1;//温度 方位
	private float lastHumidity = 50.0f;//最后的  湿度
	private int humidityDirection = +1;//湿气 方位
	private Random rand = new Random(47);
	List<DataPoint> data = Collections
			.synchronizedList(new ArrayList<DataPoint>());

	
	//被调度的任务，它在每次运行时，都可以产生仿真数据，并将其添加到Greenhouse的list<DataPoint>中
	// ineer class
	class CollectData implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("CollectData》》》run");
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
		
		//延迟多少时间  关闭
		gh.scheduler(gh.new Terminate(), 5000);
		
		// former restart class not necessary:前重启类没有必要:
		gh.repeat(gh.new Bell(), 0, 1000);//响铃
		gh.repeat(gh.new ThermostatNight(), 0, 2000);//夜晚  2秒执行
		
		gh.repeat(gh.new LightOn(), 0, 200);//灯
		gh.repeat(gh.new LightOff(), 0, 400);

		gh.repeat(gh.new WaterOn(), 0, 600);//水
		gh.repeat(gh.new WaterOff(), 0, 800);
//
	    gh.repeat(gh.new ThernostatDay(), 0, 1400);//白天
		gh.repeat(gh.new CollectData(), 500, 500);

	}
}
/***
 * output:
 * Bing!响铃>>
自动控温器  夜晚设置
打开电灯
旋转 关灯 
温室水开
温室水关
温室白天 设置
打开电灯
打开电灯
旋转 关灯 
CollectData》》》run
温室水开
打开电灯
打开电灯
旋转 关灯 
温室水关
Bing!响铃>>
打开电灯
CollectData》》》run
打开电灯
温室水开
旋转 关灯 
打开电灯
温室白天 设置
CollectData》》》run
打开电灯
温室水关
旋转 关灯 
打开电灯
温室水开
Bing!响铃>>
CollectData》》》run
旋转 关灯 
打开电灯
自动控温器  夜晚设置
打开电灯
打开电灯
旋转 关灯 
温室水开
温室水关
CollectData》》》run
打开电灯
打开电灯
旋转 关灯 
温室白天 设置
打开电灯
CollectData》》》run
温室水开
Bing!响铃>>
旋转 关灯 
温室水关
打开电灯
打开电灯
CollectData》》》run
旋转 关灯 
温室水开
打开电灯
打开电灯
Bing!响铃>>
自动控温器  夜晚设置
旋转 关灯 
温室水关
CollectData》》》run
打开电灯
打开电灯
温室水开
温室白天 设置
打开电灯
旋转 关灯 
CollectData》》》run
打开电灯
打开电灯
温室水关
温室水开
旋转 关灯 
Bing!响铃>>
打开电灯
CollectData》》》run
Terminate》》结束
DataPoint:2015/07/19 09:00 00temperature:66.4 humidity:50.05
DataPoint:2015/07/19 09:30 00temperature:68.0 humidity:50.47
DataPoint:2015/07/19 10:00 00temperature:69.7 humidity:51.42
DataPoint:2015/07/19 10:30 00temperature:70.8 humidity:50.87
DataPoint:2015/07/19 11:00 00temperature:72.0 humidity:50.32
DataPoint:2015/07/19 11:30 00temperature:73.2 humidity:49.92
DataPoint:2015/07/20 12:00 00temperature:71.9 humidity:49.81
DataPoint:2015/07/20 12:30 00temperature:70.1 humidity:50.25
DataPoint:2015/07/20 01:00 00temperature:68.9 humidity:51.00
DataPoint:2015/07/20 01:30 00temperature:67.7 humidity:50.21

 */

