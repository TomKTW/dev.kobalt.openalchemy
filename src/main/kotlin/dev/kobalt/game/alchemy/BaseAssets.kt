package dev.kobalt.game.alchemy

import com.badlogic.gdx.utils.Disposable
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

open class BaseAssets : Disposable {
    val assets = AssetStorage()

    fun load() {
        KtxAsync.initiate()
        KtxAsync.launch {
            onLoad()
        }
    }

    open suspend fun onLoad() {

    }

    override fun dispose() {
        assets.dispose()
    }

}