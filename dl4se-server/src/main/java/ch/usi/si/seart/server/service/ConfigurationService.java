package ch.usi.si.seart.server.service;

import ch.usi.si.seart.model.Configuration;
import ch.usi.si.seart.repository.ConfigurationRepository;
import ch.usi.si.seart.server.config.properties.PlatformProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface ConfigurationService {

    <T> T get(String key, Class<T> type);
    Map<String, String> get();
    Map<String, String> update(Collection<Configuration> configurations);
    boolean exists(Configuration configuration);

    @Service
    @AllArgsConstructor(onConstructor_ = @Autowired)
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    class ConfigurationServiceImpl implements ConfigurationService, InitializingBean {

        private static final String CONFIGURATION_ENVIRONMENT_PROPERTY_SOURCE_NAME = "configurationEnvironment";

        private static final Collector<Configuration, ?, Map<String, Object>> DEFAULT_COLLECTOR = Collectors.toMap(
                Configuration::getKey, Configuration::getValue
        );

        PlatformProperties platformProperties;

        ConfigurationRepository configurationRepository;
        ConfigurableEnvironment configurableEnvironment;

        @Override
        public <T> T get(String key, Class<T> type) {
            return configurableEnvironment.getRequiredProperty(key, type);
        }

        @Override
        public Map<String, String> get() {
            return configurationRepository.findAll().stream()
                    .collect(Collectors.toMap(
                            Configuration::getKey,
                            Configuration::getValue,
                            (ignored, value) -> value,
                            TreeMap::new
                    ));
        }

        @Override
        public Map<String, String> update(Collection<Configuration> configurations) {
            configurationRepository.saveAll(configurations);
            configurations = configurationRepository.findAll();
            Map<String, Object> map = configurations.stream().collect(DEFAULT_COLLECTOR);
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            propertySources.replace(
