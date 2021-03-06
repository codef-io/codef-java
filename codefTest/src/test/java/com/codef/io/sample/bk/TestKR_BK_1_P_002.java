package com.codef.io.sample.bk;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;
import com.codef.io.util.RSAUtil;

/**
 * 은행 개인 수시입출 거래내역	
 */
public class TestKR_BK_1_P_002 {
	
	@Test
	public void testKR_BK_1_P_002() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_BK_1_P_002;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("connectedId",	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put("organization",	"기관코드"); 
		bodyMap.put("account",		"계좌번호");
		bodyMap.put("startDate",	"조회시작일자");
		bodyMap.put("endDate",		"조회종료일자");
		bodyMap.put("orderBy",		"정렬기준");
		bodyMap.put("inquiryType",	"조회구분");
		bodyMap.put("accountPassword", RSAUtil.encryptRSA("계좌비밀번호", CommonConstant.PUBLIC_KEY));		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}
