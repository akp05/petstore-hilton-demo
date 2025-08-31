package org.openapitools.api;

import org.openapitools.model.Pet;
import org.openapitools.configuration.PetConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import jakarta.annotation.Generated;
import java.util.ArrayList;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-08-12T01:40:17.501326+05:30[Asia/Kolkata]", comments = "Generator version: 7.6.0")
@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v2}")
public class PetApiController implements PetApi {

    private final NativeWebRequest request;
    private final PetConfiguration petConfiguration;

    @Autowired
    public PetApiController(NativeWebRequest request, PetConfiguration petConfiguration) {
        this.request = request;
        this.petConfiguration = petConfiguration;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        List<Pet> canine = new ArrayList<>();
        Pet rex = new Pet("Rex", List.of("rex.jpg"));
        rex.setId(1L);
        canine.add(rex);
        Pet buddy = new Pet("Buddy", List.of("buddy.jpg"));
        buddy.setId(2L);
        canine.add(buddy);

        List<Pet> feline = new ArrayList<>();
        Pet misty = new Pet("Misty", List.of("misty.jpg"));
        misty.setId(1L);
        feline.add(misty);
        Pet shadow = new Pet("Shadow", List.of("shadow.jpg"));
        shadow.setId(2L);
        feline.add(shadow);

        List<Pet> source = petConfiguration.isCanine() ? canine : feline;

        for (Pet p : source) {
            if (p.getId() != null && p.getId().equals(petId)) {
                return ResponseEntity.ok(p);
            }
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
