package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.post

import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import org.springframework.stereotype.Component

@Component
class PostDAOMapper() {


    fun mapToDTO(post: Post): PostDto {

//        if (null != post.getRepost()) {
//            builder.repost(this.map(post.getRepost()))
//        }
//
//        if (null != post.getMedia())
//            builder.media(post.getMedia().stream().map(???({ mediaDAOMapper.map() })).collect(Collectors.toSet()))

        var postDto = PostDto()
        postDto.id = post.id
        postDto.title = post.title
        postDto.description = post.description
        postDto.createStamp = post.createStamp
        postDto.tags = post.tags.orEmpty()
        postDto.timeChangeLog = post.timeChangeLog.orEmpty()
        postDto.scope = post.scope.orEmpty()
        return postDto
    }


    fun map(post: PostDto?): Post? {
//
//        if (null != post.getRepost()) {
//            builder.repost(this.map(post.getRepost()))
//        }
//
//        if (null != post.getMedia())
//            builder.media(post.getMedia().stream().map(???({ mediaDAOMapper.map() })).collect(Collectors.toSet()))


        return Post(post?.id, post?.title as String, post?.description as String, post?.createStamp as Long, post.tags, post.timeChangeLog, null,
                null, null, 1000, post.scope, null)
    }
}