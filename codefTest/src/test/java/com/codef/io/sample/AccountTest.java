package com.codef.io.sample;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import com.codef.io.util.ApiRequest;
import com.codef.io.util.CommonConstant;
import com.codef.io.util.RSAUtil;

/** 
 * 계정관리 테스트
 */
public class AccountTest {
	/**
	 * 계정 등록을 통한 커넥티트 아이디 발급
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
	@Ignore
	public void create() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		/// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.CREATE_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0004");
		accountMap1.put("loginType",  	"0");
		
		String password1 = "엔드유저의 인증서 비밀번호";
		accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */
				
		accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
		accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
		list.add(accountMap1);
		
		HashMap<String, Object> accountMap2 = new HashMap<String, Object>();
		accountMap2.put("countryCode",	"KR");
		accountMap2.put("businessType",	"BK");
		accountMap2.put("clientType",  	"P");
		accountMap2.put("organization",	"0020");
		accountMap2.put("loginType",  	"1");
		
		String password2 = "엔드유저의 기관 로그인 비밀번호";
		accountMap2.put("password",  	RSAUtil.encryptRSA(password2, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */

		accountMap2.put("id",  			"엔드 유저의 기관 로그인 아이디");
		accountMap2.put("birthday",		"YYMMDD");
		list.add(accountMap2);
		
		bodyMap.put("accountList", list);
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
	/**	
	 * 계정 목록조회
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@Test 
	@Ignore
	public void list() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.GET_ACCOUNTS;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		String connectedId = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}

	/**	
	 * connectedId 목록조회
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@Test 
	@Ignore
	public void connectedIdList() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.GET_CONNECTED_IDS;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put(CommonConstant.PAGE_NO, 0);		// 페이지 번호(생략 가능) 생략시 1페이지 값(0) 자동 설정
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
	 * 계정 추가 테스트
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
	@Ignore
	public void add() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.ADD_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0020");
		accountMap1.put("loginType",  	"0");
		
		String password1 = "엔드유저의 인증서 비밀번호";
		accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */
				
		accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
		accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
		list.add(accountMap1);
		
		bodyMap.put("accountList", list);
		
		String connectedId = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
	 * 계정 수정 테스트
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
	@Ignore
	public void update() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.UPDATE_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0020");
		accountMap1.put("loginType",  	"0");
		
		String password1 = "엔드유저의 인증서 비밀번호";
		accountMap1.put("password",  	RSAUtil.encryptRSA(password1, CommonConstant.PUBLIC_KEY));	/**	password RSA encrypt */
				
		accountMap1.put("keyFile",      "BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열");
		accountMap1.put("derFile",      "BASE64로 Encoding된 엔드유저의 인증서 der파일 문자열");
		list.add(accountMap1);
		
		bodyMap.put("accountList", list);
		
		String connectedId = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
	

	/**
	 * 계정 삭제 테스트
	 * 
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test 
	@Ignore
	public void delete() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.DELETE_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType", "BK");
		accountMap1.put("clientType",   "P");
		accountMap1.put("organization", "0020");
		accountMap1.put("loginType",  	"0");
		list.add(accountMap1);
		
		bodyMap.put("accountList", list);
		
		String connectedId = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, connectedId);
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
	 * BASE64로 Encoding된 엔드유저의 인증서 key파일 문자열 추출 샘플
	 *  
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	@Test 
	@Ignore
	public void getBase64FromCertFile() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		File certFile = new File("/Users/abcdefg/Documents/Modules/certification/heenamsub/signCert.der");
		File keyFile = new File("/Users/abcdefg/Documents/Modules/certification/heenamsub/signPri.key");
		
		byte[] fileContent = FileUtils.readFileToByteArray(certFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		System.out.println(encodedString);
		
		fileContent = FileUtils.readFileToByteArray(keyFile);
		encodedString = Base64.getEncoder().encodeToString(fileContent);
		System.out.println(encodedString);
	}
	
}


