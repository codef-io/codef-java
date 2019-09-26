package com.codef.io.sandbox;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 운전면허 진위여부 조회
 */
public class KR_PB_A_003 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_PB_EF_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");
		 
		bodyMap.put("birthDate", "19820123");
		bodyMap.put("licenseNo01", "23");
		bodyMap.put("licenseNo02", "08");
		bodyMap.put("licenseNo03", "000000");
		bodyMap.put("licenseNo04", "61");
		bodyMap.put("serialNo", "NO9PTP");
		bodyMap.put("userName", "홍길동");
		
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}
