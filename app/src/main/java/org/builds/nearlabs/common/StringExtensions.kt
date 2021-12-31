package org.builds.nearlabs.common

fun String.shorten(maxLength: Int = 15) : String {
    return if (this.length >= maxLength) buildString {
        append(this@shorten.take(5))
        append("...")
        append(this@shorten.takeLast(5))
    } else {
        this
    }
}