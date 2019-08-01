package com.codef.io.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 토큰 발급 요청 처리 클래스
 */
public class RequestToken {

	/**
	 * 토큰 발급 요청 (클라이언트 인증 플로우 (Client Credentials flow))
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException 
	 */
	public static String getToken(String clientId, String secretKey) throws IOException, InterruptedException, ParseException {
		try {
			// HTTP 요청을 위한 URL 오브젝트 생성
			URL url = new URL(CommonConstant.TOKEN_DOMAIN + CommonConstant.GET_TOKEN);
	
			String POST_PARAMS = "grant_type=client_credentials&scope=read";	// Oauth2.0 사용자 자격증명 방식(client_credentials) 토큰 요청 설정
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			
			// 클라이언트아이디, 시크릿코드 Base64 인코딩
			String auth = clientId + ":" + secretKey;
			byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
			String authStringEnc = new String(authEncBytes);
			String authHeader = "Basic " + authStringEnc;
			
			System.out.println("Authorization :: " + authHeader);
	
			con.setRequestProperty("Authorization", authHeader);
			con.setDoOutput(true);
			
			// 리퀘스트 바디 전송
			OutputStream os = con.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
	
			// 응답 코드 확인
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode + "	message ::" + con.getResponseMessage());
	
			BufferedReader br;
			if (responseCode == HttpURLConnection.HTTP_OK) {	// 정상 응답
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {	 // 에러 발생
				System.out.println("POST request not worked");
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 응답 바디 read
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			// 응답 문자열 확인
			System.out.println("RESPONSE_STRING : " + URLDecoder.decode(response.toString(), "UTF-8"));
			
			// 응답 문자열 인코딩, JSONObject 변환
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(URLDecoder.decode(response.toString(), "UTF-8"));
			JSONObject tokenJson = (JSONObject)obj;
			
			// 토큰 확인
			System.out.println("access_token : " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tokenJson));
			
			// 토큰 반환
			return tokenJson.get("access_token").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
