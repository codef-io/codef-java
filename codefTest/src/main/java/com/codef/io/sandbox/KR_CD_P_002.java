package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 카드 개인  승인내역 조회
 */
public class KR_CD_P_002 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_P_002;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId", "sandbox_connectedId");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization", "0309");
		
		bodyMap.put("birthDate", "");	
		
		bodyMap.put("startDate", "20190601");
		bodyMap.put("endDate", "20190619");
		bodyMap.put("orderBy", "0");
		bodyMap.put("inquiryType", "1");
		bodyMap.put("cardNo", "6253-****-****-0000");
		bodyMap.put("cardName", "");
		bodyMap.put("duplicateCardSelect", "");
		bodyMap.put("duplicateCardIdx", "");
		bodyMap.put("memberStoreInfoType" ,"0");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}
