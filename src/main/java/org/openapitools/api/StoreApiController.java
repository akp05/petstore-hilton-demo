package org.openapitools.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-12T01:40:17.501326+05:30[Asia/Kolkata]", comments = "Generator version: 7.6.0")
@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v2}")
public class StoreApiController implements StoreApi {

    private final NativeWebRequest request;

    @Autowired
    public StoreApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
