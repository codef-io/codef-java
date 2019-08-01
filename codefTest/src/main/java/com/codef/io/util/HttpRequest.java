package com.codef.io.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * HTTP 호출을 위한 재사용 클래스
 */
public class HttpRequest {

	public static Object post(String url_path, String token, String bodyString) {
		try {
			// HTTP 요청을 위한 URL 오브젝트 생성
			URL url = new URL(url_path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			con.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

			if (token != null) {
				con.setRequestProperty("Authorization", "Bearer " + token);		// 엑세스 토큰 헤더 설정
			}

			// 리퀘스트 바디 전송
			OutputStream os = con.getOutputStream();
			os.write(bodyString.getBytes());
			os.flush();
			os.close();

			// 응답 코드 확인
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode + "	message ::" + con.getResponseMessage());

			BufferedReader br;
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
				br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			} else { // 에러 발생
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
//			System.out.println("RESPONSE_STRING : " + URLDecoder.decode(response.toString(), "UTF-8"));

			// 응답 문자열 인코딩, JSONObject 변환
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(URLDecoder.decode(response.toString(), "UTF-8"));

			// 결과 반환
			return obj;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
