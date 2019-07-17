package com.codef.io.sample.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
 * 
 * @author daejongkwak
 *
 */
public class HttpRequest {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static Object post(String url_path, String token, String bodyString) {
		try {
			System.out.println("bodyString : " + URLDecoder.decode(bodyString, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			URL url = new URL(url_path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

			if (token != null) {
				con.setRequestProperty("Authorization", "Bearer " + token);		// token 설정
			}

			System.out.println("");
			System.out.println("token : " + token);
			System.out.println("[" + sdf.format(new Date()) + "]	REQUEST_STRING : " + bodyString);

			OutputStream os = con.getOutputStream();
			os.write(bodyString.getBytes());
			os.flush();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.println("responseCode : " + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			System.out.println("[" + sdf.format(new Date()) + "]	RESPONSE_STRING : " + URLDecoder.decode(response.toString(), "UTF-8"));

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(URLDecoder.decode(response.toString(), "UTF-8"));

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
