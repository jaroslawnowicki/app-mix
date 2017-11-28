package it.nowicki.jaroslaw.socialnetwork.frontend.domain.user

/**
 * Created by jarek on 15.06.17.
 */
interface UserRepository {

    fun save(user: User): User?

    fun getByUserId(userId: String?): User?

    fun getWithFriendsAndFollowers(userId: String): User?

    fun getByInviteToAccept(userId: String): List<User?>

    fun getByInviteSent(userId: String): List<User?>

    fun getByFollows(userId: String): List<User?>

    fun getByFriends(userId: String): List<User?>

    fun search(text: String): Set<User?>

    fun deleteInvite(userIdFrom: String, userIdTo: String)

    fun deleteFollow(userId: String, userIdFollow: String)

    fun deleteFriend(userId: String, userIdFriend: String)

    fun deleteAll()

}