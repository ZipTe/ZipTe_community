package com.zipte.platform.application.service.apt;

import com.zipte.platform.application.port.in.dto.request.apt.AptRequest;
import com.zipte.platform.application.port.out.apt.LoadAptPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.zipte.platform.application.port.in.apt.AptService;
import com.zipte.platform.adapter.in.api.dto.response.AptResposnseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class AptServiceImpl implements AptService {

    private final LoadAptPort aptRepository;
    private final WebClient webClient;

    @Override
    public AptResposnseDto register(AptRequest aptRequest) {
        return null;
    }

    @Override
    public Mono<Object> getOne(String apartment_name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/find/")
                        .queryParam("kaptName", apartment_name)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> getListByAI(String apartment_name, int top_n) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/apt/")
                        .queryParam("apartment_name", apartment_name)
                        .queryParam("top_n", top_n)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> getPriceByDong(String dong, int year) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/change")
                        .queryParam("dong", dong)
                        .queryParam("year", year)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> getPriceByDongAndSize(String dong, String size, int year) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/change/size")
                        .queryParam("dong", dong)
                        .queryParam("size", size)
                        .queryParam("year", year)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    @Override
    public Mono<Object> getPriceByApt(String aptName, String size, int year) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/price/apt")
                        .queryParam("apt_name", aptName)
                        .queryParam("size", size)
                        .queryParam("year", year)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

}
