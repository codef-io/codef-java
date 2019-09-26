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
 * 공공 부동산등기 
 */
public class TestKR_PB_A_002 {
	
	@Test
	public void testKR_PB_A_002() throws IOException, InterruptedException, ParseException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// 요청 URL 설정
		String urlPath = CommonConstant.getRequestDomain() + CommonConstant.KR_PB_CK_001;
		
		// 요청 파라미터 설정 시작
		HashMap<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("organization", "기관코드"); 
		
		bodyMap.put("phoneNo", "전화번호");	
		bodyMap.put("password", RSAUtil.encryptRSA("비밀번호", CommonConstant.PUBLIC_KEY));
		bodyMap.put("inquiryType", "조회구분");			// 0:고유번호로 찾기, 1:간편검색, 2:소재지번으로 찾기, 3:도로명주소로 찾기
		bodyMap.put("uniqueNo", "부동산 고유번호");		// 조회구분="0" (고유번호찾기) 인 경우 필수
		
		bodyMap.put("realtyType", "부동산구분");		// 조회구분=="1" or "2" or "3",  [0:토지+건물, 1: 집합건물, 2:토지, 3:건물], 조회구분="1"인 경우 미입력시 전체
		bodyMap.put("addr_sido", "주소_시/도");			// 조회구분=="1" or "2" or "3", 조회구분="1"인 경우 미입력시 전체
		bodyMap.put("address", "주소");					// 조회구분=="1" (간편검색)
		bodyMap.put("recordStatus", "등기기록상태");	// 조회구분=="1" (간편검색),  [0:현행, 1.폐쇄, 2:현행+폐쇄, default="0"]
		
		bodyMap.put("addr_dong", "주소_읍면동로");		// 조회구분=="2" (소재지번), 리/동
		bodyMap.put("addr_lotNumber", "주소_지번");		// 조회구분=="2" (소재지번)
		bodyMap.put("inputSelect", "입력선택");			// 조회구분=="2" (소재지번)이고 부동산구분="1" (집합건물)인 경우 필수,  [0:지번, 1:건물명칭]
		bodyMap.put("buildingName", "건물명칭");		// 조회구분=="2" (소재지번)
		
		bodyMap.put("dong", "동");						// 부동산구분=="1"(집합건물)인 경우, "동" or "호" 둘 중 하나 이상 필수
		bodyMap.put("ho", "호");						// 부동산구분=="1"(집합건물)인 경우, "동" or "호" 둘 중 하나 이상 필수
		
		bodyMap.put("addr_sigungu", "주소_시군구");		// 조회구분=="3" (도로명주소), 시/군/구
		bodyMap.put("addr_roadName", "주소_도로명");	// 조회구분=="3" (도로명주소)
		bodyMap.put("addr_buildingNumber", "주소_건물번호(도로명)");	// 조회구분="3" (도로명주소)
		
		bodyMap.put("jointMortgageJeonseYN", "공동담보/전세목록 포함여부");	// 0:미포함, 1:포함 (default="0")
		bodyMap.put("tradingYN", "매매목록 포함 여부");	// 0:미포함, 1:포함 (default="0")
		bodyMap.put("listNumber", "목록번호");			// default: 목록전체, 다건 선택시 "|" 로구분,  ex) "2007123|2017134"
		bodyMap.put("electronicClosedYN", "전산폐쇠조회 여부");	// 조회구분=="2" or "3", [0:미포함, 1:포함 (default="0")]
		
		bodyMap.put("ePrepayNo", "선불전자지급수단 번호");		
		bodyMap.put("ePrepayPass", "선불전자지급수단 비밀번호");	
		
		bodyMap.put("issueType", "발행구분");			// "0":발급, "1":열람, "2":고유번호조회, "3": 원문데이타로 결과처리 (default : "0")
		bodyMap.put("startPageNo", "시작페이지번호");	// 조회구분=="1" (간편검색), 미입력시 첫페이지 부터 조회
		bodyMap.put("pageCount", "조회페이지수");		// 조회구분=="1" (간편검색), (default=100)
		bodyMap.put("originData", "원문Data");			// 발행구분=="3"인 경우 필수 
		
		bodyMap.put("originDataYN", "원문Data 포함 여부");	// 0:미포함, 1:포함 (default: 0)
		bodyMap.put("warningSkipYN", "경고 무시 여부");		// 0:실행 취소, 1:무시(실행 진행) (default: 0)
		bodyMap.put("registerSummaryYN", "등기사항요약 출력 여부");	// 0:미출력, 1:출력 (default: 0)
		bodyMap.put("applicationType", "신청구분");		// 부동산구분="1"(집합건물)인 경우, 검색결과에서 (0:전유 제외, 1:전유 포함, (default=0))
		// 요청 파라미터 설정 종료
		
		// API 요청
		String result = ApiRequest.reqeust(urlPath, bodyMap);

		// 응답결과 확인
		System.out.println(result);
	}
}