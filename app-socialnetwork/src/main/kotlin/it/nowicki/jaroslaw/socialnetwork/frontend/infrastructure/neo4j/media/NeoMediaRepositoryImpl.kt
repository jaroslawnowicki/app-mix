package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.media

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.media.Media
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.media.MediaRepository
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post.PostDAOMapper
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDAOMapper
import org.springframework.stereotype.Repository

@Repository
class NeoMediaRepositoryImpl(val mediaDAOMapper: MediaDAOMapper, val mediaDAO: MediaDAO, val postDAOMapper: PostDAOMapper, val userDAOMapper: UserDAOMapper) : MediaRepository {
    override fun save(media: Media): Media? {
        val mediaDto: MediaDto? = mediaDAOMapper.mapToDTO(media)
        val postDto = postDAOMapper.mapToDTO(media.post as Post)
        val userDto = userDAOMapper.mapToDTO(media.user as User)
        mediaDto?.owner = userDto
        mediaDto?.post = postDto
        mediaDAO.save(mediaDto, 1)
        return mediaDAOMapper.map(mediaDto)
    }

    override fun delete(id: Long?) {
        mediaDAO.delete(id)
    }

    override fun deleteAll() {
        mediaDAO.deleteAll()
    }

    override fun getById(id: Long?): Media? {
        val mediaDto = mediaDAO.findOne(id, 2)

        val media = mediaDAOMapper.map(mediaDto)




        return media;
    }
}