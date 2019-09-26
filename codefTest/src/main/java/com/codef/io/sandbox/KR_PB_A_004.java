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
 * 주민등록 진위여부 조회
 */
public class KR_PB_A_004 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_PB_MW_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0002");
		 
		bodyMap.put("password", "RSA암호화된 비밀번호");
		bodyMap.put("certFile", "BASE64로 Encoding된 인증서 der파일 문자열");	// 진위여부를 판단할 사용자가 아닌 제3자의 인증서로 로그인 가능
		bodyMap.put("keyFile",	"BASE64로 Encoding된 인증서 key파일 문자열");
		
		bodyMap.put("identity", "8201231000000");
		bodyMap.put("issueDate", "20150518");
		bodyMap.put("userName", "홍길동");
		
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = SandboxApiRequest.reqeust(urlPath, bodyMap);	//  샌드박스 요청 오브젝트 사용
	}
}
