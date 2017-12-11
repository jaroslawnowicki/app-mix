package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.security


import org.keycloak.KeycloakSecurityContext
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.AccessToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.lang.Exception
import javax.servlet.http.HttpServletRequest


@Configuration
//@EnableWebSecurity
@ComponentScan(basePackageClasses = arrayOf(KeycloakSecurityComponents::class))
class SpringSecurityConfig : KeycloakWebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagge‌​r-ui.html",
                        "/swagger-resources/configuration/security").permitAll()
                .antMatchers("/*").hasRole("user")
                .anyRequest().authenticated();
//                .hasRole("R_USER")
//                .anyRequest()
//                .permitAll()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(
            auth: AuthenticationManagerBuilder) {

        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(
                SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Bean
    fun KeycloakConfigResolver(): KeycloakSpringBootConfigResolver {
        return KeycloakSpringBootConfigResolver()
    }

    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(
                SessionRegistryImpl())
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun getAccessToken(): AccessToken {
        return (getRequest().getUserPrincipal() as KeycloakAuthenticationToken).account.keycloakSecurityContext.token
    }

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun getKeycloakSecurityContext(): KeycloakSecurityContext {
        return (getRequest().getUserPrincipal() as KeycloakAuthenticationToken).account.keycloakSecurityContext
    }

    private fun getRequest(): HttpServletRequest {
        return (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).getRequest()
    }

}