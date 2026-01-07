package com.sonny.ecommerce.product;


import com.sonny.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;
    public List<PurchaseRes> purchaseProducts(List<PurchaseReq> req) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseReq>> request = new HttpEntity<>(req, headers);
        ParameterizedTypeReference<List<PurchaseRes>> responseType = new ParameterizedTypeReference<List<PurchaseRes>>() {};
        ResponseEntity<List<PurchaseRes>> response = restTemplate.exchange(productUrl + "/purchase", HttpMethod.POST, request, responseType);
        if(response.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing product purchase, status: " + response.getStatusCode());
        }
        return response.getBody();
    }
}
