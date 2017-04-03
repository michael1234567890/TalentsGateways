package com.phincon.talents.gateways;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.talents.gateways.utils.ForceResponse;

public class ForceAdapter<E> implements InterfaceAdapter {
	protected String instanceUrl = "";
	protected String urlThirdParty;
	protected String clientId = "";
	protected String clientSecret="";
	protected String username="";
	protected String password="";
	protected String query = "";
	
	RestTemplate restTemplate = new RestTemplate();
	ObjectMapper objectMapper = new ObjectMapper();
	private List<E> listResponse;
	String accessToken;
	String tokenType;

	
	public void setConfigure(String urlThirdParty, String clientId,
			String clientSecret, String username, String password) {
		//super();
		this.urlThirdParty = urlThirdParty;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.username = username;
		this.password = password;
	}
	
	

	public ForceAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}



	/*
	 * send data to host salesforce
	 */
	@Override
	public void send() {
		System.out.println("Send Method");
	}

	/*
	 * prepare data which modified date > last sent
	 */
	@Override
	public void getData() {
		// TODO Auto-generated method stub
		System.out.println("getData Method");
	}

	/*
	 * receive data from host and save to Database
	 */

	@Override
	public void receive() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters",
				headers);

		// String url = "https://test.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9AJuBE3rTYDg6xqNgcfzIt0yKktBvgS_EGAKJUa3FUAE9Ehfq.kjP.d6sOU8loQaSGVsjT2BUu3CRc4Qt&client_secret=5249588143570196746&username=hendra.ramdhan@payroll.dev2.pysandbox1&password=bismillah123454iNfyIRLbcB3bLYqVWDzCZ66";
		String url = this.urlThirdParty + "/services/oauth2/token?grant_type=password&client_id="+this.clientId+"&client_secret="+this.clientSecret+"&username="+this.username+"&password="+this.password;
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


		/* String urlQuery = instanceUrl
				+ "/services/data/v36.0/query?q=select id, First_Name__c from HRPERINFO__c limit 10 ";*/
		

		String urlQuery = instanceUrl
				+ "/services/data/v36.0/query?q=" + this.query;
		
		
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
			ForceResponse forceResponse = (ForceResponse) objectMapper
					.readValue(resultQuery.getBody(), ForceResponse.class);
			if (forceResponse != null) {
				System.out.println("Total Size : "
						+ forceResponse.getTotalSize());
				listResponse = convertToListObject(forceResponse.getRecords());
				
				if(listResponse.size() > 0) {
					// save into DB
					saveListDate(listResponse);
				}
				// convert response to List of Object

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<E> convertToListObject(List<Map<String, Object>> records) {
		List<E> list = new ArrayList<E>();
		
		for (Map<String, Object> mapResult : records) {
			E e = convertMapResultToObject(mapResult);
			if(e != null)
				list.add(e);
		}
		return list;
	}

	protected E convertMapResultToObject(Map<String, Object> mapResult) {
		return null;
	}

	@Override
	public void saveData() {

	}
	
	public void saveListDate(List<E> listData) {
		
	}

}
