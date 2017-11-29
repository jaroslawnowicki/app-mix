package it.nowicki.jaroslaw.socialnetwork

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class SocialNetworkApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SocialNetworkApplication::class.java, *args)
        }
    }

}






