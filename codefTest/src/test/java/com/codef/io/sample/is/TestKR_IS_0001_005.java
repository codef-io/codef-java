package com.codef.io.sample.is;

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
 *  한국신용정보원 > 보험다보여 회원 탈퇴
 *
 */
public class TestKR_IS_0001_005 {
	
	/**
	 * :: 한국신용정보원 > 보험다보여 회원 탈퇴
	 * 
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	
	@Test
	public void aa() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		request1();
	}
	
	
	/**
	 * 한국신용정보원 > 보험다보여 회원 탈퇴 1차 요청
	 * :: 기본 파라미터 설정 및 요청
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	private void request1()throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() +  CommonConstant.KR_IS_0001_005;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("id", 		"user_id");				// 로그인 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 로그인패스워드
		
		//bodyMap.put("password1", "변경할 비밀번호");		// 변경할 비밀번호: 임시비밀번호로 로그인한 경우 필수

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
	 
	[requestBody]	요청 바디
	{
		"organization":"0001",
		"id":"user_id",
		"password":"QWEFDAOGFREOJGEROJGREAGAER",
	}
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "",
	    "message" : "성공",
	    "transactionId" : "f72b6f860c364003b368a5778c6d7aed"
	  },
	  "data" : {
	    "resRegistrationStatus" : "1",		<- [회원탈퇴결과] "0" :false , "1" :true
	    "resResultDesc" : ""
	  }
	}
	
	 */
}
