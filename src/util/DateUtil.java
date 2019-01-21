package util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String formatDate(Date date,String format){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
		if(date!=null){
			return simpleDateFormat.format(date);
		}
		return null;
	}

	public static Date formatString(String str,String format){
		if(StringUtil.isEmpty(str)){
			return null;
		}
		else{
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
			try {
				return simpleDateFormat.parse(str);
			}
			catch (Exception ex){
				ex.printStackTrace();
				return null;
			}
		}
	}
	public static Date addMonth(Date date){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, 1);
		Date reDate = rightNow.getTime();
		return reDate;
	}

	public static Date subDay(Date date){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DAY_OF_YEAR, -1);
		Date reDate = rightNow.getTime();
		return reDate;
	}
	public static Date addDay(Date date){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DAY_OF_YEAR, 1);
		Date reDate = rightNow.getTime();
		return reDate;
	}

	public static Date addDay(Date date,int add){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DAY_OF_YEAR, add);
		Date reDate = rightNow.getTime();
		return reDate;
	}

	public static int getYear(Date date){
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		int year=calendar.get(Calendar.YEAR);

		return year;
	}

	public static int getMonth(Date date){
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		int month=calendar.get(Calendar.MONTH);

		return month+1;//比实际少一月，加一
	}

	public static int getDay(Date date){
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		int day=calendar.get(Calendar.DATE);

		return day;
	}

	/**
	 * 日期的星期数
	 * @param date
	 * @return
	 */
	public static int getDateIntOfWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;

		if(intWeek==0) {//周日返回7（原本是0）
			return 7;
		}
		return intWeek;
	}

	/**
	 * 判断是否是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if((year%4==0&&year%100!=0)||(year%400==0))
			return true;
		return false;
	}

	/**
	 * 两个日期相隔几天
	 * @param date2
	 * @param date1
	 * @return
	 */
	public static int dayDiff(Date date2, Date date1) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		if(days<0){
			return -1*days;
		}
		return days;
	}
}