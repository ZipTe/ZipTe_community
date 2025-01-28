package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.AptService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api/apt")
public class AptController {

    private final AptService aptService;

    @GetMapping("/AI")
    public Mono<ApiResponse<Object>> getAl(
            @RequestParam String apartment_name,
            @RequestParam int count) {

        final var response = aptService.getListByAI(apartment_name,count);
        return response.flatMap(data -> Mono.just(ApiResponse.ok(data)));
    }

    @GetMapping()
    public Mono<ApiResponse<Object>> getOne(@RequestParam String apartment_name){

        final var response = aptService.getOne(apartment_name);
        return response.flatMap(data -> Mono.just(ApiResponse.ok(data)));
    }

    @GetMapping("/price")
    public Mono<ApiResponse<Object>> getPriceDong(@RequestParam String dong, @RequestParam int year){

        final var response = aptService.getPriceByDong(dong,year);
        return response.flatMap(data -> Mono.just(ApiResponse.ok(data)));
    }

    @GetMapping("/price/size")
    public Mono<ApiResponse<Object>> getPriceDongSize(@RequestParam String dong, @RequestParam String size, @RequestParam int year){

        final var response = aptService.getPriceByDongAndSize(dong,size,year);
        return response.flatMap(data -> Mono.just(ApiResponse.ok(data)));
    }

    @GetMapping("/price/apt")
    public Mono<ApiResponse<Object>> getPriceApt(@RequestParam String apt_name, @RequestParam String size, @RequestParam int year){

        final var response = aptService.getPriceByApt(apt_name,size,year);
        return response.flatMap(data -> Mono.just(ApiResponse.ok(data)));
    }

}
