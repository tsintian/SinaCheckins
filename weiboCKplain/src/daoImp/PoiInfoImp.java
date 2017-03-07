package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pojo.PoiInfo;

public class PoiInfoImp {
	private  final String tableName = "poi_info";
	private Connection conn=null;
	Logger logger = LogManager.getFormatterLogger(PoiInfoImp.class);
	
	
	public PoiInfoImp(){
		conn = DBManager.getConnection();
	}
	
	
	public boolean insert(PoiInfo r){
		String sql="INSERT INTO "+tableName+" (poiid,title,address,lat,lng,category,city,province,district_no,"
				+ "phone,extra,rid,categorys,category_name,checkin_user_num,poi_street_address,enterprise,trend,country)  "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(r == null){
			return false;
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, r.getPoiid());
			ps.setString(2,r.getTitle());
			ps.setString(3, r.getAddress());
			ps.setDouble(4, r.getLat());
			ps.setDouble(5, r.getLon());
			ps.setString(6, r.getCategory());
			ps.setString(7, r.getCity());
			ps.setString(8, r.getProvince());
			ps.setString(9, r.getDistrict());
			ps.setString(10, r.getPhone());
			ps.setString(11, r.getExtra());
			ps.setString(12, r.getRid());
			ps.setString(13, r.getCategorys());
			ps.setString(14, r.getCategory_name());
			ps.setLong(15, r.getCheckin_user_num());
			ps.setString(16, r.getPoi_street_address());
			ps.setInt(17, r.getEnterprise());
			ps.setString(18, r.getTrend());
			ps.setString(19, r.getCountry());
						
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
	
	public boolean isExist(String poiid){
		String sql = "select * from " + tableName+ "  where poiid = ?" ;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, poiid);
			;
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
