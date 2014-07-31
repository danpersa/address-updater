package de.regis;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author danix
 */
@Configuration
@ComponentScan(basePackages = { "de.regis.domain", "de.regis.repository", "de.regis.service"})
@EnableAutoConfiguration
@ActiveProfiles("integration")
public class ServiceIntegrationTestConfig {
}
