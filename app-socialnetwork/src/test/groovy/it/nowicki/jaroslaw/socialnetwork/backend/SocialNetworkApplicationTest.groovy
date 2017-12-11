package it.nowicki.jaroslaw.socialnetwork

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Profile

@SpringBootApplication
@Profile(["test", "integration"])
class SocialNetworkApplicationTest {

    static main(args) {
        SpringApplication.run(SocialNetworkApplicationTest.class, *args)
    }

}