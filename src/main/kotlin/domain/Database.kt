package domain

import com.mongodb.rx.client.MongoClient
import com.mongodb.rx.client.MongoClients
import com.mongodb.rx.client.MongoDatabase
import com.mongodb.rx.client.Success
import kotlinx.coroutines.rx2.asFlow
import model.Currency
import model.Good
import model.User
import org.bson.Document
import org.bson.conversions.Bson
import rx.Observable

class Database(
    val converter: CurrencyConverter
) {
    private val client: MongoClient = MongoClients.create()
    private val database: MongoDatabase = client.getDatabase("rx")

    fun addUser(user: User): Observable<Success> =
        database
            .getCollection("Users")
            .insertOne(
                Document("id", user.id)
                    .append("userName", user.name)
                    .append("currency", user.currency.toString())
            ).apply { subscribe() }

    fun addGood(good: Good): Observable<Success> =
        database
            .getCollection("Goods")
            .insertOne(
                Document()
                    .append("name", good.name)
                    .append("ownerId", good.ownerId)
                    .append("currency", good.currency.toString())
                    .append("price", good.price)
            ).apply { subscribe() }

    fun getGoods(ownerCurrency: String): Observable<Double> = database
        .getCollection("Goods")
        .find()
        .toObservable()
        .map(Good.Companion::fromDocument)
        .concatMap { converter.convert(it.currency, Currency.valueOf(ownerCurrency), it.price) }

    fun getUsers(): Observable<User> =
        database
            .getCollection("Users")
            .find()
            .toObservable()
            .map { User.fromDocument(it) }
}