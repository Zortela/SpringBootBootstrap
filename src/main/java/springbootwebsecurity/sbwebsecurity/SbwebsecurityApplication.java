package springbootwebsecurity.sbwebsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import springbootwebsecurity.sbwebsecurity.model.RoleFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class SbwebsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbwebsecurityApplication.class, args);
    }

    @Configuration
    static class WebConfig implements WebMvcConfigurer {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public void addFormatters(FormatterRegistry formatterRegistry) {
            formatterRegistry.addFormatter(new RoleFormatter(entityManager));
        }

    }

    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
