# CODEF API - Java
Java sample for CODEF API

## Documentation

본 샘플은 CODEF API의 연동을 위한 공통 코드를 포함하고 있으며, 지원하는 모든 API의 엔드포인트(은행, 카드, 보험, 증권, 공공, 기타)는
https://develpers.codef.io 를 통해 확인할 수 있습니다.

## CODEF API Env

CODEF API는 원활한 개발을 위해 샌드박스, 개발, 운영 환경을 각각 제공한다.

- 샌드박스 : https://sandbox.codef.io
- 개발 : https://development.codef.io
- 운영 : https://api.codef.io

## Getting Started


### OAuth2.0

CODEF API를 사용하기 위해서는 'access_token' 발행이 선행되어야 하며, 거래 시 Header 에 포함하여 요청합니다.
'access_token'을 발급 받기 위한 'client_id' 및 'client_secret'은 https://codef.io/#/account/keys 에서 확인할 수 있습니다.

```java
String token_url = 'https://api.codef.io/oauth/token';
String access_token = RequestToken.getToken("CODEF로부터 발급받은 클라이언트 아이디", "CODEF로부터 발급받은 시크릿 키");
if (raccess_token != null){
	System.out.println(raccess_token);
}    
else{
	System.out.println('토큰발급 오류');
}	
```
```json
{
  "access_token" : "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXJ2aWNlX3R5cGUiOiIwIiwic2NvcGUiOlsicmVhZCJdLCJzZXJ2aWNlX25vIjoiMDAwMDAwMDQyMDAxIiwiZXhwIjoxNTYzOTQ4NDQ2LCJhdXRob3JpdGllcyI6WyJJTlNVUkFOQ0UiLCJQVUJMSUMiLCJCQU5LIiwiRVRDIiwiU1RPQ0siLCJDQVJEIl0sImp0aSI6Ijc4NDUyMjY4LWFkNDctNGVhNS04ZjljLTQ5ZWI5Yjk1YmQxZCIsImNsaWVudF9pZCI6ImNvZGVmX21hc3RlciJ9.ddZ38ARfTIa4_E8by6gITeIadhQKeDDG4YoGQdGiu-n2sJ1iQ7z81dsMJtc9-YYV-ItIcEn5OXqnIZlGaeF8Ya6Jqy6XdrIb8ou5Sq-jYoB6UXyQRzQsV_1oIIXYSeQtQKalSpPbGGOgLaXsm61fBKimFnnCd1anhxtZAIHwCLMbvQCZlwOeTls1F1EEOvQ76qcdUcmsw-LHM_9I68DwjIwAjyOTe4WPMhsK6KD4MryCAfZRAmdRhG6BWVKk_8D1JPFy42qQmILAr9LXOMODqnVaNeGA-izmtfX5KqqdYxAR6XV_7B9muzYPyGnBL_l2pEcLq5kVSL7YGtczwqB-AA",
  "scope" : "read",
  "token_type" : "bearer",
  "expires_in" : 604799
}
```


### 계정 생성

CODEF API를 사용하기 위해서는 엔드유저가 사용하는 대상기관의 인증수단 등록이 필요하며, 이를 통해 사용자마다 유니크한 'connected_id'를 발급받을 수 있습니다.
이후에는 별도의 인증수단 전송 없이 'connected_id'를 통해서 대상기관의 데이터를 연동할 수 있습니다.

```java
String urlPath = 'https://api.codef.io/v1/account/create';

HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
accountMap1.put("countryCode",	"KR");  // 국가코드
accountMap1.put("businessType",	"BK");  // 업무구분코드
accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
accountMap1.put("organization",	"0020");// 기관코드
accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)
accountMap1.put("password",     "INSERT YOUR END USER PASSWORD");
accountMap1.put("keyFile",      "INSERT YOUR END USER keyFile to BASE64 Encoding String");
accountMap1.put("derFile",      "INSERT YOUR END USER derFile to BASE64 Encoding String");
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

# CODEF API 호출
String result = ApiRequest.reqeust(urlPath, bodyMap);
```
```json
{
  "result" : {
    "code" : "CF-00000",
    "extraMessage" : "",
    "message" : "정상"
  },
  "data" : {
    "successList" : [ {
      "clientType" : "P",
      "code" : "CF-00000",
      "loginType" : "0",
      "countryCode" : "KR",
      "organization" : "0004",
      "businessType" : "BK",
      "message" : "성공"
    } ],
    "errorList" : [ ],
    "connectedId" : "45t4DJOD44M9uwH7zxSgBg"
  }
} 
```


### 계정 추가

계정 생성을 통해 발급받은 'connected_id'에 추가 기관의 인증수단을 등록할 수 있습니다. 추가 등록한 기관을 포함하여 이후에는 별도의 인증수단 전송없이
'connected_id'를 통해서 대상기관의 데이터를 연동할 수 있습니다.

```java
String urlPath = 'https://api.codef.io/v1/account/add';

HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
accountMap1.put("countryCode",	"KR");  // 국가코드
accountMap1.put("businessType",	"BK");  // 업무구분코드
accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
accountMap1.put("organization",	"0020");// 기관코드
accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)
accountMap1.put("password",  	"INSERT YOUR END USER PASSWORD");
accountMap1.put("keyFile",  	"INSERT YOUR END USER keyFile to BASE64 Encoding String");
accountMap1.put("derFile",  	"INSERT YOUR END USER derFile to BASE64 Encoding String");
list.add(accountMap1);

bodyMap.put("accountList", list);


# CODEF API 호출
String result = ApiRequest.reqeust(urlPath, bodyMap);
```
```json
{
  "result" : {
    "code" : "CF-00000",
    "extraMessage" : "",
    "message" : "정상"
  },
  "data" : {
    "successList" : [ {
      "clientType" : "P",
      "code" : "CF-00000",
      "loginType" : "0",
      "countryCode" : "KR",
      "organization" : "0020",
      "businessType" : "BK",
      "message" : "성공"
    } ],
    "errorList" : [ ],
    "connectedId" : "45t4DJOD44M9uwH7zxSgBg"
  }
}
```


### 계정 수정

계정 생성을 통해 발급받은 'connected_id'에 등록된 기관의 인증수단을 변경할 수 있습니다. 변경 요청한 기관의 인증 수단은 호출 즉시 변경되며, 이 후
'connected_id'를 통해서 대상기관의 데이터를 연동할 수 있습니다.

```java
String urlPath = 'https://api.codef.io/v1/account/update';

HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
accountMap1.put("countryCode",	"KR");  // 국가코드
accountMap1.put("businessType",	"BK");  // 업무구분코드
accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
accountMap1.put("organization",	"0020");// 기관코드
accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)
accountMap1.put("password",     "INSERT YOUR END USER PASSWORD");
accountMap1.put("keyFile",      "INSERT YOUR END USER keyFile to BASE64 Encoding String");
accountMap1.put("derFile",      "INSERT YOUR END USER derFile to BASE64 Encoding String");
list.add(accountMap1);

bodyMap.put("accountList", list);


# CODEF API 호출
String result = ApiRequest.reqeust(urlPath, bodyMap);
```
```json
{
  "result" : {
    "code" : "CF-00000",
    "extraMessage" : "",
    "message" : "정상"
  },
  "data" : {
    "successList" : [ {
      "clientType" : "P",
      "code" : "CF-00000",
      "loginType" : "0",
      "countryCode" : "KR",
      "organization" : "0020",
      "businessType" : "BK",
      "message" : "성공"
    } ],
    "errorList" : [ ],
    "connectedId" : "45t4DJOD44M9uwH7zxSgBg"
  }
}
```


### 계정 삭제

엔드유저가 등록된 계정의 삭제를 요청 시 'connected_id'에 등록된 기관의 인증수단을 즉시 삭제할 수 있습니다. 요청한 기관의 인증 수단은 호출 즉시 삭제되며,
해당 데이터는 복구할 수 없습니다.

```java
String urlPath = 'https://api.codef.io/v1/account/delete';

HashMap<String, Object> bodyMap = new HashMap<String, Object>();	
List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

HashMap<String, Object> accountMap1 = new HashMap<String, Object>();
accountMap1.put("countryCode",	"KR");  // 국가코드
accountMap1.put("businessType",	"BK");  // 업무구분코드
accountMap1.put("clientType",  	"P");   // 고객구분(P: 개인, B: 기업)
accountMap1.put("organization",	"0020");// 기관코드
accountMap1.put("loginType",  	"0");   // 로그인타입 (0: 인증서, 1: ID/PW)
list.add(accountMap1);

bodyMap.put("accountList", list);


# CODEF API 호출
String result = ApiRequest.reqeust(urlPath, bodyMap);
```
```json
{
  "result" : {
    "code" : "CF-00000",
    "extraMessage" : "",
    "message" : "정상"
  },
  "data" : {
    "successList" : [ {
      "clientType" : "P",
      "loginType" : "0",
      "countryCode" : "KR",
      "organization" : "0020",
      "businessType" : "BK"
    } ],
    "connectedId" : "45t4DJOD44M9uwH7zxSgBg"
  }
}
```




### CODEF API(법인 보유계좌조회)

발급받은 'connected_id' 를 통해 등록된 기관의 보유계좌를 조회할 수 있습니다.

TestKR_BK_1_B_001.java
```java
// 요청 URL 설정
String urlPath = CommonConstant.API_DOMAIN + CommonConstant.KR_BK_1_B_001;

// 요청 파라미터 설정 시작
HashMap<String, Object> bodyMap = new HashMap<String, Object>();
bodyMap.put("connectedId", 	"9LUm.uhVQbzaangazwI0tr");	// 엔드유저의 은행/카드사 계정 등록 후 발급받은 커넥티드아이디 예시
bodyMap.put("organization", "기관코드"); 					
// 요청 파라미터 설정 종료 

// API 요청
String result = ApiRequest.reqeust(urlPath, bodyMap);

// 응답결과 확인
System.out.println(result);
```
```json
{
	"result": {
		"code": "CF-00000",
		"extraMessage": "",
		"message": "성공",
		"transactionId": "aedb0cade44f44e48a5f3181c2fe9a96"
	},
	"data": {
		"resAccountEndDate": "",
		"resLoanLimitAmt": "",
		"resWithdrawalAmt": "19615",
		"resAccount": "05300000004040",
		"resAccountStatus": "활동",
		"resLoanEndDate": "",
		"commEndDate": "20190630",
		"resInterestRate": "",
		"resAccountName": "OO기업자유예금",
		"resAccountStartDate": "20130605",
		"resAccountCurrency": "KRW",
		"resAccountBalance": "19615",
		"commStartDate": "20190401",
		"resTrHistoryList": [
			{
				"resAccountTrTime": "095900",
				"resAccountDesc4": "",
				"resAccountDesc3": "ｒｎｄ＿오전",
				"resAccountDesc2": "인터넷",
				"resAccountDesc1": "（주）OO",
				"resAccountTrDate": "20190628",
				"resAccountOut": "1",
				"resAfterTranBalance": "13634",
				"resAccountIn": "0"
			},
			{
				"resAccountTrTime": "174603",
				"resAccountDesc4": "",
				"resAccountDesc3": "rnd_입금표시1",
				"resAccountDesc2": "타행이체",
				"resAccountDesc1": "OO（주）",
				"resAccountTrDate": "20190627",
				"resAccountOut": "0",
				"resAfterTranBalance": "13635",
				"resAccountIn": "1"
			},
			{
				"resAccountTrTime": "164618",
				"resAccountDesc4": "",
				"resAccountDesc3": "rnd_입금표시1",
				"resAccountDesc2": "타행이체",
				"resAccountDesc1": "OO（주）",
				"resAccountTrDate": "20190627",
				"resAccountOut": "0",
				"resAfterTranBalance": "13634",
				"resAccountIn": "1"
			},
			{
				"resAccountTrTime": "092130",
				"resAccountDesc4": "",
				"resAccountDesc3": "ｒｎｄ＿오전",
				"resAccountDesc2": "인터넷",
				"resAccountDesc1": "（주）OO",
				"resAccountTrDate": "20190627",
				"resAccountOut": "1",
				"resAfterTranBalance": "13633",
				"resAccountIn": "0"
			}
		],
		"resAccountHolder": "(주)OO",
		"resManagementBranch": "(0044)북아현동",
		"resLastTranDate": "20190711"
	}
}
```


### 오류

CODEF API 오류는 HTTP status code 와 CODEF API ErrorCode로 분류합니다.

HTTP 401 - OAuth2.0 토큰 만료
```json
{"error":"invalid_token","error_description":"Cannot convert access token to JSON","code":"CF-99997","message":"OAUTH2.0 토큰 에러입니다. 메시지를 확인하세요."}
```

그 외 오류 HTTP 200 - CODEF 오류 변환(CF-XXXXX)
```json
{"result":{"code":"CF-94002","extraMessage":"","message":"사용자 계정정보 설정에 실패했습니다."},"data":{}}
```


## Change Log

CODEF API의 변경내을 [CHANGELOG.md](CHANGELOG.md) 을 통해 확인할 수 있습니다.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
