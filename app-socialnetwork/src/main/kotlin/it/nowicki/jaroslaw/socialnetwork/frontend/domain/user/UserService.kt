package it.nowicki.jaroslaw.socialnetwork.frontend.domain.user

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.*
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Created by jarek on 15.06.17.
 */
@Service
@Slf4j
class UserService @Autowired constructor(val userRepository: UserRepository) {

    fun create(user: User): User? {

        val userOptional = userRepository.getByUserId(user.userId)

        if (null != userOptional)
            throw UserExistsException()

        user.createStamp = System.currentTimeMillis()

        return this.userRepository.save(user)
    }

    fun update(user: User) {

        val userExists: User = userRepository.getByUserId(user.userId) ?: throw UserNotFoundException()

        if (!userExists.userId.equals(user.userId))
            throw AccessDeniedException()

        this.userRepository.save(user)
    }

    fun active(userId: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()

        val userExists = userRepository.getByUserId(user.userId) ?: throw UserNotFoundException()

        if (!userExists.userId.equals(user.userId))
            throw AccessDeniedException()

        user.isActive = true

        this.userRepository.save(user)
    }

    fun getByUserId(userId: String): User {
        return this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
    }

    @Transactional
    fun delete(userId: String) {
        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        user.isActive = false
        this.userRepository.save(user)
    }

    @Transactional
    fun addInvite(userId: String, userInviteId: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val userInvite = this.userRepository.getByUserId(userInviteId) ?: throw UserNotFoundException()

        val userFriends = this.userRepository.getByFriends(userId)

        if (userFriends.contains(userInvite))
            throw UserCurrentFriendException()

        var invities: HashSet<User> = user.invites.orEmpty() as HashSet<User>
        invities.add(userInvite)

        user.invites = invities
        this.userRepository.save(user)
    }

    @Transactional
    fun deleteInviteSent(userId: String, userInviteId: String) {
        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()

        val invites = userRepository.getByInviteSent(userId)

        val userInvite = this.userRepository.getByUserId(userInviteId) ?: throw UserNotFoundException()

        if (!invites.contains(userInvite))
            throw UserNotSentInviteException()

        this.userRepository.deleteInvite(userId, userInviteId)

        this.userRepository.save(user)
    }

    @Transactional
    fun deleteInviteWaiting(userId: String, userIdFrom: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()

        val inviteWaiting = this.userRepository.getByInviteToAccept(userId)

        val userInvite = this.userRepository.getByUserId(userIdFrom) ?: throw UserNotFoundException()

        if (!inviteWaiting.contains(userInvite))
            throw UserNotSentInviteException()

        this.userRepository.deleteInvite(userId, userIdFrom)
        this.userRepository.save(user)
    }

    fun getByInviteToAccept(userId: String): List<User?> {
        return this.userRepository.getByInviteToAccept(userId)
    }

    fun getByInviteSent(userId: String): List<User?> {
        return this.userRepository.getByInviteSent(userId)
    }

    @Transactional
    fun addFollow(userId: String, userIdFollow: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val userFollow = this.userRepository.getByUserId(userIdFollow) ?: throw UserNotFoundException()
        val follows = user.follows.orEmpty().toHashSet()
        follows.add(userFollow)
        user.follows = follows
        this.userRepository.save(user)
    }

    @Transactional
    fun deleteFollow(userId: String, userIdFollow: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val userFollow = this.userRepository.getByUserId(userIdFollow) ?: throw UserNotFoundException()

        val usersFollow = this.userRepository.getByFollows(userId)

        if (!usersFollow.contains(userFollow))
            throw UserNotFollowException()

        this.userRepository.deleteFollow(userId, userIdFollow)
    }

    fun getByFollows(userId: String): List<User?> {
        return this.userRepository.getByFollows(userId)
    }

    @Transactional
    fun addFriend(userId: String, userIdFriend: String) {

        val user = this.userRepository.getByUserId(userId) ?: throw UserNotFoundException()
        val userInvite = this.userRepository.getByUserId(userIdFriend) ?: throw UserNotFoundException()

        val usersInvite = this.userRepository.getByInviteToAccept(userId)

        if (!usersInvite.contains(userInvite))
            throw UserNotSentInviteException()

        this.userRepository.deleteInvite(userId, userIdFriend)
        val friends = user.friends.orEmpty().toHashSet()
        friends.add(userInvite)
        user.friends = friends
        this.userRepository.save(user)
    }

    @Transactional
    fun deleteFriend(userId: String, userIdFriend: String) = this.userRepository.deleteFriend(userId, userIdFriend)

    fun getByFriends(userId: String): List<User?> = userRepository.getByFriends(userId)

    fun getUserWithFriendsAndFollowers(userId: String): User = userRepository.getWithFriendsAndFollowers(userId) ?: throw UserNotFoundException()

    fun search(text: String): Set<User?> = userRepository.search(text)

    fun deleteAllUser() = userRepository.deleteAll()

}

