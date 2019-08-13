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
import com.codef.io.util.RSAUtil;

/**
 * 은행 개인 빠른계좌		
 */
public class KR_BK_1_P_005 {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.SANDBOX_DOMAIN + CommonConstant.KR_BK_1_P_005;
		
		// 요청 파라미터 설정 시작 - 은행사별로 요구되는 요청 파라미터가 상이합니다. 비밀번호 관련 필드가 사용되는 경우 RSA 암호화가 필요합니다.  
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		bodyMap.put("fastId", "");
		bodyMap.put("fastPassword", "RSA암호화된 비밀번호");		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.	
		bodyMap.put("id", "");
		bodyMap.put("password", "RSA암호화된 비밀번호");			// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
		
		bodyMap.put("organization", "0004");
		
		bodyMap.put("account", "06170204160000"); 
		bodyMap.put("accountPassword", "RSA암호화된 비밀번호");		// 해당 필드 사용시 RSA암호화 필요. 미사용시 공백으로 설정.
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