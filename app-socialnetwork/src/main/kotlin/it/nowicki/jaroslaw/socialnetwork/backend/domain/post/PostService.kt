package it.nowicki.jaroslaw.socialnetwork.backend.domain.post

import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.PostNotFoundException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.UserNotFriendException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.UserNotOwnerPostException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.media.MediaService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by jarek on 16.06.17.
 */
@Service
class PostService {

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mediaService: MediaService

    fun create(userId: String, post: Post): Post? {
        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        post.createStamp = System.currentTimeMillis()
        post.owner = user
        return this.postRepository.save(post)
    }

    @Transactional(readOnly = true)
    fun getById(postId: Long?): Post = postRepository.getById(postId) ?: throw PostNotFoundException()

    fun getWall(userId: String): List<Post?> {
        userRepository.getByUserId(userId) ?: throw  UserNotFoundException()

        val wall = postRepository.getByUserId(userId)

        return wall
    }

    fun getWall(userId: String, index: Int?, limit: Int?): List<Post?> = postRepository.getByUserId(userId, index, limit)

    fun getWallFriend(userId: String, userIdFriend: String): List<Post?> {
        val userFriends = userRepository.getByFriends(userId)
        val userFriend = userRepository.getByUserId(userIdFriend) ?: throw UserNotFoundException()

        if (!userFriends.contains(userFriend))
            throw UserNotFriendException()

        return postRepository.getByUserId(userId)
    }

    fun getWallFriend(userId: String, userIdFriend: String, index: Int?, limit: Int?): List<Post?> {

        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val userFriend = userRepository.getByUserId(userId) ?: throw UserNotFoundException()
//
//        if (user.fi getFriends().contains(userFriend))
//            throw UserNotFriendException()

        return postRepository.getByUserId(userId, index, limit)
    }

    fun update(postId: Long?, post: Post, userId: String) {

        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()

        val postDB = postRepository.getById(postId) ?: throw PostNotFoundException()

        if (!(postDB.owner?.equals(user) as Boolean))
            throw UserNotOwnerPostException()

        if (null != post.description)
            postDB.description = post.description

        if (post?.scope.orEmpty().toHashSet().size > 0)
            postDB.scope = post.scope

        if (null != post.title)
            postDB.title = post.title

        if (null != post.tags) {
            postDB.tags = post.tags
        }


//        postDB.timeChangeLog.add(System.currentTimeMillis())
        postRepository.save(postDB)
    }

    fun delete(postId: Long?, userId: String) {

        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()

        val postDB = postRepository.getById(postId) ?: throw PostNotFoundException()

        if (!(postDB.owner?.equals(user) as Boolean))
            throw UserNotOwnerPostException()

        postRepository.delete(postId)
    }

    @Transactional
    fun like(userId: String, postId: Long?) {
        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val post = postRepository.getById(postId) ?: throw PostNotFoundException()
//        post.getLikes().add(user)
        this.postRepository.save(post)
    }

    fun unlike(userId: String, postId: Long?) = postRepository.deleteLike(userId, postId)


    @Transactional
    fun repost(userId: String, postId: Long?): Post? {
        val user = userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        return postRepository.getByIdAndMedia(postId) ?: throw PostNotFoundException()
    }

    fun search(text: String): List<Post?> = postRepository.search(text)

    fun search(text: String, index: Int?, limit: Int?): List<Post?> = postRepository.search(text)

    fun deleteAll() = postRepository.deleteAll()
}
