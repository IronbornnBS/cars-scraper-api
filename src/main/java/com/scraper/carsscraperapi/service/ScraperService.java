package com.scraper.carsscraperapi.service;

import com.scraper.carsscraperapi.model.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ScraperService {

    @Value("${website.urls.ikman}")
    private String ikmanUrl;

    public Mono<List<ResponseDTO>> extractDataFromIk(String vehicleModel) {
        List<ResponseDTO> ResponseDTOS = new ArrayList<>();

        try {
            Document document = Jsoup.connect(ikmanUrl + vehicleModel).get();
            Element element = document.getElementsByClass("list--3NxGO").first();

            Elements elements = Objects.requireNonNull(element).getElementsByTag("a");

            for (Element ads: elements) {

                ResponseDTO responseDTO = new ResponseDTO();

                if (StringUtils.isNotEmpty(ads.attr("href"))) {
                    responseDTO.setTitle(ads.attr("title"));
                    responseDTO.setUrl("https://ikman.lk" + ads.attr("href"));
                }

                if (responseDTO.getUrl() != null) ResponseDTOS.add(responseDTO);

            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return Mono.just(ResponseDTOS);
    }

}
