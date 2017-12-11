package it.nowicki.jaroslaw.socialnetwork.backend.domain.comment

import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.PostService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserService
import it.nowicki.jaroslaw.socialnetwork.backend.support.IntegrationTest
import it.nowicki.jaroslaw.socialnetwork.backend.support.ObjectSupport
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class CommentServiceTest extends IntegrationTest {

    @Autowired
    private CommentService commentService

    @Autowired
    private UserService userService

    @Autowired
    private PostService postService

    @Shared
    def objectSupport = new ObjectSupport()

    User user = null

    Post post = null

    def setup() {
        user = userService.create(objectSupport.addUser())
        post = postService.create(user.userId, objectSupport.addPost())
    }

    def "added comment, should by return added comment"() {
        given:
        Comment comment = commentService.create(user.userId, post.id, objectSupport.addComment(post, user))
        Comment commentResponse = commentService.getById(comment.id)

        expect:
        commentResponse.message == objectSupport.COMMENT_MESSAGE
        commentResponse.user.userId == user.userId
        commentResponse.post.id == post.id
    }


}
