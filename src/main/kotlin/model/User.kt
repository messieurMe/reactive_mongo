package model

import org.bson.Document

data class User(
    val id: Int,
    var name: String,
    val currency: Currency
) {
    companion object {
        fun fromDocument(doc: Document) = with(doc) {
            User(
                getInteger("id"),
                getString("name"),
                Currency.valueOf(getString("currency"))
            )
        }

        fun fromArguments(list: List<String>) = User(
            list[0].toInt(),
            list[1],
            Currency.valueOf(list[2])
        )
    }
}