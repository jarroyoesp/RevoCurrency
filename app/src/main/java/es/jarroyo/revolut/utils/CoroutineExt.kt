package es.jarroyo.revolut.utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Equivalent to [launch] but return [Unit] instead of [Job].
 *
 * Mainly for usage when you want to lift [launch] to return. Example:
 *
 * ```
 * override fun loadData() = launchSilent {
 *     // code
 * }
 * ```
 */
fun launchSilent(
    context: CoroutineContext = Dispatchers.Main,
    job: Job,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    val coroutineScope = CoroutineScope(context + job)
    coroutineScope.launch(context, start, block)
}

