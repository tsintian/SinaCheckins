package pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CheckIn implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	//TODO: confirm the primary key.
	
	private String user_id;  //
	private String poiid;
	private Timestamp checkin_time;
	
	
	
	
	public CheckIn(String user_id, String poiid, Timestamp checkin_time) {
		super();
		this.user_id = user_id;
		this.poiid = poiid;
		this.checkin_time = checkin_time;
	}
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPoiid() {
		return poiid;
	}
	public void setPoiid(String poiid) {
		this.poiid = poiid;
	}
	public Timestamp getCheckin_time() {
		return checkin_time;
	}
	public void setCheckin_time(Timestamp checkin_time) {
		this.checkin_time = checkin_time;
	}
	
	@Override
	public String toString() {
		return "CheckIn [user_id=" + user_id + ", poiid=" + poiid + ", checkin_time=" + checkin_time + "]";
	}
	
	
	
	

}
