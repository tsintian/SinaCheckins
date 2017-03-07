package pojo;

import java.io.Serializable;

public class PoiInfo implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String poiid;
	private String title;
	private String address;
	private double lon;
	private double lat;
	private String category;
	private String city;
	private String province;
	private String country;
	private String district; //Foreigh Key responding to district ID.
	private String phone;
	private String extra;
	private String rid;
	private String categorys;
	private String category_name;
	private long checkin_user_num;
	private String poi_street_address;
	private int enterprise;
	private String trend;
	
	
	
	
	
	public PoiInfo(String poiid, String title, String address, double lon, double lat, String category, String city,
			String province, String country, String district, String phone, String extra, String rid, String categorys,
			String category_name, long checkin_user_num, String poi_street_address, int enterprise, String trend) {
		super();
		this.poiid = poiid;
		this.title = title;
		this.address = address;
		this.lon = lon;
		this.lat = lat;
		this.category = category;
		this.city = city;
		this.province = province;
		this.country = country;
		this.district = district;
		this.phone = phone;
		this.extra = extra;
		this.rid = rid;
		this.categorys = categorys;
		this.category_name = category_name;
		this.checkin_user_num = checkin_user_num;
		this.poi_street_address = poi_street_address;
		this.enterprise = enterprise;
		this.trend = trend;
	}
	public String getPoiid() {
		return poiid;
	}
	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getCategorys() {
		return categorys;
	}
	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public long getCheckin_user_num() {
		return checkin_user_num;
	}
	public void setCheckin_user_num(long checkin_user_num) {
		this.checkin_user_num = checkin_user_num;
	}
	public String getPoi_street_address() {
		return poi_street_address;
	}
	public void setPoi_street_address(String poi_street_address) {
		this.poi_street_address = poi_street_address;
	}
	public int getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(int enterprise) {
		this.enterprise = enterprise;
	}
	public String getTrend() {
		return trend;
	}
	public void setTrend(String trend) {
		this.trend = trend;
	}
	@Override
	public String toString() {
		return "PoiInfo [poiid=" + poiid + ", title=" + title + ", address=" + address + ", lon=" + lon + ", lat=" + lat
				+ ", category=" + category + ", city=" + city + ", province=" + province + ", country=" + country
				+ ", district=" + district + ", phone=" + phone + ", extra=" + extra + ", rid=" + rid + ", categorys="
				+ categorys + ", category_name=" + category_name + ", checkin_user_num=" + checkin_user_num
				+ ", poi_street_address=" + poi_street_address + ", enterprise=" + enterprise + ", trend=" + trend
				+ "]";
	}
	
	
	
	
	
}
