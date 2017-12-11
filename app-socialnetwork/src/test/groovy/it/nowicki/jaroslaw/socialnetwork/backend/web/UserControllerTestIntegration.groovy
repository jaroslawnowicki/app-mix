//package it.nowicki.jaroslaw.socialnetwork.frontend.web
//
//import it.nowicki.jaroslaw.socialnetwork.fronted.domain.user.UserService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.context.embedded.LocalServerPort
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.context.annotation.Profile
//import org.springframework.http.HttpEntity
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpMethod
//import org.springframework.http.ResponseEntity
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.util.LinkedMultiValueMap
//import org.springframework.util.MultiValueMap
//import org.springframework.web.context.WebApplicationContext
//import spock.lang.Specification
//
///**
// * Created by jarek on 27.07.17.
// */
//@ContextConfiguration // not mentioned by docs, but had to include this for Spock to startup the Spring context
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserControllerTestIntegration  extends Specification {
//
//    @Autowired
//    WebApplicationContext context
//
//    @LocalServerPort
//    String port
//
//    @Autowired
//    UserService userService
//
//    TestRestTemplate test
//
//    def setup() {
//         test = new TestRestTemplate()
//    }
//
//    def "get user info"() {
//        given:
//            MultiValueMap<String, String> form = new LinkedMultiValueMap<>()
//            form.add("username", "adminx")
//            form.add("password", "password")
//
//            ResponseEntity<String> response = test.postForEntity("http://localhost:" + port + "/login", form, String.class);
//            String sessionCookie = response.getHeaders().get("Set-Cookie")
//                    .get(0).split(";")[0];
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Cookie", sessionCookie);
//            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//
//            response = test.exchange("http://localhost:" + port + "/user",
//                    HttpMethod.GET, httpEntity, String.class);
//        expect: "web application context exists"
//        context != null
//    }
//
//
//}