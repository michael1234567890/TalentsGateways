package com.phincon.talents.gateways.adapter.force;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.talents.gateways.adapter.InterfaceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.utils.ForcePyDetailGroupingResponse;

public class ForceDetailGroupingAdapter<E> implements InterfaceAdapter {
	protected String instanceUrl = "";
	protected String urlThirdParty;
	protected String clientId = "";
	protected String clientSecret = "";
	protected String username = "";
	protected String password = "";
	protected String query = "";
	protected String forceModuleName = "";
	protected Long companyid = 0L;

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

	public ForceDetailGroupingAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * send data to host salesforce
	 */
	@Override
	public void send(List<Map<String, Object>> listData,boolean status) {
		System.out.println("Send Method");
		// data yg tebaru atau terupdate

		// submit ke force
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

		String urlQuery = instanceUrl + "/services/apexrest/"
				+ this.forceModuleName;

		System.out.println(urlQuery);
		MultiValueMap<String, String> headersPost = new LinkedMultiValueMap<String, String>();
		headersPost.add("Authorization", "Bearer " + accessToken);
		headersPost.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJackson2HttpMessageConverter());

		// PREPARE POST DATA
		//for (Map<String, Object> mapPost : listData) {
			Map<String, Object> mapPost = new HashMap<String, Object>();
			mapPost.put("items", listData);
			System.out.println(mapPost);
			Long id = (Long) mapPost.get("id");
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
				
				updateExtId(listData);

			} catch (HttpClientErrorException ex) {
				System.out.println("Error HTTP Client " + ex.getMessage());
			} catch (Exception ex) {
				System.out.println("Error " + ex.getMessage());

			}
	//	}

	}

	public void updateExtId(List<Map<String, Object>> list) {
		
	}

	/*
	 * prepare data which modified date > last sent
	 */
	@Override
	public void sendUpdatedData() {
		System.out.println("Get Updated Data Method");

	}

	/*
	 * receive data from host and save to Database
	 */

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
		if(urlQuery == null)
			urlQuery = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName;

		System.out.println(urlQuery);

		MultiValueMap<String, String> multiValueHeaders = new LinkedMultiValueMap<String, String>();
		String strAuthorization = tokenType + " " + accessToken;
		multiValueHeaders.add("Authorization", strAuthorization);
		// headers.add("Content-Type", "application/json");

		HttpEntity<Object> entityQuery = new HttpEntity<Object>(
				multiValueHeaders);
		ResponseEntity<String> resultQuery = restTemplate.exchange(urlQuery,
				HttpMethod.GET, entityQuery, String.class);
		System.out.println(resultQuery.getBody());
		try {
			ForcePyDetailGroupingResponse forceResponse = (ForcePyDetailGroupingResponse) objectMapper
					.readValue(resultQuery.getBody(), ForcePyDetailGroupingResponse.class);
			if (forceResponse != null) {
				listResponse = convertToListObject(forceResponse.getItems());

				if (listResponse.size() > 0) {
					// save into DB
					saveListDate(listResponse);
				}
				// convert response to List of Object

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<E> convertToListObject(Map<String, Object> records) {
		List<E> list = new ArrayList<E>();
		System.out.println("Key " + records.entrySet());
		
//		for (Map<String, Object> mapResult : records) {
//			E e = convertMapResultToObject(mapResult);
//			if (e != null)
//				list.add(e);
//		}
		return list;
	}

	protected E convertMapResultToObject(Map<String, Object> mapResult) {
		return null;
	}

	public void saveListDate(List<E> listData) {

	}

	@Override
	public void sendNewData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendDataAckSync() {
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
