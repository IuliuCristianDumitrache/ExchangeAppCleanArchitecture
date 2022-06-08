package com.example.atnm.extensions

import io.reactivex.disposables.Disposable

fun Disposable.disposeIfNotAlready() {
    if(!isDisposed) {
        dispose()
    }
}