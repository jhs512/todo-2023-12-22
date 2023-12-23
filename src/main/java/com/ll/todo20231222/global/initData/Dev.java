package com.ll.todo20231222.global.initData;

import com.ll.todo20231222.standard.util.Ut.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class Dev {
    @Bean
    @Order(3)
    ApplicationRunner initDev() {
        return args -> {
            String cmd = "npx openapi-typescript http://localhost:8090/v3/api-docs/API%20V1 -o ./front/src/lib/types/api/v1/schema.d.ts";
            Ut.cmd.runAsync(cmd);
        };
    }
}
