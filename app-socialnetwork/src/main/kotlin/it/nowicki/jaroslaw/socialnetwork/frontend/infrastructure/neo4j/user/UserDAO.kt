package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository


interface UserDAO : Neo4jRepository<UserDto, Long> {

    @Query("MATCH (user:User) WHERE user.userId={0} RETURN user")
    fun findByUserId(userId: String?): UserDto

    @Query("MATCH (user:User {userId: {0} }) " +
            "OPTIONAL MATCH (friend:User)-[:FRIEND]->(user) " +
            "OPTIONAL MATCH (follower:User)-[:FOLLOW]->(user) " +
            "RETURN user as user, collect(friend) as friends, collect(follower) as followers")
    fun findWithFriendsAndFollowers(userId: String): List<UserDto>

    @Query("MATCH (u:User) <- [:INVITE] - (invite) WHERE u.userId={0} RETURN invite")
    fun findByInviteWaiting(userId: String): List<UserDto>

    @Query("MATCH (u:User) - [:INVITE] -> (invite) WHERE u.userId={0} RETURN invite")
    fun findByInviteSent(userId: String): List<UserDto>

    @Query("MATCH (u:User) - [:FOLLOW] -> (follow) WHERE u.userId={0} RETURN follow")
    fun findByFollows(userId: String): List<UserDto>

    @Query("MATCH (user:User) WHERE user.userId = {0} "
            + "OPTIONAL MATCH (user) - [f1:FRIEND] -> (userFriend1:User) WITH user, f1, userFriend1 "
            + "OPTIONAL MATCH (user) <- [f2:FRIEND] - (userFriend2:User) WITH user, f1, userFriend1, f2, userFriend2 "
            + "RETURN f1, userFriend1, f2, userFriend2")
    fun findByFriends(userId: String): List<UserDto>

    @Query("MATCH (user: User) - [in:INVITE] - (us:User) WHERE user.userId = {0} AND us.userId = {1} DELETE in")
    fun deleteInvite(userIdFrom: String, userIdTo: String)

    @Query("MATCH (user: User) - [in:FOLLOW] - (us:User) WHERE user.userId = {0} AND us.userId = {1} DELETE in")
    fun deleteFollow(userId: String, userIdFollow: String)

    @Query("MATCH (user: User) - [in:FRIEND] - (us:User) WHERE user.userId = {0} AND us.userId = {1} DELETE in")
    fun deleteFriend(userId: String, userIdFriend: String)

    @Query("MATCH (user:User) WHERE LOWER(user.name) =~ LOWER({0}) OR LOWER(user.nickName) =~ LOWER({0}) OR user.email =~ {0} RETURN user")
    fun search(text: String): Set<UserDto>
}