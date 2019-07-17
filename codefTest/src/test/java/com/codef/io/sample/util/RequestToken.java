package com.codef.io.sample.util;

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


public class RequestToken {

	/**
	 * 토큰 발급 요청 (클라이언트 인증 플로우 (Client Credentials flow))
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException 
	 */
	public static String getToken(String clientId, String secretKey) throws IOException, InterruptedException, ParseException {
		URL url = new URL(CommonConstant.TOKEN_DOMAIN + "/oauth/token");

		String POST_PARAMS = "grant_type=client_credentials&scope=read";
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		con.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		String auth = clientId + ":" + secretKey;
		byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
		String authStringEnc = new String(authEncBytes);
		String authHeader = "Basic " + authStringEnc;
		System.out.println("[getToken] Authorization :: " + authHeader);

		con.setRequestProperty("Authorization", authHeader);
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("[getToken] POST Response Code :: " + responseCode + "	message ::" + con.getResponseMessage());

		if (responseCode != HttpURLConnection.HTTP_OK) { // success
			System.out.println("[getToken] POST request not worked");
			return null;
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			String result = response.toString();
			System.out.println("[getToken] result	:: " + result);

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(URLDecoder.decode(response.toString(), "UTF-8"));
			JSONObject tokenJson = (JSONObject)obj;
			
			// 토큰 확인
			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tokenJson));
			
			return tokenJson.get("access_token").toString(); 
		}
	}
}
