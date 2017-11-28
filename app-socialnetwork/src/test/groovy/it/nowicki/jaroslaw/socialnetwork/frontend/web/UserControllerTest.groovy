//package it.nowicki.jaroslaw.socialnetwork.frontend.web
//
//import groovy.json.JsonSlurper
//import org.springframework.context.annotation.Profile
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.User
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.test.context.web.WebAppConfiguration
//import org.springframework.test.web.servlet.MvcResult
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import it.nowicki.jaroslaw.socialnetwork.fronted.domain.user.UserService
//import it.nowicki.jaroslaw.socialnetwork.fronted.infrastructure.security.UserInfo
//import it.nowicki.jaroslaw.socialnetwork.fronted.web.UserController
//import spock.lang.Shared
//import spock.lang.Specification
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//
//@ContextConfiguration
//@WebAppConfiguration
//@ActiveProfiles("integration")
//class UserControllerTest extends Specification {
//
//    def userService = Mock(UserService)
//
//    def userInfo = Mock(UserInfo)
//
//    def userController =null
//
//    def mvc = null
//
//    @Shared
//    def jsonSlurper = new JsonSlurper()
//
//    def setup() {
//        userController = new UserController(userService, userInfo)
//        mvc = MockMvcBuilders.standaloneSetup(userController).build()
//    }
//
//    def "shouldByLogon"() {
//        given:
//            logonUser(userId, login, password)
//        when:
//            MvcResult result = mvc.perform(get(endpoint)).andReturn()
//
//        then:
//            result.response.getStatus() == status
//            def res = jsonSlurper.parseText(result.getResponse().getContentAsString())
//            res.email == email
//        where:
//            endpoint         | userId       | login    | password   || status                 || content                                        || email
//            "/user"          | "admin"      | "admin"  | "password" || HttpStatus.OK.value()  || createUser(1, "admin", true)     || "admin"
//            "/user"          | "jarek   "  | "jarek"  | "password" || HttpStatus.OK.value() || createUser(0, "jarek", false)      || "jarek"
//    }
//
//    def logonUser(String userId, String login, String password) {
//        List<GrantedAuthority> listOfRoles = new ArrayList<GrantedAuthority>()
//        listOfRoles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        userInfo.principal() >> new User(login, password, listOfRoles )
//        userService.getByUserId(userId) >> createUser(1, login, true)
//    }
//
//    def it.nowicki.jaroslaw.socialnetwork.fronted.domain.user.User createUser(Integer id, String text, Boolean active) {
//        return null
//    }
//}
