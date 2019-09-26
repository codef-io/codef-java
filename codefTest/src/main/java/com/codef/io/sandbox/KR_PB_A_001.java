package com.codef.io.sandbox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 사업자 상태정보(휴폐업) 조회
 */
public class KR_PB_A_001 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_PB_NT_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0004");
		
		List<HashMap<String, String>> companyList = new ArrayList<HashMap<String, String>>();
		String[] companyArr = { "3333344444", "1234567890" };
		for (String company : companyArr) {
			HashMap<String, String> companyMap = new HashMap<String, String>();
			companyMap.put("reqIdentity", company);
			companyList.add(companyMap);
		} 
		bodyMap.put("reqIdentityList", companyList);
		
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}
