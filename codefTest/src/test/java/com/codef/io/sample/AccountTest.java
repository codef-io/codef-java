package com.codef.io.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import com.codef.io.sample.util.ApiRequest;
import com.codef.io.sample.util.CommonConstant;

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
	 */
	@Test  @Ignore
	public void create() throws IOException, InterruptedException, ParseException {
		/// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.CREATE_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0004");
		accountMap1.put("loginType",  	"0");
		accountMap1.put("password",  	"INSERT YOUR END USER PASSWORD");
		accountMap1.put("keyFile",  	"INSERT YOUR END USER keyFile to BASE64 Encoding String");
		accountMap1.put("derFile",  	"INSERT YOUR END USER derFile to BASE64 Encoding String");
		list.add(accountMap1);
		
		HashMap<String, Object> accountMap2 = new HashMap<String, Object>();
		accountMap2.put("countryCode",	"KR");
		accountMap2.put("businessType",	"BK");
		accountMap2.put("clientType",  	"P");
		accountMap2.put("organization",	"0020");
		accountMap2.put("loginType",  	"1");
		accountMap2.put("password",  	"INSERT END USER PASSWORD");
		accountMap2.put("id",  			"INSERT END USER ID");
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
	@Test @Ignore
	public void list() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.GET_ACCOUNTS;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		String client_id = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시 
		bodyMap.put(CommonConstant.CONNECTED_ID, client_id);
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
	 */
	@Test @Ignore
	public void add() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.ADD_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0020");
		accountMap1.put("loginType",  	"0");
		accountMap1.put("password",  	"INSERT YOUR END USER PASSWORD");
		accountMap1.put("keyFile",  	"INSERT YOUR END USER keyFile to BASE64 Encoding String");
		accountMap1.put("derFile",  	"INSERT YOUR END USER derFile to BASE64 Encoding String");
		list.add(accountMap1);
		
		bodyMap.put("accountList", list);
		
		String client_id = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, client_id);
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
	 */
	@Test @Ignore
	public void update() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.UPDATE_ACCOUNT;	
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
		accountMap1.put("countryCode",	"KR");
		accountMap1.put("businessType",	"BK");
		accountMap1.put("clientType",  	"P");
		accountMap1.put("organization",	"0020");
		accountMap1.put("loginType",  	"0");
		accountMap1.put("password",  	"INSERT YOUR END USER PASSWORD");
		accountMap1.put("keyFile",  	"INSERT YOUR END USER keyFile to BASE64 Encoding String");
		accountMap1.put("derFile",  	"INSERT YOUR END USER derFile to BASE64 Encoding String");
		list.add(accountMap1);
		
		bodyMap.put("accountList", list);
		
		String client_id = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, client_id);
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
	@Test @Ignore
	public void delete() throws IOException, InterruptedException, ParseException {
		// 요청 URL 설정
		String urlPath = CommonConstant.TEST_DOMAIN + CommonConstant.DELETE_ACCOUNT;	
		
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
		
		String client_id = "45t4DJOD44M9uwH7zxSgBg";	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
		bodyMap.put(CommonConstant.CONNECTED_ID, client_id);
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
		
		// 응답결과 확인
		System.out.println(result);
	}
	
}


