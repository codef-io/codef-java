package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 카드 법인 청구내역 조회
 */
public class KR_CD_B_003 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_CD_B_003;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId", "6OOOZ58zAU.aX0pRRgzEBk");	// 희남 법인 connectedId
		
		bodyMap.put("organization", "0301");
		bodyMap.put("identity", "1138630000");
		
		bodyMap.put("startDate", "201902");
		bodyMap.put("inquiryType", "0");
		bodyMap.put("cardNo", "426586******0000");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}