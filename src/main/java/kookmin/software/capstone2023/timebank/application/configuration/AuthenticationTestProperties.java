package kookmin.software.capstone2023.timebank.application.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import lombok.Data;

@ConfigurationProperties(prefix = "application.authentication.test")
@Data
public class AuthenticationTestProperties {
    private Boolean enabled = false;

    private UserProperties user = new UserProperties();
}
