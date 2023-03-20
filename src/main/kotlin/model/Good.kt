package model

import org.bson.Document

data class Good(
    val name: String,
    val ownerId: Int,
    var price: Double,
    var currency: Currency
) {

    companion object {
        fun fromDocument(doc: Document) = with(doc) {
            Good(
                getString("name"),
                getInteger("ownerId"),
                getDouble("amount"),
                Currency.valueOf(getString("currency"))
            )
        }

        fun fromArguments(list: List<String>) = Good(
            list[0],
            list[1].toInt(),
            list[2].toDouble(),
            Currency.valueOf(list[3])
        )
    }
}