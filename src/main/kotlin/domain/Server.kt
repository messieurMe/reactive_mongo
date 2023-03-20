package domain

import io.reactivex.netty.protocol.http.server.HttpServer
import model.Good
import model.User
import java.lang.IllegalArgumentException

class Server(private val database: Database) {

    fun invoke() = HttpServer
        .newServer(8080)
        .start { request, response ->
            val path = request.decodedPath.substring(1).split("/")
            response.writeString(when (path[0]) {
                "newGood" -> database.addGood(Good.fromArguments(path.subList(1, path.size))).map { "New good added" }
                "newUser" -> database.addUser(User.fromArguments(path.subList(1, path.size))).map { "New user added" }
                "getUsers" -> database.getUsers().map { it.toString() }
                "getGoods" -> database.getGoods(path[1]).map { it.toString() }
                else -> throw IllegalArgumentException("Unexpected argument")
            })
        }.awaitShutdown()
}