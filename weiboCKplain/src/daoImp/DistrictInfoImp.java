package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pojo.DistrictInfo;

public class DistrictInfoImp {
	private  final String tableName = "district_info";
	private Connection conn=null;
	Logger logger = LogManager.getFormatterLogger(DistrictInfoImp.class);
	
	
	public DistrictInfoImp(){
		conn = DBManager.getConnection();
	}
	
	
	public boolean insert(DistrictInfo r){
		String sql="INSERT INTO "+tableName+" (districtID,title,intro,lat,lng,country,province,checkin_num)  "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		if(r == null){
			return false;
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, r.getDistrictID());
			ps.setString(2,r.getTitle());
			ps.setString(3, r.getIntro());
			ps.setDouble(4, r.getLat());
			ps.setDouble(5, r.getLng());
			ps.setString(6, r.getCountry());
			ps.setString(7, r.getProvince());
			ps.setLong(8, r.getCheckin_num());
			
						
			ps.execute();
			return true;		

		} catch (SQLException e) {	
			e.printStackTrace();
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
	
	public boolean isExist(String districtid){
		String sql = "select * from " + tableName+ "  where districtID = ?" ;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, districtid);
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
