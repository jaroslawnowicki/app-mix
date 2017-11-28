package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.comment

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.Comment
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post.PostDAOMapper
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDAOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class CommentDAOMapper @Autowired
constructor(private val userDAOMapper: UserDAOMapper, private val postDAOMapper: PostDAOMapper) {

    fun mapToDTO(comment: Comment): CommentDto {

        val postDto = postDAOMapper.mapToDTO(comment.post!!)
        val usetDto = userDAOMapper.mapToDTO(comment.user!!)

        var commentDto = CommentDto()
        commentDto.createStamp = comment.createStamp
        commentDto.updateStamp = comment.updateStamp
        commentDto.message = comment.message
        commentDto.post = postDto
        commentDto.user = usetDto

        return commentDto
    }

    fun map(commentDto: CommentDto): Comment? {

        if (null == commentDto)
            return null

        val post = postDAOMapper.map(commentDto.post)
        val user = userDAOMapper.map(commentDto.user)
        return Comment(commentDto.id, post, user, commentDto.message, commentDto.createStamp, commentDto.updateStamp)
    }
}