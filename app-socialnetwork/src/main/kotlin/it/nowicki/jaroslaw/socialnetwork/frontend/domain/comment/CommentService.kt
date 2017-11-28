package it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.CommentNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.PostNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostRepository
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CommentService @Autowired
constructor(private val commentRepository: CommentRepository, private val postRepository: PostRepository, private val userRepository: UserRepository) {

    @Transactional
    fun create(userId: String, postId: Long?, comment: Comment): Comment? {
        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val post = this.postRepository.getById(postId) ?: throw PostNotFoundException()
        comment.createStamp = System.currentTimeMillis()
        comment.post = post
        comment.user = user
        return this.commentRepository.save(comment)
    }

    @Transactional
    fun update(userId: String, commentId: Long?, com: Comment) {
        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val comment = this.commentRepository.getById(commentId) ?: throw CommentNotFoundException()

        if (comment?.user?.userId != user?.userId)
            throw it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.AccessDeniedException()

        comment?.message = com.message
        comment?.id = commentId

        this.commentRepository.save(comment!!)
    }

    @Transactional
    fun delete(userId: String, commentId: Long?) {
        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val comment = this.commentRepository.getById(commentId) ?: throw CommentNotFoundException()

        if (comment?.user?.userId != user?.userId)
            throw it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.AccessDeniedException()

        this.commentRepository.delete(commentId)
    }

    fun getById(commentId: Long?): Comment? = commentRepository.getById(commentId) ?: throw CommentNotFoundException()

    fun deleteAll() = this.commentRepository.deleteAll()
}