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
 *  한국신용정보원 > 보험다보여 회원 가입 2Way 방식 안내
 *
 */
public class TestKR_IS_0001_002 {
	
	/**
	 * :: 한국신용정보원 > 보험다보여 회원 가입은 2Way인증을 통한 추가 정보 입력이 진행되어야 정상적인 상품 요청이 완료됩니다.
	 * 
	 * 1. 2Way 방식은 1차 [기본 파라미터] 요청 + 추가 [기본 파라미터 + 추가(인증정보) 파라미터] 요청으로 이뤄집니다. 
	 * 	- 2Way는 2차례 요청을 의미하진 않습니다. 대상기관이 요구하는 인증 방식에 따라 2차가 아닌 n차로 추가 정보를 입력합니다. 
	 * 	- 요청 방식은 n회만큼 동일하게 반복될 뿐 더 복잡해지지 않습니다.
	 *  - 1차 이후의 n차 요청 역시 모두 동일한 URL을 사용합니다.
	 * 
	 * 2. 추가로 요청하는 파라미터는 SMS수신인증번호, reCAPTCHA 보안문자 입력 등과 같은 "사용자 인증을 위한 정보"가 대부분입니다.
	 *  - 일부 타업무의 경우, "사용자 인증을 위한 정보"가 아닌 별도의 다른 정보(파라미터)로 2Way 요청을 하는 경우도 존재합니다.
	 *  - 그러한 특수한 추가 파라미터를 필요로 하는 업무의 경우 CODEF홈페이지 등을 통해 별도의 가이드가 첨부될 예정입니다.
	 * 
	 * 3. 샌드박스는 2Way를 지원하지 않습니다.
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
		
		request1();	// 1차 : 기본 요청
		
//		request2();	// 2차 : 기본 요청 + 이메일 인증번호
		
//		request3();	// 3차 : 기본 요청 + 이메일 인증번호 + 보안문자 ( 신용카드 인증 사용자의 경우 3차에서 회원 가입 완료)
		
//		request4();	// 4차 : 기본 요청 + 이메일 인증번호 + 보안문자 + 사용자가 선택한 추가 인증( 휴대폰SMS 인증 )
	}
	
	
	/**
	 * 한국신용정보원 > 보험다보여 회원 가입 1차 요청
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
		String urlPath = CommonConstant.getRequestDomain() +  CommonConstant.KR_IS_0001_002;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("id", 		"user_id");				// 로그인 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 로그인패스워드
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)

		// 신용카드인증 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		// bodyMap.put("cardNo", "신용카드번호입력");			// 카드번호
		// bodyMap.put("cardvalidPeriod", "MMYY");				// 카드유효기간	
		// bodyMap.put("cardPassword",  RSAUtil.encryptRSA("NN", CommonConstant.PUBLIC_KEY));	// 카드비밀번호
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
 	#1. 1차 요청
	[requestBody]	요청 바디
	{
		"organization":"0001",
		"userName":"홍길동",
		"identity":"8201231111111",
		"id":"user_id",
		"password":"iPLECaF5BGUTJ0vsiMQ3b9V9oKcPnjJvYOVNBzTi9OwsrAyyDY5fo23m94CiCvjaKO4c6CMhjb42pzM0Iovpu/ANNz+kvBGTcM1mhr6Foup+8jO6rK6j/4xU/hTj7RxXj65zcTPsMLdEK/QglDby4j/kJgfrjtJUzuXXa08GWFUC/46qJNWBkqe5o/fs5GeLqF933zxI98Z4wq7igsYeNP26kLo7rG0/EXe+0WqV74yq0fVQDRgrapLdAE1wrrxr5i9QONXHFs+YFR1lsunXVx+78mJu4vvsbIfnaFYioN9Hk1hwiJspG2bjNBvSRLJnjsZ+rsyxcSMZlD1esJvndg==",
		"email":"user_email@gmail.com",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
	}
	
	
	- 1차 요청 이후 서버로부터 아래와 같이 "API 요청 처리 정상 진행 중"이란 성공 응답을 받게 되면 클라이언트는 추가 요청에 대한 준비를 해야합니다.
	추가 요청 필요 여부는 data.continue2Way의 값으로 판단하며 추가 요청이 필요 없는 경우에는 data.continue2Way는 존재하지 않습니다.
	따라서 클라이언트는 result.code가 CF-00000이고 HashMap으로 캐스팅한 data에 containsKey("continue2Way") 여부를 판단한 뒤 해당 값이 true이면 
	data의 나머지 항목들을 모두 2Way 정보로 설정해 추가 요청을 진행합니다. 

	* 1차 요청에 대한 응답으로 extraInfo가 반환됩니다.
	** extraInfo.reqEmailAuthNo는 사용자가 입력한 이메일로 발송된 인증숫자를 설정할 항목입니다. 사용자는 수신한 이메일의 인증숫자 다음 요청시 요청 바디에 추가해야 합니다.
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "28fefcad9b6b4b1a823c6deebdef8768"
	  },
	  "data" : {
	    "continue2Way" : true,						<-- 2Way 추가 요청 필요 여부(해당 값이 존재하고 true인 경우 2Way 방식이 진행중인 것으로 판단함)
	    "jobIndex" : 0,								<-- 2Way 정보(추가 요청 바디에 사용)
	    "threadIndex" : 0,							<-- 2Way 정보(추가 요청 바디에 사용)
	    "jti" : "5db280e0ec828b92fc9c1818",			<-- 2Way 정보(추가 요청 바디에 사용)
	    "twoWayTimestamp" : 1571979491176,			<-- 2Way 정보(추가 요청 바디에 사용)
	    "extraInfo" : {								<-- 2Way 정보(보안 이미지 문자 이미지 파일)
	      "reqEmailAuthNo" : ""
	    }
	  }
	}
	 */
	
	
	
	/**
	 * 2차 요청
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
	private void request2() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() +  CommonConstant.KR_IS_0001_002;
		
		// 요청 파라미터 설정 시작 - 1차 요청과 모두 동일한 값으로 설정
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("id", 		"user_id");				// 로그인 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 로그인패스워드
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(60~170)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(이메일 인증 문자)
		bodyMap.put("emailAuthNo", "067042");				// 엔드유저가 입력한  이메일 인증 문자
		
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부

		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db280e0ec828b92fc9c1818");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571979491176L);			// long **
		
		bodyMap.put("twoWayInfo", twoWayInfoMap);						// 1차 요청의 응답으로 받은 2Way 정보 설정
		
		// 요청 파라미터 설정 종료

		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
 	#2. 2차 요청
	[requestBody]	요청 바디
	{
		"organization":"0001",
		"userName":"홍길동",
		"identity":"8201231111111",
		"id":"user_id",
		"password":"iPLECaF5BGUTJ0vsiMQ3b9V9oKcPnjJvYOVNBzTi9OwsrAyyDY5fo23m94CiCvjaKO4c6CMhjb42pzM0Iovpu/ANNz+kvBGTcM1mhr6Foup+8jO6rK6j/4xU/hTj7RxXj65zcTPsMLdEK/QglDby4j/kJgfrjtJUzuXXa08GWFUC/46qJNWBkqe5o/fs5GeLqF933zxI98Z4wq7igsYeNP26kLo7rG0/EXe+0WqV74yq0fVQDRgrapLdAE1wrrxr5i9QONXHFs+YFR1lsunXVx+78mJu4vvsbIfnaFYioN9Hk1hwiJspG2bjNBvSRLJnjsZ+rsyxcSMZlD1esJvndg==",
		"email":"user_email@gmail.com",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"emailAuthNo":"027610",			<- 사용자 이메일로 전송된 인증 문자 입력
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 1차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db280e0ec828b92fc9c1818",
						"twoWayTimestamp":1571979491176
					}
	}
	
	
	- 추가 요청의 파라미터는 1차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 이메일 인증 문자(emailAuthNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	
	* 2차 요청에 대한 응답으로 extraInfo가 반환됩니다.
	** extraInfo.reqSecureNo는 보안 이미지 문자 이미지 파일 데이터로서 해당 이미지의 숫자값을 2차 요청시 secureNo에 설정을 해야합니다.
	*** 따라서 클라이언트는 extraInfo.reqSecureNo의 이미지를 화면에 출력하고 엔드 유저에게 해당 이미지에 표현된 보안 숫자를 입력하게 해야 합니다.
	**** 보안 이미지의 문자 식별이 어려워 새로운 보안 이미지를 요청하려는 경우 extraInfo.reqSecureNoRefresh 값을 1로 설정한 뒤 요청을 하면 새로운 이미지 파일로 응답을 받을 수 있습니다.
	
		
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "f4656bba44e642c1975e2cf937fdd154"
	  },
	  "data" : {
	    "continue2Way" : true,						<-- 2Way 추가 요청 필요 여부(해당 값이 존재하고 true인 경우 2Way 방식이 진행중인 것으로 판단함)
	    "jobIndex" : 0,								<-- 2Way 정보(추가 요청 바디에 사용)
	    "threadIndex" : 0,							<-- 2Way 정보(추가 요청 바디에 사용)
	    "jti" : "5db280e0ec828b92fc9c1818",			<-- 2Way 정보(추가 요청 바디에 사용)
	    "twoWayTimestamp" : 1571979582409,			<-- 2Way 정보(추가 요청 바디에 사용)
	    "extraInfo" : {								<-- 2Way 정보(보안 이미지 문자 이미지 파일)
	      "reqSecureNoRefresh" : "0",
	      "reqSecureNo" : "data:img/jpeg;base64,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"
	    }
	  }
	}
	 */




	
	/**
	 * 3차 요청
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
	private void request3() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() +   CommonConstant.KR_IS_0001_002;
		
		// 요청 파라미터 설정 시작 - 1차 요청과 모두 동일한 값으로 설정
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("id", 		"user_id");				// 로그인 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 로그인패스워드
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(60~170)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(이메일 인증 문자)
		bodyMap.put("emailAuthNo", "067042");				// 엔드유저가 입력한  이메일 인증 문자
		
		// 추가 요청 파라미터 2	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "883293");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)		
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부
	
		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db280e0ec828b92fc9c1818");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571979582409L);			// long **
		
		bodyMap.put("twoWayInfo", twoWayInfoMap);						// 1차 요청의 응답으로 받은 2Way 정보 설정
		
		// 요청 파라미터 설정 종료
	
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
	
		// 응답결과 확인
		System.out.println(result);
	}
	
	/**
 	#3. 3차 요청
	[requestBody]	요청 바디
	{
		"organization":"0001",
		"userName":"홍길동",
		"identity":"8201231111111",
		"id":"user_id",
		"password":"iPLECaF5BGUTJ0vsiMQ3b9V9oKcPnjJvYOVNBzTi9OwsrAyyDY5fo23m94CiCvjaKO4c6CMhjb42pzM0Iovpu/ANNz+kvBGTcM1mhr6Foup+8jO6rK6j/4xU/hTj7RxXj65zcTPsMLdEK/QglDby4j/kJgfrjtJUzuXXa08GWFUC/46qJNWBkqe5o/fs5GeLqF933zxI98Z4wq7igsYeNP26kLo7rG0/EXe+0WqV74yq0fVQDRgrapLdAE1wrrxr5i9QONXHFs+YFR1lsunXVx+78mJu4vvsbIfnaFYioN9Hk1hwiJspG2bjNBvSRLJnjsZ+rsyxcSMZlD1esJvndg==",
		"email":"user_email@gmail.com",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"emailAuthNo":"027610",			<- 사용자 이메일로 전송된 인증 문자 입력
		
		"secureNo":"883293"				<- 보안 이미지 문자 입력
		"secureNoRefresh":"0",			<- 보안 이미지 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 2차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db280e0ec828b92fc9c1818",
						"twoWayTimestamp":1571979582409
					}
	}
	
	
	- 추가 요청의 파라미터는 2차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 보안 이미지 문자(secureNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	
	* 3차 요청에 대한 응답으로 extraInfo가 반환됩니다.
	** extraInfo.reqSMSAuthNo는 사용자가 선택한 인증방식에 따른 추가 입력 값을 의미합니다.(휴대폰 SMS 인증 문자)
	*** 신용카드 인증 방식의 경우 3차 응답으로 회원 가입이 완료됩니다.
	
	
		
	[responseBody]	응답 바디
	
	- SMS 인증 방식의 경우 :: 4차 요청이 필요함.
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "f4656bba44e642c1975e2cf937fdd154"
	  },
	  "data" : {
	    "continue2Way" : true,						<-- 2Way 추가 요청 필요 여부(해당 값이 존재하고 true인 경우 2Way 방식이 진행중인 것으로 판단함)
	    "jobIndex" : 0,								<-- 2Way 정보(추가 요청 바디에 사용)
	    "threadIndex" : 0,							<-- 2Way 정보(추가 요청 바디에 사용)
	    "jti" : "5db280e0ec828b92fc9c1818",			<-- 2Way 정보(추가 요청 바디에 사용)
	    "twoWayTimestamp" : 1571979642903,			<-- 2Way 정보(추가 요청 바디에 사용)
	    "extraInfo" : {
	      "reqSMSAuthNo" : ""						
	    }
	  }
	}
	
	-신용카드 인증 방식의 경우 :: 회원 가입 완료
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "",
	    "message" : "성공",
	    "transactionId" : "e52bdf4326b34cd6a02ee0bfa10de4a0"
	  },
	  "data" : {
	    "resRegistrationStatus" : "1",
	    "resResultDesc" : "",
	    "resLoginId" : "user_id"
	  }
	}
	 */
	
	
	
	/**
	 * 4차 요청
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
	private void request4() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() +  CommonConstant.KR_IS_0001_002;
		
		// 요청 파라미터 설정 시작 - 1차 요청과 모두 동일한 값으로 설정
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("id", 		"user_id");				// 로그인 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 로그인패스워드
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(60~170)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(이메일 인증 문자)
		bodyMap.put("emailAuthNo", "067042");				// 엔드유저가 입력한  이메일 인증 문자
		
		// 추가 요청 파라미터 2	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "883293");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)		
		
		// 추가 요청 파라미터 3 - 2Way 요청을 위한 추가 파라미터 설정(휴대폰 SMS 인증문자-SMS인증방식 선택한 경우에만 해당)
		bodyMap.put("smsAuthNo", "257553");					// 휴대폰 SMS 인증문자
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부
	
		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db280e0ec828b92fc9c1818");	// String
		twoWayInfoMap.put("twoWayTimestamp", 1571979642903L);			// long **
		
		bodyMap.put("twoWayInfo", twoWayInfoMap);						// 1차 요청의 응답으로 받은 2Way 정보 설정
		
		// 요청 파라미터 설정 종료
	
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);
	
		// 응답결과 확인
		System.out.println(result);
	}

}
/**
	#4. 4차 요청
	[requestBody]	요청 바디
	{
		"organization":"0001",
		"userName":"홍길동",
		"identity":"8201231111111",
		"id":"user_id",
		"password":"iPLECaF5BGUTJ0vsiMQ3b9V9oKcPnjJvYOVNBzTi9OwsrAyyDY5fo23m94CiCvjaKO4c6CMhjb42pzM0Iovpu/ANNz+kvBGTcM1mhr6Foup+8jO6rK6j/4xU/hTj7RxXj65zcTPsMLdEK/QglDby4j/kJgfrjtJUzuXXa08GWFUC/46qJNWBkqe5o/fs5GeLqF933zxI98Z4wq7igsYeNP26kLo7rG0/EXe+0WqV74yq0fVQDRgrapLdAE1wrrxr5i9QONXHFs+YFR1lsunXVx+78mJu4vvsbIfnaFYioN9Hk1hwiJspG2bjNBvSRLJnjsZ+rsyxcSMZlD1esJvndg==",
		"email":"user_email@gmail.com",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"emailAuthNo":"027610",			<- 사용자 이메일로 전송된 인증 문자 입력
		
		"secureNo":"883293",			<- 보안 이미지 문자 입력
		"secureNoRefresh":"0",			<- 보안 이미지 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		"smsAuthNo":"257553",			<- 휴대폰 SMS 인증 문자입력
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 2차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db280e0ec828b92fc9c1818",
						"twoWayTimestamp":1571979642903
					}
	}
	
	- 추가 요청의 파라미터는 3차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 휴대폰 SMS 인증 문자(smsAuthNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "",
	    "message" : "성공",
	    "transactionId" : "e52bdf4326b34cd6a02ee0bfa10de4a0"
	  },
	  "data" : {
	    "resRegistrationStatus" : "1",
	    "resResultDesc" : "",
	    "resLoginId" : "user_id"
	  }
	}

	
	- 2Way 방식은 추가 요청 처리까지 모두 정상 처리되어야 정상 응답 값을 받게 됩니다.

*/