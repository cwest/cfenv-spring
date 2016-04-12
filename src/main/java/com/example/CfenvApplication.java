package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CfenvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfenvApplication.class, args);
	}
}

@RestController
class CfenvController {
    public final Environment environment;

    @Autowired
    public CfenvController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping("/")
    public CloudFoundryEnvironment cloudFoundryEnvironment() {
        return new CloudFoundryEnvironment(environment.getProperty("CF_INSTANCE_INDEX"));
    }
}

class CloudFoundryEnvironment {
    private final String instance;

    public CloudFoundryEnvironment(String instance) {
        this.instance = instance;
    }

    public String getInstance() {
        return instance;
    }
}
