package test;

import process.GetCheckins;
import process.GetCheckins2;
import until.TokenUtil;

public class TestGetCheckins {

	public static void main(String[] args) {
		
		String access_token = TokenUtil.GetToken();
		//1093672054
		//2844136217
		String uid="2844136217";
		int page = 1;
		int count = 30;
		GetCheckins tck = new GetCheckins();
		//GetCheckins2 tck2 = new GetCheckins2();
		//tck2.getCheckin(uid);
		tck.searchWrapper(access_token, uid);
		System.out.println("===========over===============");

	}

}
