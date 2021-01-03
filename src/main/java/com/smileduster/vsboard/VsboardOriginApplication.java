package com.smileduster.vsboard;

import com.smileduster.vsboard.api.tools.DefaultConverter;
import com.smileduster.vsboard.api.tools.DefaultGenerator;
import com.smileduster.vsboard.api.tools.Converter;
import com.smileduster.vsboard.api.tools.Generator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VsboardOriginApplication {

    @Bean
    public Converter converter() {
        return new DefaultConverter();
    }

    @Bean
    public Generator generator() {
        return new DefaultGenerator();
    }

    public static void main(String[] args) {
        SpringApplication.run(VsboardOriginApplication.class, args);
    }

}
