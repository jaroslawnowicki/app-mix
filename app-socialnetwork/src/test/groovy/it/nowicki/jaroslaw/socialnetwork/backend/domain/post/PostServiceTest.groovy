package it.nowicki.jaroslaw.socialnetwork.backend.domain.post

import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserService
import it.nowicki.jaroslaw.socialnetwork.backend.support.IntegrationTest
import it.nowicki.jaroslaw.socialnetwork.backend.support.ObjectSupport
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class PostServiceTest extends IntegrationTest {

    @Autowired
    UserService userService

    @Autowired
    PostService postService

    @Shared
    def objectSupport = new ObjectSupport()

    User user = null

    Post post = null

    def setup() {
        user = userService.create(objectSupport.addUser())
        post = postService.create(user.userId, objectSupport.addPost())
    }

    def "should by create post and return post"() {
        given:
        Post postResponse = postService.getById(post.id)

        expect:
//        postResponse.countLikes == objectSupport.POST_COUNT_LIKES
        postResponse.description == objectSupport.POST_DESCRIPTION
        postResponse.title == objectSupport.POST_TITLE
    }

}