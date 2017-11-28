package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post

import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository


/**
 * Created by jarek on 20.09.17.
 */
interface PostDAO : Neo4jRepository<PostDto, Long> {


    @Query("MATCH (post:`Post`) MATCH (user:`User`) WHERE user.`userId` = {0} "
            + "OPTIONAL MATCH (post)<-[:`POST`]-(user) WITH post MATCH p=(post)-[*0..2]-(m) "
            + "RETURN p, ID(post)")
    fun findByOwnerUserId(userId: String, sort: Sort): List<PostDto?>

    @Query("MATCH (post:`Post`) MATCH (user:`User`) WHERE user.`userId` = {0} "
            + "OPTIONAL MATCH (post)<-[:`POST`]-(user) WITH post MATCH p=(post)-[*0..2]-(m) "
            + "RETURN p, ID(post)")
    fun findByOwnerUserId(userId: String, pageable: Pageable): List<PostDto?>

    @Query("MATCH (user:User) - [like:LIKE] - (post:Post) WHERE user.userId = {0} AND id(post) = {1} DELETE like")
    fun deleteLike(userId: String, postId: Long?)

    @Query("MATCH (post: Post) - [like:LIKE] - (user:User) WHERE id(post) = {0} RETURN user")
    fun findByLike(postId: Long?): List<UserDto?>

    @Query("MATCH (post: Post) WHERE LOWER(post.title) =~ LOWER({0}) OR LOWER(post.description) =~ LOWER({0}) "
            + "OPTIONAL MATCH (post) - [post_like:LIKE] -> (user_like:User) WITH post, user_like, post_like, count(user_like) as countLikes ORDER BY post.createStamp DESC "
            + "OPTIONAL MATCH (comments:Comment) - [post_comment:COMMENT] -> (post) WITH post_comment, comments, post, user_like, post_like, count(user_like) as countLikes ORDER BY comments.createStamp ASC "
            + "RETURN post_comment, comments, post, user_like, post_like, count(user_like) as countLikes")
    fun search(text: String): List<PostDto?>

    @Query("MATCH (post: Post) WHERE LOWER(post.title) =~ LOWER({0}) OR LOWER(post.description) =~ LOWER({0}) "
            + "OPTIONAL MATCH (post) - [post_like:LIKE] -> (user_like:User) WITH post, user_like, post_like, count(user_like) as countLikes ORDER BY post.createStamp DESC "
            + "OPTIONAL MATCH (comments:Comment) - [post_comment:COMMENT] -> (post) WITH post_comment, comments, post, user_like, post_like, count(user_like) as countLikes ORDER BY comments.createStamp ASC "
            + "RETURN post_comment, comments, post, user_like, post_like, count(user_like) as countLikes SKIP {1} LIMIT {2}")
    fun search(text: String, skip: Int?, limit: Int?): List<PostDto?>
}
