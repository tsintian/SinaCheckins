package until;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import pojo.DistrictInfo;
import pojo.PoiInfo;




public class Adapters {
	static Logger logger = LogManager.getLogger(Adapters.class);

	/**
	 * Converts java.util.Date to java.sql.Date.
	 * @param d
	 * @return
	 */
	public static java.sql.Date utilDate2sqlDate(Date d){
		if(d != null)
			return new java.sql.Date(d.getTime());
		return null;
	}

	
	/**
	 * Converts a JSONObject object into a DistrictInfo object.
	 * @param jsonDistrict
	 * @return
	 */
	public static DistrictInfo jsonO2DistrictInfo(JSONObject jsonDistrict){
		try{
			String districtID = jsonDistrict.getString("id");
			String title = jsonDistrict.getString("title");
			String intro = jsonDistrict.getString("intro");
			double lng = NumberUtil.str2Double(jsonDistrict.getString("lng"));
			double lat = NumberUtil.str2Double(jsonDistrict.getString("lat"));
			String country = jsonDistrict.getString("country");
			String province = jsonDistrict.getString("province");
			String city = jsonDistrict.getString("city");
			String county = jsonDistrict.getString("county");
			long checkin_user_num = jsonDistrict.getLong("checkin_user_num");
			
			return new DistrictInfo(districtID, title, intro, lng, lat, country,
					province, city, county, checkin_user_num);

		}
		catch(JSONException e){
			logger.error("Error appears while converting json object into districtInfo object. "+jsonDistrict.toString()+" . "+e.getMessage());
			return null;
		}

		
		

	}
	
	public static Timestamp string2Timestamp(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date d = sdf.parse(str);
			Timestamp time = new Timestamp(d.getTime());
			return time;
		} catch (ParseException e) {
			logger.error("error appears while converting string into timestamp : "+ str + " . "+e.getMessage());
			return null;
		}
	}
}
