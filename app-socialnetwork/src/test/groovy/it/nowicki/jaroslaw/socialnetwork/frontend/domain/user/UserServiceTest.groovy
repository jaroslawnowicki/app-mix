package it.nowicki.jaroslaw.socialnetwork.frontend.domain.user

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserExistsException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotSentInviteException
import it.nowicki.jaroslaw.socialnetwork.frontend.support.IntegrationTest
import it.nowicki.jaroslaw.socialnetwork.frontend.support.ObjectSupport
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class UserServiceTest extends IntegrationTest {

    @Autowired
    UserService userService

    @Shared
    def objectSupport = new ObjectSupport()

    User user = null

    def setup() {
        user = userService.create(objectSupport.addUser())
    }

    def "should create user and get user"() {
        given:

        User userResponse = userService.getByUserId(objectSupport.USER_USER_ID)

        expect: "should equals field"

        userResponse.email == objectSupport.USER_EMAIL
        userResponse.active == objectSupport.USER_IS_ACTIVE_FALSE
        userResponse.name == objectSupport.USER_NAME
        userResponse.nickName == objectSupport.USER_NICK_NAME
        userResponse.userId == objectSupport.USER_USER_ID
    }

    def "create double user, should exception"() {
        when:
        userService.create(objectSupport.addUser())

        then:
        thrown(UserExistsException)
    }

    def "update not exists user, should exception"() {
        when:
        user.setUserId("newUserId")
        userService.update(user)

        then:
        thrown(UserNotFoundException)
    }

    def "update user"() {
        given:
        user.setEmail(objectSupport.USER_EMAIL_UPDATE)
        user.setName(objectSupport.USER_NAME_UPDATE)
        user.setNickName(objectSupport.USER_NICK_NAME_UPDATE)
        user.setActive(objectSupport.USER_IS_ACTIVE_TRUE)

        when:
        userService.update(user)

        then:
        User userResponse = userService.getByUserId(user.getUserId())

        expect:
        userResponse.email == objectSupport.USER_EMAIL_UPDATE
        userResponse.name == objectSupport.USER_NAME_UPDATE
        userResponse.nickName == objectSupport.USER_NICK_NAME_UPDATE
        userResponse.active == objectSupport.USER_IS_ACTIVE_TRUE
    }

    def "should user active"() {
        given:
        user.active = objectSupport.USER_IS_ACTIVE_TRUE

        when:
        userService.update(user)

        then:
        User userResponse = userService.getByUserId(user.getUserId())

        expect:
        userResponse.active == objectSupport.USER_IS_ACTIVE_TRUE
    }

    def "activate not exists user, should exception"() {

        when:
        userService.active(user.userId + "x")

        then:
        thrown(UserNotFoundException)
    }

    def "delete user, should user deactivate"() {
        given:
        user.active = objectSupport.USER_IS_ACTIVE_TRUE
        userService.update(user)
        userService.delete(user.userId)

        and:
        User userResponse = userService.getByUserId(user.userId)

        expect:
        userResponse.active == objectSupport.USER_IS_ACTIVE_FALSE
    }

    def "delete not exists user"() {

        when:
        userService.delete(user.userId + "x")

        then:
        thrown(UserNotFoundException)
    }

    def "add invite for user"() {
        given:
        User user2 = objectSupport.addUser2()

        when:
        userService.create(user2)
        userService.addInvite(user.userId, user2.userId)

        then:
        List<User> userResponse1 = userService.getByInviteSent(user.userId)
        List<User> userResponse2 = userService.getByInviteToAccept(user2.userId)

        expect:
        userResponse1.get(0).userId == user2.userId
        userResponse2.get(0).userId == user.userId
    }

    def "delete invite not sent"() {
        given:
        User user2 = objectSupport.addUser2()

        when:
        userService.create(user2)
        userService.addInvite(user.userId, user2.userId)
        userService.deleteInviteSent(user2.userId, user.userId)

        then:
        thrown(UserNotSentInviteException)
    }

    def "delete invite waiting"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addInvite(user.userId, user2.userId)
        userService.deleteInviteWaiting(user2.userId, user.userId)

        then:
        List<User> userResponse1 = userService.getByInviteSent(user.userId)
        List<User> userResponse2 = userService.getByInviteToAccept(user2.userId)

        expect:
        userResponse1.size() == 0
        userResponse2.size() == 0
    }

    def "delete invite not waiting"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.deleteInviteWaiting(user.userId, user2.userId)

        then:
        thrown(UserNotSentInviteException)
    }

    def "should by return invites to accept"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addInvite(user.userId, user2.userId)

        then:
        List<User> userResponse = userService.getByInviteToAccept(user2.userId)

        expect:
        userResponse.size() > 0
    }

    def "should by invite sent"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addInvite(user.userId, user2.userId)

        then:
        List<User> userResponse = userService.getByInviteSent(user.userId)

        expect:
        userResponse.size() > 0
    }

    def "add follow, should by return follows"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addFollow(user.userId, user2.userId)

        then:
        List<User> follows = userService.getByFollows(user.userId)

        expect:
        follows.size() > 0
    }

    def "delete follow"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addFollow(user.userId, user2.userId)
        userService.deleteFollow(user.userId, user2.userId)

        then:
        List<User> follows = userService.getByFollows(user.userId)

        expect:
        follows.size() == 0
    }

//    def "add friend, should return friends"() {
//        given:
//        User user2 = objectSupport.addUser2()
//        userService.create(user2)
//
//        when:
//        userService.addInvite(user.userId, user2.userId)
//        userService.addFriend(user2.userId, user.userId)
//
//        then:
//        List<User> userResponse1 = userService.getByFriends(user.userId)
//        List<User> userResponse2 = userService.getByFriends(user2.userId)
//
//        expect:
//        userResponse1.get(0).userId == user2.userId
//        userResponse2.get(0).userId == user.userId
//    }

    def "add friend without send invite, should by return exception"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addFriend(user2.userId, user.userId)

        then:
        thrown(UserNotSentInviteException)
    }

    def "delete friend, should by return zero friends"() {
        given:
        User user2 = objectSupport.addUser2()
        userService.create(user2)

        when:
        userService.addInvite(user.userId, user2.userId)
        userService.addFriend(user2.userId, user.userId)
        userService.deleteFriend(user.userId, user2.userId)

        then:
        List<User> userResponse = userService.getByFriends(user.userId)

        expect:
        userResponse.size() == 0
    }
}