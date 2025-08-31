package org.openapitools.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Binds application properties with prefix "app".
 */
@Component
@ConfigurationProperties(prefix = "app")
public class PetConfiguration {

    /**
     * Flag to select canine list when true; feline list when false.
     */
    private boolean canine = true;

    public boolean isCanine() {
        return canine;
    }

    public void setCanine(boolean canine) {
        this.canine = canine;
    }
}


