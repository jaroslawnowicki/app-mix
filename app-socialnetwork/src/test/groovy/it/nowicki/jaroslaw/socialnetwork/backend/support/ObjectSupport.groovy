package it.nowicki.jaroslaw.socialnetwork.backend.support

import it.nowicki.jaroslaw.socialnetwork.backend.domain.comment.Comment
import it.nowicki.jaroslaw.socialnetwork.backend.domain.media.Media
import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import org.springframework.context.annotation.Profile

@Profile(["integration"])
class ObjectSupport {

    def USER_USER_ID = UUID.randomUUID().toString()
    def USER_USER_ID_2 = UUID.randomUUID().toString()

    def USER_EMAIL = "email@gmail.com"
    def USER_NAME = "userName"
    def USER_NICK_NAME = "userNickName"
    def USER_IS_ACTIVE_TRUE = true
    def USER_IS_ACTIVE_FALSE = false
    def USER_CREATE_STAMP = 200L

    def USER_EMAIL_UPDATE = "email@gmail.com_update"
    def USER_NAME_UPDATE = "userName_update"
    def USER_NICK_NAME_UPDATE = "userNickName_update"

    def POST_TITLE = "postTitle"
    def POST_DESCRIPTION = "postDescription"
    def POST_CREATE_STAMP = 200L
    def POST_TAG_1 = "TAG1"
    def POST_TAG_2 = "TAG2"
    def POST_TAGS = null //HashSet<String>(Arrays.asList(POST_TAG_1, POST_TAG_2))
    def POST_TIME_CHANGE_LOG = null //HashSet<Long>(Arrays.asList(1L, 2L))
    def POST_COUNT_LIKES = 2L
    def POST_PUBLIC = "public"
    def POST_SCOPES = null //HashSet<String>(Arrays.asList(POST_PUBLIC))

    def COMMENT_MESSAGE = "comment_message"
    def COMMENT_CREATE_STAMP = 100L
    def COMMENT_UPDATE_STAMP = 200L

    def MEDIA_BLOB = "blob"
    def MEDIA_TYPE = ".jpg"
    def MEDIA_CREATE_STAMP = 100L
    def MEDIA_PATH = "media/path"


    User addUser() {
        return new User(1, USER_USER_ID, USER_IS_ACTIVE_FALSE, USER_CREATE_STAMP, USER_EMAIL, USER_NAME, USER_NICK_NAME, null, null, null)

    }

    User addUser2() {
        return new User(2, USER_USER_ID_2, USER_IS_ACTIVE_FALSE, USER_CREATE_STAMP, USER_EMAIL, USER_NAME, USER_NICK_NAME, null, null, null)
    }

    Post addPost() {
        return new Post(1, POST_TITLE, POST_DESCRIPTION, POST_CREATE_STAMP, POST_TAGS, POST_TIME_CHANGE_LOG, null, null, null, POST_COUNT_LIKES, POST_SCOPES, null)
    }

    Comment addComment(Post post, User user) {
        return new Comment(null, post, user, COMMENT_MESSAGE, COMMENT_CREATE_STAMP, COMMENT_UPDATE_STAMP)
    }

    Media addMedia(User user, Post post) {
        return new Media(null, MEDIA_BLOB, MEDIA_TYPE, MEDIA_CREATE_STAMP, MEDIA_PATH, user, post)
    }


}