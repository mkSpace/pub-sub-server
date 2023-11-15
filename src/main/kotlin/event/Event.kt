package event

import client.Subscriber

sealed class Event(
    open val channelName: String
) {
    class Subscribe(
        override val channelName: String,
        val subscriber: Subscriber
    ) : Event(channelName)

    class Publish(
        override val channelName: String,
        val message: String
    ) : Event(channelName)
}