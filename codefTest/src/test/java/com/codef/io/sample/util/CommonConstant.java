package com.codef.io.sample.util;

public class CommonConstant {
	
	public static final String TEST_DOMAIN 	= "http://192.168.10.126:8101";										// API서버 테스트 도메인		
//	public static final String TEST_DOMAIN 	= "https://tapi.codef.io";										// API서버 테스트 도메인		
	public static final String TOKEN_DOMAIN = "https://toauth.codef.io";									// OAUTH2.0 테스트 도메인
	
	public static final String CONNECTED_ID = "connectedId";												// 유저 식별 연결 아이디
	                                                                                                        
	public static final String KR_BK_1_B_001	= "/v1/kr/bank/b/account/account-list";                     // 은행 법인 보유계좌
	public static final String KR_BK_1_B_002	= "/v1/kr/bank/b/account/transaction-list";                 // 은행 법인 수시입출 거래내역
	public static final String KR_BK_1_B_003	= "/v1/kr/bank/b/installment-savings/transaction-list";     // 은행 법인 적금 거래내역
	public static final String KR_BK_1_B_004	= "/v1/kr/bank/b/loan/transaction-list";                    // 은행 법인 대출 거래내역
	public static final String KR_BK_1_B_005	= "/v1/kr/bank/b/exchange/transaction-list";                // 은행 법인 외화 거래내역
	public static final String KR_BK_1_B_006	= "/v1/kr/bank/b/fund/transaction-list";                    // 은행 법인 펀드 거래내역
	                                                                                                        
	public static final String KR_BK_1_P_001	= "/v1/kr/bank/p/account/account-list";                     // 은행 개인 보유계좌				
	public static final String KR_BK_1_P_002	= "/v1/kr/bank/p/account/transaction-list";                 // 은행 개인 수시입출 거래내역
	public static final String KR_BK_1_P_003	= "/v1/kr/bank/p/installment-savings/transaction-list";     // 은행 개인 적금 거래내역
	public static final String KR_BK_1_P_004	= "/v1/kr/bank/p/loan/transaction-list";                    // 은행 개인 대출 거래내역
	                                                                                                        
	public static final String KR_BK_2_P_001	= "/v1/kr/bank2/p/account/account-list";                    // 저축은행 개인 보유계좌 조회
	public static final String KR_BK_2_P_002	= "/v1/kr/bank2/p/account/transaction-list";                // 저축은행 개인 수시입출 거래내역
	               
	public static final String KR_CD_B_001	= "/v1/kr/card/b/account/card-list";                            // 카드 법인 보유카드
	public static final String KR_CD_B_002	= "/v1/kr/card/b/account/approval-list";                        // 카드 법인 승인내역
	public static final String KR_CD_B_003	= "/v1/kr/card/b/account/billing-list";                         // 카드 법인 청구내역
	public static final String KR_CD_B_004	= "/v1/kr/card/b/account/limit";                                // 카드 법인 한도조회
	               
	public static final String KR_CD_P_001	= "/v1/kr/card/p/account/card-list";                            // 카드 개인 보유카드
	public static final String KR_CD_P_002	= "/v1/kr/card/p/account/approval-list";                        // 카드 개인 승인내역
	public static final String KR_CD_P_003	= "/v1/kr/card/p/account/billing-list";                         // 카드 개인 청구내역
	public static final String KR_CD_P_004	= "/v1/kr/card/p/account/limit";                                // 카드 개인 한도조회
	              
	
	public static final String GET_ACCOUNTS = "/v1/account/list";            								// 계정 목록 조회			
	public static final String CREATE_ACCOUNT = "/v1/account/create";            							// 계정 등록(커넥티드아이디 발급)			
	public static final String ADD_ACCOUNT = "/v1/account/add";            									// 계정 추가			
	public static final String UPDATE_ACCOUNT = "/v1/account/update";            							// 계정 수정			
	public static final String DELETE_ACCOUNT = "/v1/account/delete";            							// 계정 삭제			
	
	
	
	
	
	
	
	
	
	
	
	
	/**	CODEF로부터 발급받은 클라이언트 아이디	*/
	public static final String CLIENT_ID 	= "CODEF로부터 발급받은 클라이언트 아이디";						
	
	/**	CODEF로부터 발급받은 시크릿 키	*/
	public static final String SECERET_KEY 	= "CODEF로부터 발급받은 시크릿 키";												
	
	/**	OAUTH2.0 토큰	*/
	public static String ACCESS_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXJ2aWNlX3R5cGUiOiIwIiwic2NvcGUiOlsicmVhZCJdLCJzZXJ2aWNlX25vIjoiMDAwMDAwMDQyMDAxIiwiZXhwIjoxNTYzMzI3MzQ1LCJhdXRob3JpdGllcyI6WyJJTlNVUkFOQ0UiLCJQVUJMSUMiLCJCQU5LIiwiRVRDIiwiU1RPQ0siLCJDQVJEIl0sImp0aSI6ImFlZGIwY2FkLWU0NGYtNDRlNC04YTVmLTMxODFjMmZlOWE5NiIsImNsaWVudF9pZCI6ImNvZGVmX21hc3RlciJ9.VQb_mtyc6aa5AY1JNilvK2TmLitjHM8pwXckvAustNAcN-HOdtryu9k_x3BCnZE1GSd_cMXl57kev8LHnKlNQFTD3vo9wKrzMBDUT6kgXd4CoaduGAyb6VzMZH4M-wXzgCg8yuWuFKXKYq_ivs66qWtV-4s4Hh3IW-6TIa4BD58vztPvIfLsBka_dJ31YDSvs9FINUNMcSMCob9iFQbxy0FHKwb4jY65RaKEE570wTbJ3l-dyt7eg65c1R4bYvGx2iD6C7KJKTzVAYoGv8Bms26cF_qOKIS7ZDTMdx8MBpcJ0e2rjbhfj6BNl8re0W2nScSN_qPI_YLaoNWSWpeqbQ";
}
