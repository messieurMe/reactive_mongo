import domain.CurrencyConverter
import domain.Database
import domain.Server

fun main(args: Array<String>) {
    Server(
        Database(CurrencyConverter())
    ).invoke()
}