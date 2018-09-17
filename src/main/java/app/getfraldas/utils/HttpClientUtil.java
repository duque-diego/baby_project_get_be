package app.getfraldas.utils;

import org.apache.http.util.ExceptionUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class HttpClientUtil {
	
    
    private static final Logger LOGGER = Logger.getLogger(HttpClientUtil.class.getName());

    private HttpClientUtil(){}

    /**
	 * Metodo para execucao de requisicao 
	 * @param url - URL 
	 * @param parametrosQuery - Map<String, String> parametrosQuery
     * @param parametersHeader - Map<String, String> parametersHeader
	 * @return List<String> - Json em formato String com o retorno da execucao do servico
	 */
   
    public static List<String> get(String url, Map<String, List<String>> parametrosQuery, Map<String, String> parametersHeader ) throws HttpClientErrorException {    
    	
        HttpHeaders headers = construirHeader(parametersHeader);
        URI uri = construirURI(url, parametrosQuery);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = getRestTamplate();

        LOGGER.info("Get Request: " + uri.getHost() + uri.getPath());

        try {
            HttpEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return construirArrayResponse(Constants.HTTP_OK, response.getBody());

        } catch (RestClientResponseException e) {
            LOGGER.severe(String.valueOf(e.getRawStatusCode()));
            LOGGER.severe(e.getResponseBodyAsString());

            throw e;
        }
    }

    public static ResponseEntity<Object> post(String url, Object requestBodyJson, MultiValueMap<String, String> headers) throws HttpClientErrorException {
        RestTemplate restTemplate = getRestTamplate();
        HttpEntity<Object> request = new HttpEntity<Object>(requestBodyJson, headers);

        LOGGER.info("Post Request: " +url);
        LOGGER.info(requestBodyJson.toString());

        try {
            return restTemplate.postForEntity(url, request, Object.class);
        } catch (RestClientResponseException e) {
            LOGGER.severe(String.valueOf(e.getRawStatusCode()));
            LOGGER.severe(e.getResponseBodyAsString());
            throw e;
		}
    }

    private static HttpHeaders construirHeader(Map<String, String> parametrosHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        if(null != parametrosHeader) {
            for (Map.Entry<String, String> entry : parametrosHeader.entrySet()) {
                headers.set(entry.getKey(), entry.getValue());
            }
        }
        return  headers;
    }

    private static URI construirURI(String url, Map<String, List<String>> parametros) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        if (parametros != null) {
        		Iterator<String> it = parametros.keySet().iterator();
        		List<String> tempList = null;
        		
        		while (it.hasNext()) {
        			String key = it.next().toString();
        			tempList = parametros.get(key);
        			
        			if(tempList != null) {
        				for (String id: tempList) {
        	                builder.queryParam(key, id);
        				}
        			}
        		}
        }
        
        UriComponents components = builder.build(true);
        return components.toUri();
    }

    private static List<String> construirArrayResponse(String codeHttp, String bodyHTTP){
        LOGGER.info("Http Response: " + codeHttp);
        LOGGER.info(bodyHTTP);
        List<String> arrayResponse = new ArrayList<>();
        arrayResponse.add(codeHttp);
        arrayResponse.add(bodyHTTP);
        return  arrayResponse;
    }

    public static RestTemplate getRestTamplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate
                .getRequestFactory();
        rf.setConnectTimeout(6000);
        rf.setReadTimeout(6000);
        return restTemplate;
    }

}