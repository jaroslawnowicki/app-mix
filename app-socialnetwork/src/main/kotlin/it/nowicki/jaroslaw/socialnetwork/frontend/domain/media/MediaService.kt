package it.nowicki.jaroslaw.socialnetwork.frontend.domain.media

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.MediaNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.PostNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotOwnerPostException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostRepository
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.support.FileHelper
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.support.HashGenerator
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.FileWriter
import java.util.*


@Service
class MediaService {

    @Autowired
    lateinit var mediaRepository: MediaRepository

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var hashGenerator: HashGenerator

    @Autowired
    lateinit var fileHelper: FileHelper


    fun create(userId: String, postId: Long?, media: Set<Media>): Set<Media> {

        val mediaSave = HashSet<Media>()

        media.stream().forEach { m -> mediaSave.add(create(userId, postId, m, m.type)) }

        return mediaSave
    }

    @Transactional
    fun create(userId: String, postId: Long?, media: Media, type: String?): Media {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val post = postRepository.getByIdWithUser(postId) ?: throw PostNotFoundException()

        if (post.owner != user)
            throw UserNotOwnerPostException()

        val file = fileHelper.createDistDirectory(hashGenerator.generateMD5(media.blob!!))

        try {
            val writer = FileWriter(file)
            writer.write(media.blob!!)
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        media.createStamp = System.currentTimeMillis()
        media.path = fileHelper.removeStoragePlace(file.path)
        media.type = type

        if (null == type)
            media.type = media.type

        media.user = user
        media.post = post

        val mediaSave = mediaRepository.save(media)
        mediaSave!!.blob = media.blob
        return mediaSave
    }

    fun delete(userId: String, postId: Long?, mediaId: Long?) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val post = postRepository.getByIdWithUser(postId) ?: throw PostNotFoundException()

        if (post.owner != user)
            throw UserNotOwnerPostException()

        mediaRepository.delete(mediaId)
    }

    fun getById(id: Long?): Media? {
        val media = mediaRepository.getById(id) ?: throw MediaNotFoundException()
        media.blob = fileHelper.readFile(media?.path)
        return media
    }

    fun deleteAll() = mediaRepository.deleteAll()
}