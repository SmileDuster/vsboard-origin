package com.smileduster.vsboard;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans({
        @MapperScan("com.smileduster.vsboard.service.account.data"),
        @MapperScan("com.smileduster.vsboard.service.battle.data"),
        @MapperScan("com.smileduster.vsboard.service.member.data"),
})
public class VsboardOriginApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsboardOriginApplication.class, args);
    }

}
