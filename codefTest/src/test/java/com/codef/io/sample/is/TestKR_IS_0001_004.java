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
 *  한국신용정보원 > 보험다보여 비밀번호 변경 2Way 방식 안내
 *
 */
public class TestKR_IS_0001_004 {
	
	/**
	 * :: 한국신용정보원 > 보험다보여 비밀번호 변경는 2Way인증을 통한 추가 정보 입력이 진행되어야 정상적인 상품 요청이 완료됩니다.
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
	 * 
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
		
//		request2();	// 2차 : 기본 요청 + 보안문자
		
//		request3();	// 3차 : 기본 요청 + 보안문자 + 휴대폰SMS 인증
		
//		request4();	// 4차 : 기본 요청 + 보안문자 + 휴대폰SMS 인증 + 이메일 임시번호 입력
	}
	
	
	/**
	 * 한국신용정보원 > 보험다보여 비밀번호 변경 1차 요청
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_004;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("id", "user_id");					// 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 변경할 비밀번호
		
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
		"email":"user_email@gmail.com",
		"id":"user_id",
		"password":"ge+mBcSe+4OEFNeMlRwu3mHNwesi0a3jI8T3AZJDiJbZ9Ho6zQmjb/HsOQ8gcEJ4CjT/ddm1PE5NPdTlbPcLQP1krpV9EOK9tvYYWOdGSuTHr9p0Tyng8kWNp6h7EEdPh2jViHyBw1XKbKacDIsMIJ5cLExA2NQ0AvcUnIYupEPTpUrOwzw+psSqteLNG2jC130sOweROArGqiDtVVUWyuZui9IiUjx9WZ+qrTMzWjutufGqEkeJ3pdsIu3LZ0JSdZNqrjPSxH/OtY0Zbcy32Zbf2tcgdR4KVCRRFb9+uP64FkC8HyAHFIOKZr8tDYNFs+KqExxfFwyIQrKyroDYlA==",
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
	** extraInfo.reqSecureNo는 보안 이미지 문자 이미지 파일 데이터로서 해당 이미지의 숫자값을 2차 요청시 secureNo에 설정을 해야합니다.
	*** 따라서 클라이언트는 extraInfo.reqSecureNo의 이미지를 화면에 출력하고 엔드 유저에게 해당 이미지에 표현된 보안 숫자를 입력하게 해야 합니다.
	**** 보안 이미지의 문자 식별이 어려워 새로운 보안 이미지를 요청하려는 경우 extraInfo.reqSecureNoRefresh 값을 1로 설정한 뒤 요청을 하면 새로운 이미지 파일로 응답을 받을 수 있습니다.
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "ec4eebd25daa4e11815880f4ade57727"
	  },
	  "data" : {
	    "continue2Way" : true,
	    "jobIndex" : 0,
	    "threadIndex" : 0,
	    "jti" : "5db29eebec828b92fc9c1826",
	    "twoWayTimestamp" : 1571987180939,
	    "extraInfo" : {
	      "reqSecureNoRefresh" : "0",
	      "reqSecureNo" : "data:img/jpeg;base64,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"
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_004;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("id", "user_id");					// 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 변경할 비밀번호
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "1964");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부

		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db29eebec828b92fc9c1826");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571987180939L);			// long **
		
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
		"email":"user_email@gmail.com",
		"id":"user_id",
		"password":"ge+mBcSe+4OEFNeMlRwu3mHNwesi0a3jI8T3AZJDiJbZ9Ho6zQmjb/HsOQ8gcEJ4CjT/ddm1PE5NPdTlbPcLQP1krpV9EOK9tvYYWOdGSuTHr9p0Tyng8kWNp6h7EEdPh2jViHyBw1XKbKacDIsMIJ5cLExA2NQ0AvcUnIYupEPTpUrOwzw+psSqteLNG2jC130sOweROArGqiDtVVUWyuZui9IiUjx9WZ+qrTMzWjutufGqEkeJ3pdsIu3LZ0JSdZNqrjPSxH/OtY0Zbcy32Zbf2tcgdR4KVCRRFb9+uP64FkC8HyAHFIOKZr8tDYNFs+KqExxfFwyIQrKyroDYlA==",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"secureNo":"1964"				<- 보안 이미지 문자 입력
		"secureNoRefresh":"0",			<- 보안 이미지 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 1차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db29eebec828b92fc9c1826",
						"twoWayTimestamp":1571987180939
					}
	}
	
	
	- 추가 요청의 파라미터는 1차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 보안 이미지 문자(secureNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	
	* 2차 요청에 대한 응답으로 extraInfo가 반환됩니다.
	** extraInfo.reqSMSAuthNo는 사용자가 선택한 인증방식에 따른 추가 입력 값을 의미합니다.(휴대폰 SMS 인증 문자)
	
	*** 신용카드 인증 방식의 경우 3차 요청을 건너 뛰어 4차 요청으로 넘어갑니다.(이메일 임시비밀번호 입력)  
	
		
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
	    "jti" : "5db29eebec828b92fc9c1826",			<-- 2Way 정보(추가 요청 바디에 사용)
	    "twoWayTimestamp" : 1571987238437,			<-- 2Way 정보(추가 요청 바디에 사용)
	    "extraInfo" : {
	      "reqSMSAuthNo" : ""						
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_004;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("id", "user_id");					// 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 변경할 비밀번호
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "1964");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		// 추가 요청 파라미터 2 - 2Way 요청을 위한 추가 파라미터 설정(휴대폰 SMS 인증문자-SMS인증방식 선택한 경우에만 해당)
		bodyMap.put("smsAuthNo", "415123");					// 휴대폰 SMS 인증문자
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부
	
		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db29eebec828b92fc9c1826");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571987238437L);			// long **
		
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
		"email":"user_email@gmail.com",
		"id":"user_id",
		"password":"ge+mBcSe+4OEFNeMlRwu3mHNwesi0a3jI8T3AZJDiJbZ9Ho6zQmjb/HsOQ8gcEJ4CjT/ddm1PE5NPdTlbPcLQP1krpV9EOK9tvYYWOdGSuTHr9p0Tyng8kWNp6h7EEdPh2jViHyBw1XKbKacDIsMIJ5cLExA2NQ0AvcUnIYupEPTpUrOwzw+psSqteLNG2jC130sOweROArGqiDtVVUWyuZui9IiUjx9WZ+qrTMzWjutufGqEkeJ3pdsIu3LZ0JSdZNqrjPSxH/OtY0Zbcy32Zbf2tcgdR4KVCRRFb9+uP64FkC8HyAHFIOKZr8tDYNFs+KqExxfFwyIQrKyroDYlA==",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"secureNoRefresh":"0",			<- 보안 이미지 문자 입력
		"secureNo":"883293"				<- 보안 이미지 문자 입력
		
		"smsAuthNo":"415123",			<- 휴대폰 SMS 인증 문자입력
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 2차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db29eebec828b92fc9c1826",
						"twoWayTimestamp":1571987238437
					}
	}
	
	
	- 추가 요청의 파라미터는 2차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 휴대폰 SMS 인증 문자(smsAuthNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "423a7edb4e0847bfbe408ed7991cb0fe"
	  },
	  "data" : {
	    "continue2Way" : true,
	    "jobIndex" : 0,
	    "threadIndex" : 0,
	    "jti" : "5db29eebec828b92fc9c1826",
	    "twoWayTimestamp" : 1571987280552,
	    "extraInfo" : {
	      "reqUserPass1" : ""
	    }
	  }
	}
	
	*** 입력한 이메일로 임시 비밀번호가 발송됩니다. 사용자는 해당 임시 비밀번호를 다음 요청에 추가 입력해야 합니다.
	
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_004;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("id", "user_id");					// 아이디
		bodyMap.put("password",	RSAUtil.encryptRSA("user_password", CommonConstant.PUBLIC_KEY) );	//	 변경할 비밀번호
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "1964");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		// 추가 요청 파라미터 2 - 2Way 요청을 위한 추가 파라미터 설정(휴대폰 SMS 인증문자-SMS인증방식 선택한 경우에만 해당)
		bodyMap.put("smsAuthNo", "415123");					// 휴대폰 SMS 인증문자
		
		// 추가 요청 파라미터 3 - 2Way 요청을 위한 추가 파라미터 설정(이메일 임시비밀번호)
		bodyMap.put("password1", RSAUtil.encryptRSA("7M33dZ5S!", CommonConstant.PUBLIC_KEY));	// 이메일 수신 임시비밀번호
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부
		
		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db29eebec828b92fc9c1826");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571987280552L);			// long **
		
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
		"email":"user_email@gmail.com",
		"id":"user_id",
		"password":"ge+mBcSe+4OEFNeMlRwu3mHNwesi0a3jI8T3AZJDiJbZ9Ho6zQmjb/HsOQ8gcEJ4CjT/ddm1PE5NPdTlbPcLQP1krpV9EOK9tvYYWOdGSuTHr9p0Tyng8kWNp6h7EEdPh2jViHyBw1XKbKacDIsMIJ5cLExA2NQ0AvcUnIYupEPTpUrOwzw+psSqteLNG2jC130sOweROArGqiDtVVUWyuZui9IiUjx9WZ+qrTMzWjutufGqEkeJ3pdsIu3LZ0JSdZNqrjPSxH/OtY0Zbcy32Zbf2tcgdR4KVCRRFb9+uP64FkC8HyAHFIOKZr8tDYNFs+KqExxfFwyIQrKyroDYlA==",
		"inquiryType":"0",
		"telecom":"0",
		"phoneNo":"01033339999",
		"timeout":"160"
		
		"secureNoRefresh":"0",			<- 보안 이미지 문자 입력
		"secureNo":"883293"				<- 보안 이미지 문자 입력
		
		"smsAuthNo":"257553",			<- 휴대폰 SMS 인증 문자입력
		
		"password1":"ge+mBcSe+4OEFNeMlRwu3mHNwesi0a3jI8T3AZJDiJbZ9Ho6zQmjb/HsOQ8gcEJ4CjT/d",	<- 이메일 수신 임시비밀번호 RSA 암호화
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 2차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db29eebec828b92fc9c1826",
						"twoWayTimestamp":1571987280552
					}
	}
	
	
	- 추가 요청의 파라미터는 3차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 이메일 수신 임시비밀번호(password1) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "",
	    "message" : "성공",
	    "transactionId" : "b479bf71cd724985b795ddca9cfaf982"
	  },
	  "data" : {
	    "resRegistrationStatus" : "1",	<- [처리결과] "0" :false , "1" :true (비밀번호 변경 성공), "2" : 임시비밀번호 발급 성공 (변경은 못함)
	    "resResultDesc" : ""
	  }
	}
	
	
	
	 */
	
	
}
