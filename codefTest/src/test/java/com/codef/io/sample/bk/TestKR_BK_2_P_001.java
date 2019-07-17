package com.codef.io.sample.bk;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.sample.util.ApiRequest;
import com.codef.io.sample.util.CommonConstant;

/**
 * 저축 은행 개인 보유계좌	
 */
public class TestKR_BK_2_P_001 {
	
	@Test
	public void testKR_BK_2_P_001() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.API_DOMAIN + CommonConstant.KR_BK_2_P_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId",	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization",	"기관코드"); 
		bodyMap.put("bankName",		"<저축은행 중앙회 모듈 필수: [서브도메인 명]>"); 
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}
