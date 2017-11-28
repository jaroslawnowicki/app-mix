package it.nowicki.jaroslaw.socialnetwork.frontend.domain.post

/**
 * Created by jarek on 16.06.17.
 */
interface PostRepository {

    fun save(post: Post): Post?

    fun save(post: List<Post>)

    fun delete(id: Long?)

    fun getById(id: Long?): Post?

    fun getByIdAndMedia(id: Long?): Post?

    fun getByIdWithUser(id: Long?): Post?

    fun deleteLike(userId: String, postId: Long?)

    fun getByUserId(id: String): List<Post?>

    fun getByUserId(userId: String, index: Int?, limit: Int?): List<Post?>

    fun getByFriendId(friendId: String): List<Post?>

    fun getByOtherUser(userId: String): List<Post?>

    fun search(text: String): List<Post?>

    fun search(text: String, index: Int?, limit: Int?): List<Post?>

    fun deleteAll()

}