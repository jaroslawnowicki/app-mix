package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostRepository
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDAOMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class NeoPostRepositoryImpl @Autowired constructor(val postDAOMapper: PostDAOMapper, val userDAOMapper: UserDAOMapper, val postDAO: PostDAO) : PostRepository {

    override fun save(post: List<Post>) {
        post.stream().map({ p ->
            {
                val postDto = postDAOMapper.mapToDTO(p)
                postDto.owner = userDAOMapper.mapToDTO(p.owner!!)
                postDAO.save(postDto)
            }
        })
    }

    override fun delete(id: Long?) {
        postDAO.delete(id)
    }

    override fun getById(id: Long?): Post? {
        val postDto = postDAO.findOne(id, 2)
        val post = postDAOMapper.map(postDto)

        if (null != post) {
//            post.likes = postDto?.like?.parallelStream()?.map(userDAOMapper::map)?.collect(Collectors.toSet<User>())
//            post.countLikes = postDto?.like?.size as Long
//            post.owner = userDAOMapper.map(postDto?.owner)
//            post.setComments(postDto.getComments().stream().map(???({ commentDAOMapper.map() })).collect(Collectors.toList<T>()))
        }

        return post
    }

    override fun getByIdAndMedia(id: Long?): Post? = postDAOMapper.map(postDAO.findOne(id, 5))

    override fun getByIdWithUser(id: Long?): Post? {
        val postDto = postDAO.findOne(id, 2)
        val post = postDAOMapper.map(postDto)

        if (null != postDto)
            post?.owner = userDAOMapper.map(postDto.owner)

        return post
    }

    override fun deleteLike(userId: String, postId: Long?) {
        postDAO.deleteLike(userId, postId)
    }

    override fun getByUserId(userId: String): List<Post?> {
        val out = ArrayList<Post?>()

        val sort = Sort(Sort.Direction.DESC, "post.comments.createStamp")

        postDAO.findByOwnerUserId(userId, sort).forEach { post ->

            val p = postDAOMapper.map(post)
//            if (null != post.getLikes() && post.getLikes().size() > 0) {
//                val usersLike = post.getLikes().stream().map(???({ userDAOMapper.map() })).collect(Collectors.toSet<T>())
//                p.likes.addAll(usersLike)
//                p.countLikes = p.likes.size
//            }

//            p.owner = userDAOMapper.map(post.getOwner())
//            p.owner.setFriends(null)
//            p.owner.setFollows(null)

//            if (null != post.getComments() && post.getComments().size() > 0) {
//                p.setComments(post.getComments().stream().map(???({ commentDAOMapper.map() })).collect(Collectors.toList<T>()))
//            }

            out.add(p)
        }

        return out
    }

    override fun getByUserId(userId: String, index: Int?, limit: Int?): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByFriendId(friendId: String): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByOtherUser(userId: String): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(text: String): List<Post?> {
        val out = ArrayList<Post?>()

        postDAO.search(text).forEach { post ->
            val p = postDAOMapper.map(post)
//            if (null != post.getLikes() && post.getLikes().size() > 0) {
//                val usersLike = post.getLikes().stream().map(???({ userDAOMapper.map() })).collect(Collectors.toSet<T>())
//                p.likes.addAll(usersLike)
//                p.countLikes = p.likes.size
//            }

//            if (null != post.getComments() && post.getComments().size() > 0) {
//                p.setComments(post.getComments().stream().map(???({ commentDAOMapper.map() })).collect(Collectors.toList<T>()))
//            }
//
//            if (null != post.getOwner()) {
//                p.owner = userDAOMapper.map(post.getOwner())
//                p.owner.setFriends(null)
//                p.owner.setFollows(null)
//            }

            out.add(p)
        }
        return out
    }

    override fun search(text: String, index: Int?, limit: Int?): List<Post?> {
        val out = ArrayList<Post?>()

        postDAO.search(text, index, limit).forEach { post ->
            val p = postDAOMapper.map(post)

//            if (null != post.likes  getLikes() && post.getLikes().size() > 0) {
////                val usersLike = post.getLikes().stream().map(???({ userDAOMapper.map() })).collect(Collectors.toSet<T>())
////                p.likes.addAll(usersLike)
////                p.countLikes = p.likes.size
//            }

//            if (null != post.getComments() && post.getComments().size() > 0) {
//                p.setComments(post.getComments().stream().map(???({ commentDAOMapper.map() })).collect(Collectors.toList<T>()))
//            }
//
//            if (null != post.getOwner()) {
//                p.owner = userDAOMapper.map(post.getOwner())
//                p.owner.setFriends(null)
//                p.owner.setFollows(null)
//            }

            out.add(p)
        }
        return out
    }

    override fun deleteAll() = postDAO.deleteAll();


    override fun save(post: Post): Post? {
        val postDto = postDAOMapper.mapToDTO(post)
        postDto.owner = userDAOMapper.mapToDTO(post.owner!!)


//        if (null != post.likes)
//            postDto.like = post?.likes?.parallelStream()?.map(userDAOMapper::mapToDTO)?.collect(Collectors.toSet<UserDto>()).orEmpty()

//
//        if (null != post.getComments()) {
//            postDto.setComments(post.getComments().stream().map(???({ commentDAOMapper.map() })).collect(Collectors.toList<T>()))
//        }

        return postDAOMapper.map(postDAO.save(postDto, 2))
    }
}