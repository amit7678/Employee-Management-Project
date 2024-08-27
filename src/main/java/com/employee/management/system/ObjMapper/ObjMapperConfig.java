package com.employee.management.system.ObjMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ObjMapperConfig {     // Done Configure ObjectMapper Class as a Spring Bean
     @Bean
    ObjectMapper objMapper()
    {
        return new ObjectMapper();
    }


}
