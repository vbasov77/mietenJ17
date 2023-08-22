package mieten17.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
     System.out.println( "Файл конфигурации вступил в силу");
        registry.addResourceHandler("/static/images/**").addResourceLocations("/static/images");
    }


}
