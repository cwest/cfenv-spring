package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @RequestMapping("/")
    public CloudFoundryDemo cloudFoundryDemo(@Value("${CF_INSTANCE_INDEX:0}") int instance, @Value("${cloud.application.version}") String version) {
        return new CloudFoundryDemo(instance, version);
    }
}

class CloudFoundryDemo {
    private final int instance;
    private String version;

    public CloudFoundryDemo(int instance, String version) {
        this.instance = instance;
        this.version = version;
    }

    public int getInstance() {
        return instance;
    }

    public String getVersion() {
        return version.split("-")[0];
    }
}
