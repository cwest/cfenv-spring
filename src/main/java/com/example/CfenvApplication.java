package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CfenvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfenvApplication.class, args);
	}

    @Bean
    StringRedisTemplate stringRedisTemplate(RedisTemplate rt) {
        return new StringRedisTemplate(rt.getConnectionFactory());
    }

}

@RestController
class CfenvController {
    private final CloudFoundryDemo demo;

    @Autowired
    public CfenvController(CloudFoundryDemo demo) {
        this.demo = demo;
    }

    @RequestMapping("/")
    public CloudFoundryDemo cloudFoundryDemo(@Value("${CF_INSTANCE_INDEX:0}") int instance) {
        demo.setInstance(instance);
        return this.demo;
    }
}


@Component
class CloudFoundryDemo {
    private int instance;
    private final RedisAtomicLong visits;

    @Autowired
    public CloudFoundryDemo(StringRedisTemplate template) {
        this.visits = new RedisAtomicLong("visits", template.getConnectionFactory());
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public long getVisits() { return visits.incrementAndGet(); }
}
