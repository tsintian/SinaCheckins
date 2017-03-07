package process;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import daoImp.CheckInImp;
import daoImp.DistrictInfoImp;
import daoImp.PoiInfoImp;
import pojo.CheckIn;
import pojo.DistrictInfo;
import pojo.PoiInfo;
import until.Adapters;
import until.NumberUtil;
import until.TokenUtil;


public class GetCheckins {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		String access_token = TokenUtil.GetToken();
		//1093672054
		//2844136217
		String uid="2844136217";
		int page = 1;
		int count = 30;
		GetCheckins tck = new GetCheckins();
		tck.searchWrapper(access_token, uid);
		System.out.println("===========over===============");
	}
	
	
	
	public void searchWrapper(String access_token,String uid){
		JSONObject totalResult = this.search(access_token, uid, 1,20);
		if(totalResult == null)
			return;
		int totalNum = totalResult.getInt("total_number");
		//System.out.println("totalNum: "+totalNum);
		//Computing the number of pages while page_size is 20.
		int pageNum = (int)(totalNum/20);
		//System.out.print("pageNUm: "+pageNum);
		
		
		
		//Iterating the pages.
		for(int i=1;i<=pageNum+1;i++){
			JSONObject pageResult = this.search(access_token,uid,i,20);
			
			JSONArray results = pageResult.getJSONArray("pois");
			
			
			DistrictInfoImp disImp = new DistrictInfoImp();
			PoiInfoImp poiImp =new PoiInfoImp();
			CheckInImp chkImp = new CheckInImp();
			
			
			//For every page.
			for(int j=0;j<results.length();j++){
				JSONObject tempObject = results.getJSONObject(j);
				
				//Get the district information.
				JSONObject jsonDistrict = tempObject.getJSONObject("district_info");
				DistrictInfo districtInfo = Adapters.jsonO2DistrictInfo(jsonDistrict);
				//Get the poi information.								
				String poiid = tempObject.getString("poiid");
				String title = tempObject.getString("title");
				String address = tempObject.getString("address");				
				double lng = NumberUtil.str2Double(tempObject.getString("lon"));
				double lat = NumberUtil.str2Double(tempObject.getString("lat"));
				String category = tempObject.getString("category");
				String city = tempObject.getString("city");
				String province = tempObject.getString("province");				
				String country = tempObject.getString("country");
				String phone = tempObject.getString("phone");
				
				String extra = null;
				Object oExtra = tempObject.get("extra");
				if(oExtra != null){
					extra = oExtra.toString();
					//System.out.print("extra length: "+extra.length());
				}
				//String extra = tempObject.getString("extra");
				String rid = tempObject.getString("rid");				
				String district = tempObject.getString("district");
				String categorys = tempObject.getString("categorys");
				String category_name = tempObject.getString("category_name");
				long checkinUserNum = NumberUtil.str2Long(tempObject.getString("checkin_user_num"));
				String poiStreetAddress = tempObject.getString("poi_street_address");				
				int enterprise = tempObject.getInt("enterprise");
				String trend = tempObject.getString("trend");
				
				
				PoiInfo poi = new PoiInfo(poiid, title, address, lng, lat, category, city,
						province, country, district, phone, extra, rid, categorys,
						category_name, checkinUserNum, poiStreetAddress, enterprise, trend);
				
				//checkin time.
				Timestamp checkinTime = Adapters.string2Timestamp(tempObject.getString("checkin_time")); 
				
				//Checkin information.
				CheckIn ckInfo = new CheckIn(uid,poiid,checkinTime);
				
				//Inserts District information.	
				if(!disImp.isExist(districtInfo.getDistrictID())){
					disImp.insert(districtInfo);	
				}
							
				
				//Inserts POI information.	
				if(!poiImp.isExist(poiid)){
					poiImp.insert(poi);		
				}
						
				
				//Insert Checkin information.	
				if(!chkImp.isExist(uid, poiid, checkinTime)){
					chkImp.insert(ckInfo);	
				}
							
				
				System.out.println("district: " +districtInfo);
				System.out.println("poi: " + poi);
				System.out.println("checkin: "+ckInfo);		
				
			}
			
			disImp.close();
			poiImp.close();
			chkImp.close();
		}
		
		
		
	}
	
	
	private JSONObject search(String token,String uid,int page,int count){
		//https://api.weibo.com/2/place/users/checkins.json
		/*
		 * parameters:
		 *       access_token: 
		 *       uid:
		 *       count: range from 20 to 50
		 *       page:  start with 1. 
		 *       base_app:
		 */
		URI uri;
		try {
			String pageNum = Integer.toString(page);
			String countNum = Integer.toString(count);
			uri = new URIBuilder().setScheme("https")
					.setHost("api.weibo.com").setPath("/2/place/users/checkins.json")
					.setParameter("access_token", token)
					.setParameter("uid", uid)
					.setParameter("page", pageNum)
					.setParameter("count", countNum)
					.build();
			
			System.out.println(uri.toString());
			CloseableHttpClient client = HttpClients.createDefault();
			
			HttpGet httpGet = new HttpGet();
			
			
			httpGet.setURI(uri);
			CloseableHttpResponse response = client.execute(httpGet);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				
				if (entity != null) {
					StringBuffer sb = new StringBuffer();
					BufferedInputStream in = new BufferedInputStream(entity.getContent());
					byte[] bytes = new byte[1024];
					int length = -1;
					while ((length = in.read(bytes, 0, 1024)) != -1) {
						String str = new String(bytes, 0, length,"UTF-8");
						sb.append(str);
					}
					in.close();
					//String jsonResult = sb.toString();

					//System.out.println(sb.toString());
					if(!sb.toString().startsWith("{"))
						return null;
					return new JSONObject(sb.toString());
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
