package com.codef.io.sample.pb;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

/**
 * 공공 운전면허 진위여부
 */
public class TestKR_PB_A_003 {
	
	@Test
	public void testKR_PB_A_003() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_PB_A_003;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "기관코드"); 
		
		bodyMap.put("birthDate", "생년월일(YYYYMMDD)");
		bodyMap.put("licenseNo01", "운전 면허번호01 (지역)");
		bodyMap.put("licenseNo02", "운전 면허번호02 (년도)");
		bodyMap.put("licenseNo03", "운전 면허번호03");
		bodyMap.put("licenseNo04", "운전 면허번호04");
		bodyMap.put("serialNo", "일련번호");
		bodyMap.put("userName", "사용자 이름");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}