package it.nowicki.jaroslaw.socialnetwork.backend.domain.media

import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.PostService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.support.FileHelper
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserService
import it.nowicki.jaroslaw.socialnetwork.backend.support.IntegrationTest
import it.nowicki.jaroslaw.socialnetwork.backend.support.ObjectSupport
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class MediaServiceTest extends IntegrationTest {

    @Autowired
    private UserService userService

    @Autowired
    private PostService postService

    @Autowired
    private MediaService mediaService

    @Shared
    def objectSupport = new ObjectSupport()

    def fileHelper = Mock(FileHelper)

    private User user = null

    private Post post = null

    def setup() {
        user = userService.create(objectSupport.addUser())
        post = postService.create(user.userId, objectSupport.addPost())
    }

    def "create media and return media "() {
        given:
        Set<Media> medias = new HashSet<>()
        medias.add(objectSupport.addMedia(user, post))
        Set<Media> media = mediaService.create(user.userId, post.id, medias)

        mediaService.setFileHelper(fileHelper)
        fileHelper.readFile("file") >> "storage file"

        Media mediaResponse = mediaService.getById(media.getAt(0).id)

        expect:
        mediaResponse.path.length() > 10
        mediaResponse.user.userId == user.userId
        mediaResponse.post.id == post.id
        mediaResponse.type == objectSupport.MEDIA_TYPE
    }
}
