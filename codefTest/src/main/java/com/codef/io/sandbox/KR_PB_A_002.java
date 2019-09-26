package com.codef.io.sandbox;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;

/**
 * 부동산등기 열람/발급
 */
public class KR_PB_A_002 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_PB_CK_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		bodyMap.put("organization", "0002");
		
		bodyMap.put("phoneNo", "01011112222");
		bodyMap.put("password", "RSA암호화된 비밀번호");
		bodyMap.put("inquiryType", "3");
		bodyMap.put("uniqueNo", "");
		bodyMap.put("realtyType", "1");
		bodyMap.put("addr_sido", "서울특별시");
		bodyMap.put("address", "");
		bodyMap.put("recordStatus", "0");
		
		bodyMap.put("addr_dong", "");
		bodyMap.put("addr_lotNumber", "");
		bodyMap.put("inputSelect", "");
		bodyMap.put("buildingName", "");
		bodyMap.put("dong", "801");
		bodyMap.put("ho", "804");
		bodyMap.put("addr_sigungu", "OO구");
		bodyMap.put("addr_roadName", "OOOO로5길");
		bodyMap.put("addr_buildingNumber", "62");
		bodyMap.put("jointMortgageJeonseYN", "1");
		bodyMap.put("tradingYN", "1");
		bodyMap.put("listNumber", "");
		bodyMap.put("electronicClosedYN", "");
		bodyMap.put("ePrepayNo", "V74117000000");
		bodyMap.put("ePrepayPass", "password1234");
		bodyMap.put("issueType", "1");
		bodyMap.put("startPageNo", "");
		bodyMap.put("pageCount", "");
		bodyMap.put("originData", "");
		bodyMap.put("originDataYN", "");
		bodyMap.put("warningSkipYN", "");
		bodyMap.put("registerSummaryYN", "1");
		bodyMap.put("applicationType", "");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}
