package com.felzan.coffeeshop.infrastructure;

import co.elastic.apm.attach.ElasticApmAttacher;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

  private static final String SERVER_URL_KEY = "server_url";
  private String serverUrl;
  private static final String SERVICE_NAME_KEY = "service_name";
  private String serviceName;
  private static final String ENVIRONMENT_KEY = "environment";
  private String environment;
  private static final String APPLICATION_PACKAGES_KEY = "application_packages";
  private String applicationPackages;
  private static final String LOG_LEVEL_KEY = "log_level";
  private String logLevel;
  private static final String CAPTURE_BODY = "capture_body";
  private String captureBody;

  @PostConstruct
  public void init() {

    Map<String, String> apmProps = new HashMap<>();
    apmProps.put(SERVER_URL_KEY, serverUrl);
    apmProps.put(SERVICE_NAME_KEY, serviceName);
    apmProps.put(ENVIRONMENT_KEY, environment);
    apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
    apmProps.put(LOG_LEVEL_KEY, logLevel);
    apmProps.put(CAPTURE_BODY, captureBody);

    ElasticApmAttacher.attach(apmProps);
  }
}
