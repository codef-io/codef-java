package com.codef.io.sample.pb;

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
 * 공공 운전면허 진위여부
 */
public class TestKR_PB_A_004 {
	
	@Test
	public void testKR_PB_A_004() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_PB_MW_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "기관코드"); 
		
		bodyMap.put("certFile", "BASE64로 Encoding된 인증서 der파일 문자열");	// 진위여부를 판단할 사용자가 아닌 제3자의 인증서로 로그인 가능
		bodyMap.put("keyFile",	"BASE64로 Encoding된 인증서 key파일 문자열");	
		bodyMap.put("password",  RSAUtil.encryptRSA("인증서비밀번호", CommonConstant.PUBLIC_KEY));
		
		bodyMap.put("identity",	"사용자 주민번호");
		bodyMap.put("issueDate","발급일자");
		bodyMap.put("userName",	"사용자 이름");
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}