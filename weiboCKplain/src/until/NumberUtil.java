package until;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberUtil {
	
	private static Logger logger = LogManager.getFormatterLogger(NumberUtil.class);
	
	/**
	 * Transform a number which is between 0 and 99  to String. 
	 * If the number is less than 10, one zero will be added at its left side.
	 * @param num
	 * @return
	 */
	public static String intToStr2(int num){
		if(num < 0 || num > 99)
			return null;
		
		if( num < 10){
			return "0"+num;
		}else{
			return Integer.toString(num);
		}
	}
	
	/**
	 * Convert string into double.
	 * @param str
	 * @return
	 */
	public static Double str2Double(String str){
		if(str == null)
			return null;
		try{
		return Double.parseDouble(str);
		}catch(NumberFormatException e){
			logger.error("encounter errors while convert string into double . "+str+" : "+e.getMessage());
			return null;
		}
	}
	
	
	public static Long str2Long(String str){
		if(str == null)
			return null;
		try{
			return Long.parseLong(str);
		}catch(NumberFormatException e){
			logger.error("error appears while converting string to long : "+str+" . "+e.getMessage());
			return null;
		}
	}
}
