package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.comment

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.Comment
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.CommentRepository
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDAOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
class NeoCommentRepository @Autowired
constructor(private val commentDAO: CommentDAO, private val commentDAOMapper: CommentDAOMapper, private val userDAOMapper: UserDAOMapper) : CommentRepository {

    override fun save(comment: Comment): Comment? {
        val commentDto = commentDAOMapper.mapToDTO(comment)
        return commentDAOMapper.map(this.commentDAO.save(commentDto, 1))
    }

    override fun delete(commentId: Long?) {
        this.commentDAO.delete(commentId)
    }

    override fun getById(commentId: Long?): Comment? {
        return commentDAOMapper.map(commentDAO.getById(commentId))
    }

    override fun deleteAll() {
        this.commentDAO.deleteAll()
    }

}