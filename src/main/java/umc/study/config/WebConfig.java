package umc.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc.study.config.resolver.CheckPageArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CheckPageArgumentResolver checkPageArgumentResolver;

    public WebConfig(CheckPageArgumentResolver checkPageArgumentResolver) {
        this.checkPageArgumentResolver = checkPageArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(checkPageArgumentResolver);
    }
}

