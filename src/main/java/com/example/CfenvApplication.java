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
    public CloudFoundryDemo cloudFoundryDemo(@Value("${CF_INSTANCE_INDEX:0}") int instance) {
        return new CloudFoundryDemo(instance);
    }
}

class CloudFoundryDemo {
    private final int instance;

    public CloudFoundryDemo(int instance) {
        this.instance = instance;
    }

    public int getInstance() {
        return instance;
    }
}
