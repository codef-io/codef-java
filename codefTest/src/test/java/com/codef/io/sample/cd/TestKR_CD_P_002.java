package com.codef.io.sample.cd;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;

/**
 * 카드 개인  승인내역 조회
 */
public class TestKR_CD_P_002 {
	
	@Test
	public void testKR_CD_P_001() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_CD_P_002;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId",	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization",	"기관코드"); 
		bodyMap.put("birthDate",	"생년월일"); 
		
		bodyMap.put("startDate",	"조회시작일자");
		bodyMap.put("endDate",		"조회종료일자");
		bodyMap.put("orderBy",		"정렬기준");
		bodyMap.put("inquiryType",	"조회구분");
		bodyMap.put("cardNo",		"카드번호");
		bodyMap.put("cardName",		"카드명칭");
		bodyMap.put("duplicateCardSelect",	"중복카드선택");
		bodyMap.put("duplicateCardIdx",		"중복카드일련번호");
		bodyMap.put("memberStoreInfoType",	"가맹점정보포함여부");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}