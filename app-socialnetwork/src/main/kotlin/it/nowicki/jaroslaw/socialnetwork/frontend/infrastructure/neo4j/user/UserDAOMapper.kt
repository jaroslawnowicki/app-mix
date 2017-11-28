package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class UserDAOMapper() {


    fun mapToDTO(user: User): UserDto {


        val invites = user?.invites?.stream()?.map(this::mapToDTO)?.collect(Collectors.toSet())

        val friends = user?.friends?.stream()?.map(this::mapToDTO)?.collect(Collectors.toSet())
        val follows = user?.follows?.stream()?.map(this::mapToDTO)?.collect(Collectors.toSet())
//        val followers = user?.followers?.parallelStream()?.map(this::mapToDTO)?.collect(Collectors.toSet())
//        val posts = user?.posts?.parallelStream()?.map(postDAOMapper::mapToDTO)?.collect(Collectors.toSet())
//        val likes = user?.likes?.parallelStream()?.map(postDAOMapper::mapToDTO)?.collect(Collectors.toSet())
//
//        var avatar: String? = null
//
//        if (null != user!!.getAvatar()) {
//            avatar = user!!.getAvatar().getNameFile()
//        }


        var userDto = UserDto()
        userDto.userId = user!!.userId
        userDto.isActive = user!!.isActive
        userDto.createStamp = user.createStamp
        userDto.email = user.email
        userDto.name = user.name
        userDto.nickName = user.nickName
        userDto.invites = invites.orEmpty()
        userDto.friends = friends.orEmpty()
        userDto.follows = follows.orEmpty()
//        userDto.followers = followers
//        userDto.posts = posts
//        userDto.likes = likes
        return userDto
    }

    fun map(userDto: UserDto?): User? {

        if (null == userDto)
            return null


        val posts: Set<Post> = HashSet<Post>() // userDto?.posts?.parallelStream()?.map(postDAOMapper::map)?.collect(Collectors.toSet())
        var invites = userDto?.invites?.stream()?.map(this::map)?.collect(Collectors.toSet<User>())
        val friends = userDto?.friends?.stream()?.map(this::map)?.collect(Collectors.toSet<User>())
        val follows = userDto?.follows?.stream()?.map(this::map)?.collect(Collectors.toSet<User>())
        val followers: Set<User> = HashSet() // null // userDto?.followers?.parallelStream()?.map(this::map)?.collect(Collectors.toSet())

        val likes: Set<Post> = HashSet() // null // userDto!!.likes?.stream()?.map(postDAOMapper::map)?.collect(Collectors.toSet())
////        var avatar: Avatar? = null
//
//        if (null != userDto!!.avatarNameFile) {
////            avatar = Avatar()
////            avatar!!.setNameFile(userDto!!.getAvatarNameFile())
//        }
//
//
//
        return User(userDto?.id, userDto?.userId, userDto?.isActive, userDto?.createStamp, userDto?.email, userDto?.name, userDto?.nickName, invites.orEmpty(), friends, follows)
    }
}
