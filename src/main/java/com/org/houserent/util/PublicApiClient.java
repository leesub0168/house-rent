package com.org.houserent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.houserent.util.publicApi.dto.PublicApiDataDto;
import com.org.houserent.util.publicApi.dto.PublicApiMainDto;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;

public class PublicApiClient {
    private String publicApiUrl = "http://openapi.seoul.go.kr:8088";
    private String key = "5a75446d6b6c656539375244465959";
    private String etc = "/xml/tbLnOpendataRentV/1/5/";
    private String testUrl = "http://openapi.seoul.go.kr:8088/5a75446d6b6c656539375244465959/json/tbLnOpendataRentV/1/5/2022/11620";


    public void testCallApi() throws IOException {
        WebClient webClient = WebClient.create();
        String str = webClient.get().uri(URI.create(testUrl))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
//                .timeout(Duration.ofMillis(10000))
                .blockOptional().orElseThrow(
                        () -> new IllegalArgumentException("공공 api 호출 실패")
                );

        ObjectMapper mapper = new ObjectMapper();
        PublicApiMainDto publicApiMainDto = mapper.readValue(str, PublicApiMainDto.class);

        for (PublicApiDataDto publicApiDataDto : publicApiMainDto.getTbLnOpendataRentV().getRow()) {
            System.out.println(publicApiDataDto.getACC_YEAR());
            System.out.println(publicApiDataDto.toString());
        }

    }

}
