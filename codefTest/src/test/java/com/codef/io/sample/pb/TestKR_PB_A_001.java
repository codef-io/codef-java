package com.codef.io.sample.pb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

/**
 * 공공 사업자상태
 */
public class TestKR_PB_A_001 {
	
	@Test
	public void testKR_PB_A_001() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_PB_NT_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "기관코드"); 
		
		List<HashMap<String, String>> companyList = new ArrayList<HashMap<String, String>>();
		String[] companyArr = { "사업자등록번호" };
		for (String company : companyArr) {
			HashMap<String, String> companyMap = new HashMap<String, String>();
			companyMap.put("reqIdentity", company);
			companyList.add(companyMap);
		} 
		bodyMap.put("reqIdentityList", companyList); 
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}