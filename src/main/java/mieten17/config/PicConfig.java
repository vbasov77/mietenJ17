package mieten17.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String myExternalFilePath = "file:///D:/STUD/Spring/img_for_mieten17/";
        registry.addResourceHandler("/img_for_mieten17/**").addResourceLocations(myExternalFilePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }


}