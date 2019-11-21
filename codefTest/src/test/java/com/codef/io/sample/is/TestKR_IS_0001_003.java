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
 *  한국신용정보원 > 보험다보여 아이디찾기 2Way 방식 안내
 *
 */
public class TestKR_IS_0001_003 {
	
	/**
	 * :: 한국신용정보원 > 보험다보여 아이디찾기는 2Way인증을 통한 추가 정보 입력이 진행되어야 정상적인 상품 요청이 완료됩니다.
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
	}
	
	
	/**
	 * 한국신용정보원 > 보험다보여 아이디찾기 1차 요청
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_003;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
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
	** extraInfo.reqSecureNo는 보안 이미지 문자 이미지 파일 데이터로서 해당 이미지의 숫자값을 2차 요청시 secureNo에 설정을 해야합니다.
	*** 따라서 클라이언트는 extraInfo.reqSecureNo의 이미지를 화면에 출력하고 엔드 유저에게 해당 이미지에 표현된 보안 숫자를 입력하게 해야 합니다.
	**** 보안 이미지의 문자 식별이 어려워 새로운 보안 이미지를 요청하려는 경우 extraInfo.reqSecureNoRefresh 값을 1로 설정한 뒤 요청을 하면 새로운 이미지 파일로 응답을 받을 수 있습니다.
	
	[responseBody]	응답 바디
	{
	  "result" : {
	    "code" : "CF-00000",
	    "extraMessage" : "API 요청 처리가 정상 진행 중입니다. 추가 정보를 입력하세요.",
	    "message" : "성공",
	    "transactionId" : "72594965d35c4475a978f7b6c68f2699"
	  },
	  "data" : {
	    "continue2Way" : true,
	    "jobIndex" : 0,
	    "threadIndex" : 0,
	    "jti" : "5db298a2ec8219024801f48b",
	    "twoWayTimestamp" : 1571985436474,
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_003;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "15902");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부

		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db298a2ec8219024801f48b");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571985436474L);			// long **
		
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
		
		"secureNo":"883293"				<- 보안 이미지 문자 입력
		"secureNoRefresh":"0",			<- 보안 이미지 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 1차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db298a2ec8219024801f48b",
						"twoWayTimestamp":1571985436474
					}
	}
	
	
	- 추가 요청의 파라미터는 1차 요청과 동일한 기본 요청 파라미터에 엔드 유저가 입력한 보안 이미지 문자(secureNo) 값이 추가됩니다.
	- 그리고 2Way의 추가 요청임을 서버에 알리기 위해 is2Way의 값으로 true를 설정합니다.
	- 마지막으로 1차 응답 바디로 전송받은 2Way 정보를 twoWayInfo의 값으로 설정해 추가 요청 파라미터로 사용합니다.
	- 이중 twoWayTimestamp값은 Long타입임을 주의해야 합니다.
	
	
	* 2차 요청에 대한 응답으로 extraInfo가 반환됩니다.
	** extraInfo.reqSMSAuthNo는 사용자가 선택한 인증방식에 따른 추가 입력 값을 의미합니다.(휴대폰 SMS 인증 문자)
	*** 신용카드 인증 방식의 경우 2차 응답으로 상품 요청이 완료됩니다.
	
		
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
	    "jti" : "5db298a2ec8219024801f48b",			<-- 2Way 정보(추가 요청 바디에 사용)
	    "twoWayTimestamp" : 1571985626053,			<-- 2Way 정보(추가 요청 바디에 사용)
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
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_IS_0001_003;
		
		// 요청 파라미터 설정 시작 - 1차 요청과 모두 동일한 값으로 설정
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "0001");				// 기관코드(고정값)
		 
		bodyMap.put("userName", "홍길동");					// 사용자 이름	
		bodyMap.put("identity", "8201231111111");			// 사용자 주민번호/사업자 번호
		bodyMap.put("email", 	"user_email@gmail.com");		// 이메일
		
		bodyMap.put("inquiryType", "0");					// 조회구분  (0:휴대폰, 1:신용카드, default:0)				
		
		// 휴대폰인증 파라미터 (조회구분에 따라 휴대폰과 신용카드 정보 선택 입력)
		bodyMap.put("telecom", "0");						// 통신사
		bodyMap.put("phoneNo", "01033339999");				// 전화번호
		bodyMap.put("timeout", "160");					 	// 제한시간(30~170, default : 60)
		
		// 추가 요청 파라미터 1	- 2Way 요청을 위한 추가 파라미터 설정(보안 이미지 문자)
		bodyMap.put("secureNo", "883293");					// 엔드유저가 입력한  보안 이미지 문자
		bodyMap.put("secureNoRefresh", "0");				// 보안 이미지 문자 새로고침 ("0": 기본, "1": refresh, "2" : 입력취소, default:0)
		
		// 추가 요청 파라미터 2 - 2Way 요청을 위한 추가 파라미터 설정(휴대폰 SMS 인증문자-SMS인증방식 선택한 경우에만 해당)
		bodyMap.put("smsAuthNo", "925219");					// 휴대폰 SMS 인증문자
		
		// 2Way 파라미터   - 2Way 요청을 위한 2Way기본 정보 설정
		bodyMap.put("is2Way", true);			// 추가 요청 여부
	
		HashMap<String, Object> twoWayInfoMap = new HashMap<String, Object>();
		twoWayInfoMap.put("jobIndex", 0);								// int 
		twoWayInfoMap.put("threadIndex", 0);							// int
		twoWayInfoMap.put("jti", "5db298a2ec8219024801f48b");			// String
		twoWayInfoMap.put("twoWayTimestamp", 1571985626053L);			// long **
		
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
		
		"secureNoRefresh":"0",			<- 보안 이미지 문자 입력
		"secureNo":"883293"				<- 보안 이미지 문자 입력
		
		"smsAuthNo":"257553",			<- 휴대폰 SMS 인증 문자입력
		
		"is2Way":true					<-- 추가 요청임을 알리는 설정값
		"twoWayInfo":{					<-- 2차 요청의 응답으로 받은 2way 설정 값을 그대로 요청 정보로 설정
						"jobIndex":0,
						"threadIndex":0,
						"jti":"5db298a2ec8219024801f48b",
						"twoWayTimestamp":1571985626053
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
	    "extraMessage" : "",
	    "message" : "성공",
	    "transactionId" : "f72b6f860c364003b368a5778c6d7aed"
	  },
	  "data" : {
	    "resRegistrationStatus" : "1",	<- [처리결과] "0" :false , "1" :true
	    "resResultDesc" : ""
	  }
	}
	
	*** 입력한 이메일로 아이디가 발송되며 응답 결과에서 아이디 확인은 불가합니다. 처리 결과만 확인 가능합니다.
	
	 */
	
	
}
