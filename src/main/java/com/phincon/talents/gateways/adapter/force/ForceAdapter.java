package com.phincon.talents.gateways.adapter.force;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.talents.gateways.adapter.InterfaceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.utils.ForceResponse;

public class ForceAdapter<E> implements InterfaceAdapter {
	protected String instanceUrl = "";
	protected String urlThirdParty;
	protected String clientId = "";
	protected String clientSecret = "";
	protected String username = "";
	protected String password = "";
	protected String query = "";
	protected String forceModuleName = "";
	protected Long companyid = 0L;
	protected int TOTAL_ROW_PER_REQUEST = 1000;

	RestTemplate restTemplate = new RestTemplate();
	ObjectMapper objectMapper = new ObjectMapper();
	private List<E> listResponse;
	String accessToken;
	String tokenType;

	public void setConfigure(String urlThirdParty, String clientId,
			String clientSecret, String username, String password) {
		// super();
		this.urlThirdParty = urlThirdParty;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.username = username;
		this.password = password;
	}

	public void setConfigure(ConnectedApp connectedApp, String moduleName) {
		// super();
		this.urlThirdParty = connectedApp.getInstanceUrl();
		this.clientId = connectedApp.getConsumerKey();
		this.clientSecret = connectedApp.getConsumerSecret();
		this.username = connectedApp.getUsername();
		this.password = connectedApp.getPassword();
		this.forceModuleName = moduleName;
		this.companyid = connectedApp.getCompany();
	}

	public ForceAdapter() {
		super();
	}

	/*
	 * send data to host salesforce
	 */
	@Override
	public void send(List<Map<String, Object>> listData, boolean isAckSend) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters",
				headers);

		String url = this.urlThirdParty
				+ "/services/oauth2/token?grant_type=password&client_id="
				+ this.clientId + "&client_secret=" + this.clientSecret
				+ "&username=" + this.username + "&password=" + this.password;
		
		System.out.println("Login : " +url);
		
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);

		System.out.println("+++++++++++ LOGIN RESPONSE +++++++++++++++");
		System.out.println(result.getBody());
		Map<String, Object> mapObject = null;
		try {
			mapObject = objectMapper.readValue(result.getBody(),
					new TypeReference<Map<String, Object>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		accessToken = (String) mapObject.get("access_token");
		instanceUrl = (String) mapObject.get("instance_url");
		tokenType = (String) mapObject.get("token_type");

		System.out.println("access_token : " + accessToken);
		System.out.println("+++++++++++ REQUEST RESPONSE +++++++++++++++");
		
		String urlQuery = instanceUrl + "/services/apexrest/InsertUpdate?SyncObject="
				+ this.forceModuleName;

		System.out.println(urlQuery);
		MultiValueMap<String, String> headersPost = new LinkedMultiValueMap<String, String>();
		headersPost.add("Authorization", "Bearer " + accessToken);
		headersPost.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJackson2HttpMessageConverter());

		// PREPARE POST DATA
			Map<String, Object> mapPost = new HashMap<String, Object>();
			mapPost.put("items", listData);
			String mapPostJSON = null;
			try {
				mapPostJSON = this.objectMapper.writeValueAsString(mapPost);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
			System.out.println(mapPostJSON);
			
			mapPost.remove("id");
			try {
				HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(
						mapPost, headersPost);
				String response = restTemplate.postForObject(urlQuery, request,
						String.class);

				System.out.println("Reponse Post " + response);
				try {
					mapObject = objectMapper.readValue(response,
							new TypeReference<Map<String, Object>>() {
							});
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(isAckSend)
					updateSatatusAckSync(true,listData);
				else 
					updateExtId(listData);

			} catch (HttpClientErrorException ex) {
				System.out.println("Error HTTP Client " + ex.getMessage());
			} catch (Exception ex) {
				System.out.println("Error " + ex.getMessage());

			}

	}

	@Transactional
	private void updateSatatusAckSync(boolean status, List<Map<String, Object>> listData) {

		Set<String> extIds = new HashSet<String>();
		for (Map<String, Object> map : listData) {
			String extId = (String) map.get("Id");
			//updateAckSyncStatus(status, extId);
			extIds.add(extId);
		}
		
		if(extIds.size() > 0)
			updateAckSyncStatus(status, extIds);
	}



	public void updateExtId(List<Map<String, Object>> list) {
		
	}

	
	/*
	 * receive data from host and save to Database
	 */
	
	public Map<String,Object> loginForce(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters",
				headers);

		String url = this.urlThirdParty
				+ "/services/oauth2/token?grant_type=password&client_id="
				+ this.clientId + "&client_secret=" + this.clientSecret
				+ "&username=" + this.username + "&password=" + this.password;
		System.out.println(url);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);

		System.out.println("+++++++++++ LOGIN RESPONSE +++++++++++++++");
		System.out.println(result.getBody());
		Map<String, Object> mapObject = null;
		try {
			mapObject = objectMapper.readValue(result.getBody(),
					new TypeReference<Map<String, Object>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapObject;
	}
	
	public void initRetrieve(){
		Map<String,Object> mapLoginResult = loginForce();
		if(mapLoginResult != null) {
			accessToken = (String) mapLoginResult.get("access_token");
			instanceUrl = (String) mapLoginResult.get("instance_url");
			tokenType = (String) mapLoginResult.get("token_type");
			
			// get count of row
			String urlQueryGetCount = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncCount=true&SyncExtId=null";
			System.out.println("request URL : " + urlQueryGetCount);
			MultiValueMap<String, String> multiValueHeaders = new LinkedMultiValueMap<String, String>();
			String strAuthorization = tokenType + " " + accessToken;
			multiValueHeaders.add("Authorization", strAuthorization);
			HttpEntity<Object> entityQuery = new HttpEntity<Object>(
					multiValueHeaders);
			ResponseEntity<String> resultQuery = restTemplate.exchange(urlQueryGetCount,
					HttpMethod.GET, entityQuery, String.class);
			try {
				ForceResponse forceResponse = (ForceResponse) objectMapper
						.readValue(resultQuery.getBody(), ForceResponse.class);
				if (forceResponse != null) {
					
					int total = getTotal(forceResponse.getItems());
					int loop = (total/TOTAL_ROW_PER_REQUEST )+ 1;
//					int loop =1;
					for(int i =0; i<loop ;i++){
						String queryGetData = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncExtId=null&SyncStart=0&SyncLimit="+TOTAL_ROW_PER_REQUEST;
//						String queryGetData = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncExtId=null&SyncStart=2998&SyncLimit="+TOTAL_ROW_PER_REQUEST;
						
						receive(queryGetData,true);
						sendDataAckSync();
						
					}
					
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}else {
			
		}
	}

	private List<Map<String, Object>> getDataSendAckSync() {
		return null;
	}

	@Override
	public void receive(String urlQuery,boolean isInit) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters",
				headers);

		String url = this.urlThirdParty
				+ "/services/oauth2/token?grant_type=password&client_id="
				+ this.clientId + "&client_secret=" + this.clientSecret
				+ "&username=" + this.username + "&password=" + this.password;
		System.out.println(url);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);

		System.out.println("+++++++++++ LOGIN RESPONSE +++++++++++++++");
		System.out.println(result.getBody());
		Map<String, Object> mapObject = null;
		try {
			mapObject = objectMapper.readValue(result.getBody(),
					new TypeReference<Map<String, Object>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		accessToken = (String) mapObject.get("access_token");
		instanceUrl = (String) mapObject.get("instance_url");
		tokenType = (String) mapObject.get("token_type");

		System.out.println("access_token : " + accessToken);
		System.out.println("+++++++++++ REQUEST RESPONSE +++++++++++++++");

		//String urlQuery = instanceUrl + "/services/apexrest/" + this.query;
		// String urlQuery = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncStart=1999&SyncLimit=2000";
		if(urlQuery == null)
			urlQuery = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName;

		System.out.println(urlQuery);

		MultiValueMap<String, String> multiValueHeaders = new LinkedMultiValueMap<String, String>();
		String strAuthorization = tokenType + " " + accessToken;
		multiValueHeaders.add("Authorization", strAuthorization);
		HttpEntity<Object> entityQuery = new HttpEntity<Object>(
				multiValueHeaders);
		ResponseEntity<String> resultQuery = restTemplate.exchange(urlQuery,
				HttpMethod.GET, entityQuery, String.class);
		System.out.println(resultQuery.getBody());
		try {
			ForceResponse forceResponse = (ForceResponse) objectMapper
					.readValue(resultQuery.getBody(), ForceResponse.class);
			if (forceResponse != null) {
				listResponse = convertToListObject(forceResponse.getItems());

				if (listResponse.size() > 0) {
					// save into DB
					saveListData(listResponse,isInit);
				}
				// convert response to List of Object

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int getTotal(List<Map<String, Object>> records) {
		int total = 0; 
		for (Map<String, Object> mapResult : records) {
			total = (int)mapResult.get("Total");
		}
		return total;
	}

	public List<E> convertToListObject(List<Map<String, Object>> records) {
		List<E> list = new ArrayList<E>();

		for (Map<String, Object> mapResult : records) {
			E e = convertMapResultToObject(mapResult);
			if (e != null)
				list.add(e);
		}
		return list;
	}

	protected E convertMapResultToObject(Map<String, Object> mapResult) {
		return null;
	}

	public void saveListData(List<E> listData,boolean isInit) {

	}

	@Override
	public void sendNewData() {
		// get new Data from E class
		List<Map<String, Object>> listMap = null;
		// calling send method
		send(listMap,false);
	}
	
	
	@Override
	public void sendDataAckSync() {
		// get new Data from E class
		
	}
	
	public void sendForceDataAckSync(List<Object[]> listDataAckSync){

		List<Map<String, Object>> listMap =  new ArrayList<Map<String, Object>>();
		if(listDataAckSync != null && listDataAckSync.size() > 0) {
			for (Object[] objects : listDataAckSync) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Id", (String)objects[0]);
				map.put("ExtId__c", (String)objects[1]);
				listMap.add(map);
			}
		}
		
		if(listMap.size() >0)
			send(listMap,true);
	}

	@Override
	public void sendUpdatedData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAckSyncStatus(boolean status, String string) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void updateAckSyncStatus(boolean status, Set<String> string) {
		// TODO Auto-generated method stub
		
	}

}
