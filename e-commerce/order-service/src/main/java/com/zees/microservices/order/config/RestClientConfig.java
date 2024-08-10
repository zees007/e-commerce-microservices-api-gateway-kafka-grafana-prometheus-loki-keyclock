package com.zees.microservices.order.config;

import com.zees.microservices.order.client.InventoryClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    @Autowired
    private ObservationRegistry observationRegistry;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .requestFactory(getClientRequestFactory())
                .observationRegistry(observationRegistry)
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory(){
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3));

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout((int) clientHttpRequestFactorySettings.getConnectTimeout().toMillis());

        // Optionally, set the read timeout if available
        if (clientHttpRequestFactorySettings.getReadTimeout() != null) {
            factory.setReadTimeout((int) clientHttpRequestFactorySettings.getReadTimeout().toMillis());
        }

        return factory;
    }

    // Assuming ClientHttpRequestFactorySettings looks something like this:
    public static class ClientHttpRequestFactorySettings {
        public static final ClientHttpRequestFactorySettings DEFAULTS = new ClientHttpRequestFactorySettings(Duration.ofSeconds(5), Duration.ofSeconds(5));

        private Duration connectTimeout;
        private Duration readTimeout;

        public ClientHttpRequestFactorySettings(Duration connectTimeout, Duration readTimeout) {
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
        }

        public Duration getConnectTimeout() {
            return connectTimeout;
        }

        public Duration getReadTimeout() {
            return readTimeout;
        }

        public ClientHttpRequestFactorySettings withConnectTimeout(Duration connectTimeout) {
            return new ClientHttpRequestFactorySettings(connectTimeout, this.readTimeout);
        }

        public ClientHttpRequestFactorySettings withReadTimeout(Duration readTimeout) {
            return new ClientHttpRequestFactorySettings(this.connectTimeout, readTimeout);
        }
    }
}
