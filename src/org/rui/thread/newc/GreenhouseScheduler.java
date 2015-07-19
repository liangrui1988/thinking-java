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
 *  ���� ������
 * @author lenovo
 *
 */
public class GreenhouseScheduler
{
	private volatile boolean light = false;// ��
	private volatile boolean water = false;// ˮ
	private String thermostat = "Day";// �Զ�������

	public synchronized String getThermostat()
	{
		return thermostat;
	}

	public synchronized void setThermostat(String thermostat)
	{
		this.thermostat = thermostat;
	}

	// ���ȳ���
	ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

	/**
	 * 
	 * @param event  
	 * @param delay �ӳ�
	 */
	public void scheduler(Runnable event, long delay)
	{
		
		/**
		 * ����ִ���ڸ����ӳٺ����õ�һ���Բ�����
		 */
		scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * �ظ�
	 * @param envent
	 * @param initialDelay
	 * @param period ����ִ��֮�������    ʱ��Խ�� ִ�е�Խ��
	 */
	public void repeat(Runnable envent, long initialDelay, long period)
	{
		
		/**
		 *  ������ִ��һ���ڸ�����ʼ�ӳٺ��״����õĶ��ڲ����������������и��������ڣ�Ҳ���ǽ��� initialDelay
		 *   ��ʼִ�У�Ȼ���� initialDelay+period ��ִ�У������� initialDelay + 2 * period ��ִ�У��������ơ�
		 */
		scheduler.scheduleAtFixedRate(envent, initialDelay, period,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * inner class 
	 * �� ��
	 */
	class LightOn implements Runnable
	{

		// put hardware control code here to��Ӳ�����ƴ���������
		// physically turn on the light. ���忪�ơ�
		@Override
		public void run()
		{
			//System.out.println("Turning on lights");
			System.out.println("�򿪵��");

			light = true;

		}

	}

	/**
	 * ��
	 * @author lenovo
	 *
	 */
	class LightOff implements Runnable
	{

		// put hardware control code here to ��Ӳ�����ƴ���������
		// physically turn off the light. ��صơ�
		@Override
		public void run()
		{
			System.out.println("��ת �ص� ");
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
			System.out.println("����ˮ��");

			water = true;

		}

	}

	class WaterOff implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("����ˮ��");

			//System.out.println("Turning greenhouse water off");
			water = false;

		}

	}

	/**
	 * ������  ҹ��
	 * @author lenovo
	 *
	 */
	class ThermostatNight implements Runnable
	{

		@Override
		public void run()
		{
			// put hardware control code here ��Ӳ�����ƴ���������
			//System.out.println("thermostat to night setting");
			System.out.println("�Զ�������  ҹ������");
			setThermostat("Night");

		}
	}

	/**
	 * ����
	 * @author lenovo
	 *
	 */
	class ThernostatDay implements Runnable
	{

		@Override
		public void run()
		{
			// put hardware control code here
			System.out.println("���Ұ��� ����");
			//			System.out.println("thermostat to day setting");

			setThermostat("Day");

		}
	}

	/**
	 * ��
	 * @author lenovo
	 *
	 */
	class Bell implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Bing!����>>");
		}

	}

	/**
	 * ��ֹ
	 * @author lenovo
	 *
	 */
	class Terminate implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("Terminate��������");
			scheduler.shutdown();
			// must start a separate task to do this job ��������һ������������������ݹ���
			// since the scheduler has been shut down �Ե������Ѿ��ر�
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
	 * ���Գ��в���ʾ���������ݶ�
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
		// adjust data to the half hour �������ݵ����Сʱ
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}

	private float lastTemp = 65.0f;//
	private int tempDirection = +1;//�¶� ��λ
	private float lastHumidity = 50.0f;//����  ʪ��
	private int humidityDirection = +1;//ʪ�� ��λ
	private Random rand = new Random(47);
	List<DataPoint> data = Collections
			.synchronizedList(new ArrayList<DataPoint>());

	
	//�����ȵ���������ÿ������ʱ�������Բ����������ݣ���������ӵ�Greenhouse��list<DataPoint>��
	// ineer class
	class CollectData implements Runnable
	{

		@Override
		public void run()
		{
			System.out.println("CollectData������run");
			synchronized (GreenhouseScheduler.this)
			{
				// pretend the interval is longer than it is: ��װ���ʱ�����:
				lastTime.set(Calendar.MINUTE,
						lastTime.get(Calendar.MINUTE) + 30);
				// one in 5 chances of reversing the direction:һ����5 Ťת����Ļ���:
				if (rand.nextInt(5) == 4)
				{
					tempDirection = -tempDirection;// ����
				}
				// store previous value: ��ǰһ��ֵ:
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
		
		//�ӳٶ���ʱ��  �ر�
		gh.scheduler(gh.new Terminate(), 5000);
		
		// former restart class not necessary:ǰ������û�б�Ҫ:
		gh.repeat(gh.new Bell(), 0, 1000);//����
		gh.repeat(gh.new ThermostatNight(), 0, 2000);//ҹ��  2��ִ��
		
		gh.repeat(gh.new LightOn(), 0, 200);//��
		gh.repeat(gh.new LightOff(), 0, 400);

		gh.repeat(gh.new WaterOn(), 0, 600);//ˮ
		gh.repeat(gh.new WaterOff(), 0, 800);
//
	    gh.repeat(gh.new ThernostatDay(), 0, 1400);//����
		gh.repeat(gh.new CollectData(), 500, 500);

	}
}
/***
 * output:
 * Bing!����>>
�Զ�������  ҹ������
�򿪵��
��ת �ص� 
����ˮ��
����ˮ��
���Ұ��� ����
�򿪵��
�򿪵��
��ת �ص� 
CollectData������run
����ˮ��
�򿪵��
�򿪵��
��ת �ص� 
����ˮ��
Bing!����>>
�򿪵��
CollectData������run
�򿪵��
����ˮ��
��ת �ص� 
�򿪵��
���Ұ��� ����
CollectData������run
�򿪵��
����ˮ��
��ת �ص� 
�򿪵��
����ˮ��
Bing!����>>
CollectData������run
��ת �ص� 
�򿪵��
�Զ�������  ҹ������
�򿪵��
�򿪵��
��ת �ص� 
����ˮ��
����ˮ��
CollectData������run
�򿪵��
�򿪵��
��ת �ص� 
���Ұ��� ����
�򿪵��
CollectData������run
����ˮ��
Bing!����>>
��ת �ص� 
����ˮ��
�򿪵��
�򿪵��
CollectData������run
��ת �ص� 
����ˮ��
�򿪵��
�򿪵��
Bing!����>>
�Զ�������  ҹ������
��ת �ص� 
����ˮ��
CollectData������run
�򿪵��
�򿪵��
����ˮ��
���Ұ��� ����
�򿪵��
��ת �ص� 
CollectData������run
�򿪵��
�򿪵��
����ˮ��
����ˮ��
��ת �ص� 
Bing!����>>
�򿪵��
CollectData������run
Terminate��������
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

