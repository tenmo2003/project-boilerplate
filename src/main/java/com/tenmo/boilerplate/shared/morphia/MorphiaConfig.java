package com.tenmo.boilerplate.shared.morphia;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author anhvn
 */
@Component
public class MorphiaConfig {

    @Bean
    private Datastore datastore() {
        return Morphia.createDatastore(MongoClients.create());
    }
}
