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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_PB_EF_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "기관코드"); 
		
		bodyMap.put("birthDate", "생년월일(YYYYMMDD)");
		bodyMap.put("licenseNo01", "운전 면허번호01 (지역) 2자리");	// 면허 번호 OO-XX-XXXXXX-XX
		bodyMap.put("licenseNo02", "운전 면허번호02 (년도) 2자리");	// 면허 번호 XX-OO-XXXXXX-XX
		bodyMap.put("licenseNo03", "운전 면허번호03 6자리");		// 면허 번호 XX-XX-OOOOOO-XX
		bodyMap.put("licenseNo04", "운전 면허번호04 2자리");		// 면허 번호 XX-XX-XXXXXX-OO
		bodyMap.put("serialNo", "일련번호");						// 암호일련번호 (면허증 우측 작은사진 밑 위조방지를 위한 숫자+영문 6자리 또는 5자리 번호(숫자 3자리 + 영문2자리))
		bodyMap.put("userName", "사용자 이름");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}