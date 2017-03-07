package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pojo.CheckIn;



public class CheckInImp {
	private  final String tableName = "checkins";
	private Connection conn=null;
	Logger logger = LogManager.getFormatterLogger(CheckInImp.class);
	
	
	public CheckInImp(){
		conn = DBManager.getConnection();
	}
	
	
	public boolean insert(CheckIn r){
		String sql="INSERT INTO "+tableName+" (uid,poiid,ck_timestamp)  "
				+ "VALUES (?,?,?)";
		if(r == null){
			return false;
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, r.getUser_id());
			ps.setString(2, r.getPoiid());
			ps.setTimestamp(3, r.getCheckin_time());
						
			ps.execute();
			return true;		

		} catch (SQLException e) {			
			logger.error("erros when inserting the record"+e);
			logger.error("record :"+ r);
		}finally{
			try{
				if(ps != null)
					ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("erros when inserting the record"+e);
			}
		}
		return false;
	}
	
	public boolean isExist(String uid,String poiid,Timestamp time){
		String sql = "select * from " + tableName+ "  where uid = ? AND poiid = ? AND ck_timestamp = ?" ;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, poiid);
			ps.setTimestamp(3, time);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Encounters error while check whether the record already exists."+e.getMessage());
		}finally{
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
					logger.error("Encounters error while check whether the record already exists."+e.getMessage());
				}
		}
		return false;
	}
	
	
	public void close(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Encounters error while close the connection. " +e.getMessage());
			}
		}
	}
	
}
