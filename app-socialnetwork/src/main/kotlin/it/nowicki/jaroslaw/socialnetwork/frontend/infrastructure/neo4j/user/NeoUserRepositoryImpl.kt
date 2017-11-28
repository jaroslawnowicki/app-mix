package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class NeoUserRepositoryImpl @Autowired constructor(val userDAOMapper: UserDAOMapper, private var userDAO: UserDAO) : UserRepository {

    override fun save(user: User): User? {
        return userDAOMapper.map(userDAO.save(userDAOMapper.mapToDTO(user)))
    }

    override fun getByUserId(userId: String?): User? = userDAOMapper.map(userDAO.findByUserId(userId))

    override fun getWithFriendsAndFollowers(userId: String): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByInviteToAccept(userId: String): List<User?> = userDAO.findByInviteWaiting(userId).map(userDAOMapper::map)

    override fun getByInviteSent(userId: String): List<User?> = userDAO.findByInviteSent(userId).map(userDAOMapper::map)

    override fun getByFollows(userId: String): List<User?> = userDAO.findByFollows(userId).map(userDAOMapper::map)

    override fun getByFriends(userId: String): List<User?> = userDAO.findByFriends(userId).map(userDAOMapper::map)

    override fun search(text: String): Set<User?> = userDAO.search(text).map(userDAOMapper::map).toSet()

    override fun deleteInvite(userIdFrom: String, userIdTo: String) = userDAO.deleteInvite(userIdFrom, userIdTo)

    override fun deleteFollow(userId: String, userIdFollow: String) = userDAO.deleteFollow(userId, userIdFollow)

    override fun deleteFriend(userId: String, userIdFriend: String) = userDAO.deleteFriend(userId, userIdFriend)

    override fun deleteAll() = userDAO.deleteAll()
}