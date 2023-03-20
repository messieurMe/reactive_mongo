package domain

import kotlinx.coroutines.flow.*
import model.Currency
import model.Currency.*
import rx.Observable

class CurrencyConverter {

    fun convert(from: Currency, to: Currency, amount: Double): Observable<Double> =
        Observable.just(amount)
            .toUsd(from)
            .toCurrency(to)

    // There should be real request to converter, but I'm using random values
    private fun Observable<Double>.toUsd(from: Currency) = map { amount ->
        when (from) {
            USD -> amount
            RUB -> amount / 60
            EUR -> amount / 1.2
        }
    }

    // Same story
    private fun Observable<Double>.toCurrency(to: Currency) = map { amount ->
        when (to) {
            USD -> amount
            RUB -> amount * 60
            EUR -> amount * 1.2
        }
    }
}