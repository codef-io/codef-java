package com.codef.io.sandbox;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.codef.io.util.CommonConstant;
import com.codef.io.util.HttpRequest;
import com.codef.io.util.RequestToken;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  API 요청 템플릿 클래스
 */
public class SandboxApiRequest {

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String reqeust(String urlPath, HashMap<String, Object> bodyMap) throws IOException, InterruptedException, ParseException {
		
		// 리소스서버 접근을 위한 액세스토큰 설정(기존에 발급 받은 토큰이 있다면 유효기간 만료까지 재사용)
		String accessToken = CommonConstant.ACCESS_TOKEN;								
		if(StringUtils.isEmpty(accessToken)) {	// 액세스토큰을 발급받은 적이 없다면 토큰 발급 요청 수행
			System.out.println("====    토큰 발급 요청 수행");
			accessToken = RequestToken.getToken(CommonConstant.SANDBOX_CLIENT_ID, CommonConstant.SANDBOX_SECERET_KEY);
			CommonConstant.ACCESS_TOKEN = accessToken;	// 재사용을 위한 발급받은 액세스 토큰 저장
		}
		
		// POST요청을 위한 리퀘스트바디 생성(UTF-8 인코딩)
		String bodyString = mapper.writeValueAsString(bodyMap);	
		bodyString = URLEncoder.encode(bodyString, "UTF-8");
		
		// API 요청
		System.out.println("====    API 요청 : " + urlPath);
		JSONObject json = (JSONObject)HttpRequest.post(urlPath, accessToken, bodyString);

		System.out.println("====    API 요청 결과 확인");
		String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		System.out.println(result);
		
		// 액세스 토큰 유효기간 만료시
		if("invalid_token".equals(json.get("error"))) {
			System.out.println("====    유효하지 않은 토큰인 경우 토큰 재발급 요청");
			// 토큰 재발급 요청 수행
			accessToken = RequestToken.getToken(CommonConstant.SANDBOX_CLIENT_ID, CommonConstant.SANDBOX_SECERET_KEY);
			CommonConstant.ACCESS_TOKEN = accessToken;	// 재사용을 위한 발급받은 액세스 토큰 저장
		
			// API 재요청
			System.out.println("====    API 요청 : " + urlPath);
			json = (JSONObject)HttpRequest.post(urlPath, accessToken, bodyString);
			
			System.out.println("====    API 요청 결과 확인");
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
			System.out.println(result);
		} else if("access_denied".equals(json.get("error"))) {
			System.out.println("access_denied은 API 접근 권한이 없는 경우입니다.");
			System.out.println("코드에프 대시보드의 API 설정을 통해 해당 업무 접근 권한을 설정해야 합니다.");
		}
		
		return result;
	}
}
