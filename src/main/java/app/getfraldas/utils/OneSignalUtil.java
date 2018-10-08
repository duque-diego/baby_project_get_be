package app.getfraldas.utils;

import app.getfraldas.DTO.Contents;
import app.getfraldas.DTO.Filter;
import org.springframework.stereotype.Component;
import app.getfraldas.DTO.OneSignalPushParameters;
import app.getfraldas.DTO.SasRetorno;
import app.getfraldas.exception.SASServiceException;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@Component
public class OneSignalUtil {


    private static final Logger LOGGER = Logger.getLogger(OneSignalUtil.class.getName());

    private OneSignalUtil(){}


    public static Filter montaFiltroOneSignal (String chave, String valor){

        Filter filter = new Filter();
        filter.setField(Constants.TAG);
        filter.setKey(chave);
        filter.setRelation(Constants.EQUAL);
        filter.setValue(valor);
        return filter;
    }

    public static Contents montaContentOneSignal (String textPush){

        Contents contents = new Contents();
        contents.setEn(textPush);
        return contents;
    }

    public static SasRetorno callPushNotificationService( Contents contents, List<Filter> filterList) throws SASServiceException {


        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add(Constants.AUTHOTIZATION, Constants.AUTHTOKEN_PROMO);
        headers.add(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);

        List<String> segmentosList = new ArrayList<>();
        OneSignalPushParameters oneSignalPushParameters = new OneSignalPushParameters();
        oneSignalPushParameters.setapp_Id(Constants.PUSHAPPID_PROMO);
        oneSignalPushParameters.setContents(contents);
        oneSignalPushParameters.setFilters(filterList);

        try {
            ResponseEntity<Object> resposta = HttpClientUtil.post(Constants.ONE_SIGNAL_BASE_URL, oneSignalPushParameters, headers);
            String json = new Gson().toJson(resposta.getBody(), LinkedHashMap.class);
            return new Gson().fromJson(json, SasRetorno.class);

        } catch (HttpClientErrorException e) {
            throw new SASServiceException(e.getMessage());
        }
    }


}
