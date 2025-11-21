package com.group02.zader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZaderSocialNetworkWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaderSocialNetworkWebsiteApplication.class, args);
	}

}
