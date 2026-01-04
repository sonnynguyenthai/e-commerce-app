package com.sonny.ecommerce;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
) {

}
