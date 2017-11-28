package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.media

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.media.Media
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post.PostDAOMapper
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDAOMapper
import org.springframework.stereotype.Component

@Component
class MediaDAOMapper {

    fun mapToDTO(media: Media): MediaDto {

        val mediaDto = MediaDto()
        mediaDto.path = media.path
        mediaDto.createStamp = media.createStamp
        mediaDto.type = media.type

        return mediaDto
    }

    fun map(mediaDto: MediaDto?): Media? {

        val userDAOMapper = UserDAOMapper()
        val owner = userDAOMapper.map(mediaDto?.owner)
        val postDAOMapper = PostDAOMapper()
        val post = postDAOMapper.map(mediaDto?.post)

        return Media(mediaDto?.id, mediaDto?.path, mediaDto?.type, mediaDto?.createStamp, mediaDto?.path, owner, post)
    }
}