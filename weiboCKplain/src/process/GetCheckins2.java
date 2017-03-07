package process;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import until.TokenUtil;
import weibo4j.Place;
import weibo4j.model.Places;
import weibo4j.model.WeiboException;

public class GetCheckins2 {
	
	public void getCheckin(String uid){
		
		Place pl = new Place(TokenUtil.GetToken());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<Places> ps = null;

		Map<String,String> m = new HashMap<String,String>();
		m.put("access_token",TokenUtil.GetToken());
		m.put("uid", uid);
		
		try {
			ps = pl.checkinsList(uid);
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ps != null){
			int i=0;
			for(Places p : ps){
				System.out.println(p.getTodoNum());
				System.out.println(++i+p.toString());
			}
		}
	}
	
	
	
}
