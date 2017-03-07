package pojo;

import java.io.Serializable; 

public class DistrictInfo implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String districtID; //primary key.
	private String title;
	private String intro;
	private double lng;
	private double lat;
	private String country;
	private String province;
	private String city;
	private String county;
	private long checkin_num;
	
	
	
	public DistrictInfo(String districtID, String title, String intro, double lng, double lat, String country,
			String province, String city, String county, long checkin_num) {
		super();
		this.districtID = districtID;
		this.title = title;
		this.intro = intro;
		this.lng = lng;
		this.lat = lat;
		this.country = country;
		this.province = province;
		this.city = city;
		this.county = county;
		this.checkin_num = checkin_num;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public long getCheckin_num() {
		return checkin_num;
	}
	public void setCheckin_num(long checkin_num) {
		this.checkin_num = checkin_num;
	}
	
	@Override
	public String toString() {
		return "DistrictInfo [districtID=" + districtID + ", title=" + title + ", intro=" + intro + ", lng=" + lng
				+ ", lat=" + lat + ", country=" + country + ", province=" + province + ", city=" + city + ", county="
				+ county + ", checkin_num=" + checkin_num + "]";
	}

	
	
}
