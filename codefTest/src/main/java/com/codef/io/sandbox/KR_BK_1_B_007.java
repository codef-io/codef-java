package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 은행 기업 빠른계좌 거래내역		
 */
public class KR_BK_1_B_007 {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_B_007;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		bodyMap.put("id", "");
		bodyMap.put("password", "");
		bodyMap.put("fastId", "");
		bodyMap.put("fastPassword", "");
		
		bodyMap.put("organization", "0003");
		
		bodyMap.put("account", "05308159900000"); 
		bodyMap.put("accountPassword", "4812");
		bodyMap.put("startDate", "20190301");
		bodyMap.put("endDate", "20190310");
		bodyMap.put("orderBy", "1");
		bodyMap.put("identity", "1138630000");
		
		bodyMap.put("smsAuthNo", "");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}