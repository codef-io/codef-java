package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 은행 개인 빠른계좌 거래내역		
 */
public class KR_BK_1_P_005 {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_005;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		bodyMap.put("fastId", "");
		bodyMap.put("fastPassword", "");
		bodyMap.put("id", "");
		bodyMap.put("password", "");
		
		bodyMap.put("organization", "0004");
		
		bodyMap.put("account", "06170204160000"); 
		bodyMap.put("accountPassword", "3500");
		bodyMap.put("startDate", "20190301");
		bodyMap.put("endDate", "20190619");
		bodyMap.put("orderBy", "0");
		bodyMap.put("identity", "820123");
		
		bodyMap.put("smsAuthNo", "");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}