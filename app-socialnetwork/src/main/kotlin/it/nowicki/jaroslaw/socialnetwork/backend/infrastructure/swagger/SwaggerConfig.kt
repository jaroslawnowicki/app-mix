package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate


@Configuration
@EnableSwagger2()
open class SwaggerConfig : WebMvcConfigurerAdapter() {

    private val appName: String = "Social Network"

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("it.nowicki.jaroslaw.socialnetwork.fronted.web"))
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate::class.java, String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .apiInfo(apiInfo)
    }

    private val apiInfo by lazy {
        ApiInfoBuilder().title(appName).build()
    }
}