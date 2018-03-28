package it.nowicki.jaroslaw.socialnetwork.backend.web


import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.PostService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.response.OkResponse
import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.security.UserInfo
import org.springframework.web.bind.annotation.*

/**
 * Created by jarek on 16.06.17.
 */
@RestController
@RequestMapping("/post")
class PostController(val postService: PostService, val userInfo: UserInfo) {

    @PostMapping("")
//	@ApiOperation(value = "doc header...", notes = "detailed doc...")
//	@RequestMapping(value = "/double", method = arrayOf(RequestMethod.GET))
    fun create(@RequestBody post: Post): Post? {
        return postService.create(userInfo.principal().username ?: throw UserNotFoundException(), post)
    }

    @GetMapping("/{postId}")
    fun post(@PathVariable postId: Long): Post = postService.getById(postId)


    @PatchMapping("/{postId}/update")
    fun update(@PathVariable postId: Long, @RequestBody post: Post): OkResponse {

        this.postService.update(postId, post, userInfo.principal().username ?: throw UserNotFoundException())
        return OkResponse()
    }

    @DeleteMapping("/{deleteId}/delete")
    fun delete(@PathVariable deleteId: Long): OkResponse {
        this.postService.delete(deleteId, userInfo.principal().username ?: throw UserNotFoundException())
        return OkResponse()
    }

    @PutMapping("/like/{postId}")
    fun like(@PathVariable postId: Long): OkResponse {
        this.postService.like(userInfo.principal().username ?: throw UserNotFoundException(), postId)
        return OkResponse()
    }

    @PutMapping("/unlike/{postId}")
    fun unlike(@PathVariable postId: Long): OkResponse {
        this.postService.unlike(userInfo.principal().username ?: throw UserNotFoundException(), postId)
        return OkResponse()
    }

    @GetMapping("/repost/{postId}")
    fun rePost(@PathVariable postId: Long): OkResponse {
        this.postService.repost(userInfo.principal().username ?: throw UserNotFoundException(), postId)
        return OkResponse()
    }

    @GetMapping("/search/{text}")
    fun search(@PathVariable text: String): List<Post?> = postService.search(text)

    @GetMapping("/search/{text}/{index}/{limit}")
    fun search(@PathVariable text: String, @PathVariable index: Int, @PathVariable limit: Int): List<Post?> = postService.search(text, index, limit)

}