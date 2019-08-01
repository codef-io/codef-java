package com.codef.io.sample.bk;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

/**
 * 은행 법인 보유계좌	
 */
public class TestKR_BK_1_B_001 {
	
	@Test
	public void testKR_BK_1_B_001() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_BK_1_B_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId",	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization",	"기관코드"); 					
		// 요청 파라미터 설정 종료 
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
}
