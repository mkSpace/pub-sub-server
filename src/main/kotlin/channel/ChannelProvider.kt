package channel

import java.util.concurrent.ConcurrentHashMap

object ChannelProvider {

    private val channelMap: ConcurrentHashMap<String, Channel> = ConcurrentHashMap()

    fun findOrCreate(channelName: String): Channel {
        return channelMap[channelName] ?: ChannelImpl().also { channelMap[channelName] = it }
    }

}