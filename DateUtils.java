package utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateUtils {
	
	public static Date getCurrDateTime(){
		return new Date(System.currentTimeMillis());
	}
	
	public static String getCurrTime(){
		Date date_time = new Date();
		return FormatDate(date_time,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getCurrDate(){
		Date date_time = new Date();
		return FormatDate(date_time,"yyyy-MM-dd");
	}
	
	public static String getYear(Date date){
		return FormatDate(date,"yyyy");
	}
	
	public static String getMonth(Date date){
		return FormatDate(date,"MM");
	}
	
	public static String getDay(Date date){
		return FormatDate(date,"dd");
	}
	
	public static String getHour(Date date){
		return FormatDate(date,"HH");
	}
	
	public static String getminute(Date date){
		return FormatDate(date,"mm");
	}
	
	public static String getSecond(Date date){
		return FormatDate(date,"ss");
	}
	
	
	public String getBeginDate(String granularity,String statisticDate){
		String beginDate = "";
		Date date = stringToDateShort(statisticDate);
		Date beginDateTemp = null;
		if(granularity.equals("1")){
			beginDateTemp = date;
		}
		
		if(granularity.equals("2")){
			beginDateTemp = getWeekBegin(date);
		}
		
		if(granularity.equals("3")){
			beginDateTemp = getPeriodBegin(date);
		}
		
		else if(granularity.equals("4")){
			beginDateTemp = getMonthBegin(date);
		}
		
		else if(granularity.equals("5")){
			beginDateTemp = getSeasonBegin(date);
		}
		
		else if(granularity.equals("6")){
			beginDateTemp = getHalfYearBegin(date);
		}
		
		else if(granularity.equals("7")){
			beginDateTemp = getYearBegin(date);
		}
		
		beginDate = dateToStringShort(beginDateTemp);
		return beginDate;
		
	}
	
	public String getEndDate(String granularity,String statisticDate){
		String beginDate = "";
		Date date = stringToDateShort(statisticDate);
		Date beginDateTemp = null;
		if(granularity.equals("1")){
			beginDateTemp = date;
		}
		
		if(granularity.equals("2")){
			beginDateTemp = getWeekEnd(date);
		}
		
		if(granularity.equals("3")){
			beginDateTemp = getPeriodEnd(date);
		}
		
		else if(granularity.equals("4")){
			beginDateTemp = getMonthEnd(date);
		}
		
		else if(granularity.equals("5")){
			beginDateTemp = getSeasonEnd(date);
		}
		
		else if(granularity.equals("6")){
			beginDateTemp = getHalfYearEnd(date);
		}
		
		else if(granularity.equals("7")){
			beginDateTemp = getYearEnd(date);
		}
		
		beginDate = dateToStringShort(beginDateTemp);
		return beginDate;
	}
	
	
	public String getTimedes(String granularity,String statisticDate){
		String timedes = "";
		Date date = stringToDateShort(statisticDate);
		String year = "";
		String month = "01";
		String day = "01";
		year = getYear(date);
		month = getMonth(date);
		day = getDay(date);
		
		if(granularity.equals("1")){
			timedes = statisticDate;
		}else if(granularity.equals("4")){
			timedes = year + "年" + month + "月";
		}else if(granularity.equals("8")){
			String quarter = month + "-" + day;
			if(quarter.equals("03-31")){
				timedes = year + "年 第1季度";
			}
			else if(quarter.equals("06-30")){
				timedes = year + "年 第2季度";
			}
			else if(quarter.equals("09-30")){
				timedes = year + "年 第3季度";
			}
			else if(quarter.equals("12-31")){
				timedes = year + "年 第4季度";
			}
		}
		else if(granularity.equals("32")){
			timedes = year + "年";
		}
		return timedes;
	}
	
	
	public static String dateToString(Date date){
		if(date == null) return "";
		return FormatDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String dateToStringShort(Date date){
		if(date == null) return "";
		return FormatDate(date,"yyyy-MM-dd");
	}
	
	public static Date stringToDate(String dateString){
		String sf = "yyyy-MM-dd HH:mm:ss";
		Date dt = stringToDate(dateString,sf);
		return dt;
	}
	
	public static Date stringToDateShort(String dateString){
		String sf = "yyyy-MM-dd";
		Date dt = stringToDate(dateString,sf);
		return dt;
	}
	
	public static String stringToString(String dateString){
		return dateString + "23:59:59";
	}
	
	public static String FormatDate(Date date ,String sf){
		if(date == null) return "";
		SimpleDateFormat dateformat = new SimpleDateFormat(sf);
		return dateformat.format(date);
	}
	
	public static Date stringToDate(String dateString,String sf){
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf = new SimpleDateFormat(sf);
		Date dt = sdf.parse(dateString,pos);
		return dt;
	}
	
	public static long diffTwoDate(Date date1, Date date2){
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		return l1 - l2;
	}
	
	public static long diffTwoDateDay(Date date1, Date date2){
		long l1 = date1.getTime();
		long l2 = date2.getTime();
		int diff = Integer.parseInt(((l1-l2)/3600L/24L/1000L)+ "");
		return diff;
	}
	
	public String getDateChangeTime(String currentTime,String type,int iQuantity){
		Date curr = stringToDate(currentTime);
		curr = getDateChangeTime(curr,type,iQuantity);
		return dateToString(curr);
	}
	
	public Date getDateChangeTime(Date currentTime,String type,int iQuantity){
		int year = Integer.parseInt(FormatDate(currentTime,"yyyy"));
		int month = Integer.parseInt(FormatDate(currentTime,"MM"));
		
		month--;
		int day = Integer.parseInt(FormatDate(currentTime,"dd"));
		int hour = Integer.parseInt(FormatDate(currentTime,"HH"));
		int mi = Integer.parseInt(FormatDate(currentTime,"mm"));
		int ss = Integer.parseInt(FormatDate(currentTime,"ss"));
		GregorianCalendar gc = new GregorianCalendar(year,month,day,hour,mi,ss);
		if(type.equalsIgnoreCase("y")){
			gc.add(1, iQuantity);
		}
		else if(type.equalsIgnoreCase("m")){
			gc.add(2, iQuantity);
		}
		else if(type.equalsIgnoreCase("d")){
			gc.add(5, iQuantity);
		}
		else if(type.equalsIgnoreCase("h")){
			gc.add(10, iQuantity);
		}
		else if(type.equalsIgnoreCase("mi")){
			gc.add(12, iQuantity);
		}
		else if(type.equalsIgnoreCase("s")){
			gc.add(13, iQuantity);
		}
		return gc.getTime();
	}
	
	
	public String getDateChangeAll(String currentTime,String type,int iQuantity){
		Date curr = null;
		String newtype = "";
		if(currentTime.length() == 10){
			curr = stringToDateShort(currentTime);
		}
		if(currentTime.length() > 10){
			curr = stringToDate(currentTime);
		}
		
		if(type.equals("1")){
			iQuantity = iQuantity;
			newtype = "d";
		}
		else if(type.equals("2")){
			iQuantity *= 7;
			newtype = "d";
		}
		
		else if(type.equals("3")){
			iQuantity *= 10;
			newtype = "d";
		}
		
		else if(type.equals("4")){
			iQuantity = iQuantity;
			newtype = "m";
		}
		
		else if(type.equals("5")){
			iQuantity *= 3;
			newtype = "m";
		}
		else if(type.equals("6")){
			iQuantity *= 6;
			newtype = "m";
		}
		else if(type.equals("7")){
			newtype = "y";
		}
		else{
			iQuantity = iQuantity;
			newtype = "d";
		}
		
		Date change = getDateChangeTime(curr, newtype, iQuantity);
		return dateToString(change);
	}
	
	
	public static void main(String[] args) {
		
	}
	
	public static String getTime(String year,String month){
		String time = "";
		int len = 31;
		int iYear = Integer.parseInt(year);
		int iMonth = Integer.parseInt(month);
		if((iMonth == 4) || (iMonth == 6) || (iMonth == 9) || (iMonth == 11))
			len = 30;
		if(iMonth == 2){
			len = 28;
			if(((iYear % 4 == 0) && (iYear % 100 == 0) && (iYear % 400 == 0)) || ((iYear % 4 == 0) && (iYear % 100 != 0))){
				len = 29;
			}
		}
		time = year + "-" + month + "-" + String.valueOf(len);
		return time;
		
	}
	
	public static Date getMonthBegin(Date date){
		String newDateStr = FormatDate(date,"yyyy-MM") + "-01";
		return stringToDateShort(newDateStr);
	}
	
	public static Date getMonthEnd(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		int day = Integer.parseInt(FormatDate(date,"dd"));
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,day,0,0,0);
		int monthLength = calendar.getActualMaximum(5);
		String newDateStr = FormatDate(date,"yyyy") + "-" + FormatDate(date,"MM") + "-";
		
		if(monthLength < 10){
			newDateStr = newDateStr + "0" +monthLength;
		}else{
			newDateStr = newDateStr + monthLength;
		}
		
		return stringToDateShort(newDateStr);
	}
	
	
	public Date getSeasonBegin(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if((month >=1) && (month <= 3)){
			newDateStr = newDateStr + "01-01";
		}
		else if((month >=4) && (month <= 6)){
			newDateStr = newDateStr + "04-01";
		}
		else if((month >=7) && (month <= 9)){
			newDateStr = newDateStr + "07-01";
		}
		else if((month >=10) && (month <= 12)){
			newDateStr = newDateStr + "10-01";
		}
		return stringToDateShort(newDateStr);
	}
	
	
	public Date getHalfYearBegin(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if(month <= 6){
			newDateStr = newDateStr + "01-01";
		}
		else{
			newDateStr = newDateStr + "07-01";
		}
		return stringToDateShort(newDateStr);
	}
	
	public Date getPeriodBegin(Date date){
		int days = Integer.parseInt(FormatDate(date,"dd"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if(days <= 10 ){
			newDateStr = newDateStr + "01";
		}else if(days <= 20){
			newDateStr = newDateStr + "11";
		}else{
			newDateStr = newDateStr + "21";
		}
		return stringToDateShort(newDateStr);
	}
	
	
	
	public Date getWeekBegin(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		
		month--;
		int day = Integer.parseInt(FormatDate(date,"dd"));
		GregorianCalendar gc = new GregorianCalendar(year,month,day);
		int week = 6;
		if(week == 0){
			week = 7;
		}
		gc.add(5, 0-week+1);
		return gc.getTime();
	}
	
	public Date getWeekEnd(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		
		month--;
		int day = Integer.parseInt(FormatDate(date,"dd"));
		GregorianCalendar gc = new GregorianCalendar(year,month,day);
		int week = 6;
		if(week == 0){
			week = 7;
		}
		gc.add(5, 7-week);
		return gc.getTime();
	}
	
	public Date getPeriodEnd(Date date){
		int days = Integer.parseInt(FormatDate(date,"dd"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if(days <= 10 ){
			newDateStr = newDateStr + "10";
		}else if(days <= 20){
			newDateStr = newDateStr + "20";
		}else{
			newDateStr = FormatDate(getMonthEnd(date),"yyyy-MM-dd");
		}
		return stringToDateShort(newDateStr);
	}
	
	public Date getHalfYearEnd(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if(month <= 6){
			newDateStr = newDateStr + "06-30";
		}
		else{
			newDateStr = newDateStr + "12-31";
		}
		return stringToDateShort(newDateStr);
	}
	
	
	public Date getSeasonEnd(Date date){
		int year = Integer.parseInt(FormatDate(date,"yyyy"));
		int month = Integer.parseInt(FormatDate(date,"MM"));
		String newDateStr = FormatDate(date,"yyyy") + "-";
		if((month >=1) && (month <= 3)){
			newDateStr = newDateStr + "03-31";
		}
		else if((month >=4) && (month <= 6)){
			newDateStr = newDateStr + "06-30";
		}
		else if((month >=7) && (month <= 9)){
			newDateStr = newDateStr + "09-30";
		}
		else if((month >=10) && (month <= 12)){
			newDateStr = newDateStr + "12-31";
		}
		return stringToDateShort(newDateStr);
	}
	
	
	public static Date getYearBegin(Date date){
		String newDateStr = FormatDate(date,"yyyy") + "-01-01";
		return stringToDateShort(newDateStr);
	}
	
	public static Date getYearEnd(Date date){
		String newDateStr = FormatDate(date,"yyyy") + "-12-31";
		return stringToDateShort(newDateStr);
	}
	
	public boolean IsXperiodEnd(Date date){
		boolean flag = false;
		String day = getDay(date);
		String month = getMonth(date);
		if(day.equalsIgnoreCase("10")){
			flag = true;
		}
		else if(day.equalsIgnoreCase("20")){
			flag = true;
		}
		
		return flag;
	}
	
	public static String getDateAfterAMonth(String date){
		Date iDate = stringToDateShort(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(iDate);
		calendar.add(2, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(calendar.getTime());
		return s ;
	}
	
	public int compareDateString(String dateString1,String dateString2){
		int rslt = 0;
		Date date1 = stringToDate(dateString1,"yyyy-MM-dd");
		Date date2 = stringToDate(dateString2,"yyyy-MM-dd");
		
		int intdate1 = Integer.parseInt(FormatDate(date1,"yyyy-MM-dd"));
		int intdate2 = Integer.parseInt(FormatDate(date2,"yyyy-MM-dd"));
		
		if(intdate1 > intdate2){
			rslt = 1;
		}else if(intdate1 < intdate2){
			rslt = -1;
		}else{
			rslt = 0;
		}
		return rslt;
	}
	
	public int compareDate(Date date1,Date date2){
		int rslt = 0;
		
		
		int intdate1 = Integer.parseInt(FormatDate(date1,"yyyyMMdd"));
		int intdate2 = Integer.parseInt(FormatDate(date2,"yyyyMMdd"));
		
		if(intdate1 > intdate2){
			rslt = 1;
		}else if(intdate1 < intdate2){
			rslt = -1;
		}else{
			rslt = 0;
		}
		return rslt;
	}
	
	
	public String getTimedes2(String granularity,String statisticDate){
		String timedes = "";
		Date date = stringToDateShort(statisticDate);
		String year = "";
		String month = "01";
		String day = "01";
		year = getYear(date).substring(2, 4);
		month = getMonth(date);
		day = getDay(date);
		
		if(granularity.equals("1")){
			timedes = month + "月" + day + "日";
		}else if(granularity.equals("4")){
			timedes = year + "年" + month + "月";
		}
		else if(granularity.equals("8")){
			String quarter = month + "-" + day;
			if(quarter.equals("03-31")){
				timedes = year + "年 第1季度";
			}
			else if(quarter.equals("06-30")){
				timedes = year + "年 第2季度";
			}
			else if(quarter.equals("09-30")){
				timedes = year + "年 第3季度";
			}
			else if(quarter.equals("12-31")){
				timedes = year + "年 第4季度";
			}
		}
		else if(granularity.equals("32")){
			timedes = year + "年";
		}
		return timedes;
	}
	
	public void setdiffDates(Map map,int i){
		Date crrdate = this.getCurrDateTime();
		Date idate = this.getDateChangeTime(crrdate, "y", -i);
		Date iyenddate = this.getMonthEnd(this.getYearBegin(idate));
		String isdatetemp = "";
		for(int m=0;m<1000;m++){
			Date idatetmp = this.getDateChangeTime(iyenddate, "m", m);
			if(this.compareDate(idatetmp, crrdate) >= 0){
				break;
			}
			isdatetemp = this.dateToStringShort(idatetmp);
			map.put(isdatetemp, isdatetemp);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
