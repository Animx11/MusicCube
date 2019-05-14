package MusicCube.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class CORSFilter extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
}