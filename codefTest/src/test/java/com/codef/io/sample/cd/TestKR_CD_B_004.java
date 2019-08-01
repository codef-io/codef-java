package com.codef.io.sample.cd;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

/**
 * 카드 법인 한도조회
 *
 */
public class TestKR_CD_B_004 {
	
	@Test
	public void testKR_CD_B_004() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_CD_B_004;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId",	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization",	"기관코드"); 
		bodyMap.put("identity",		"사업자번호/주민번호"); 
		
		bodyMap.put("cardNo",		"카드번호");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}