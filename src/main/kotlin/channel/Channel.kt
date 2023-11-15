package channel

import client.Subscriber

interface Channel {
    fun addSubscriber(subscriber: Subscriber)
    fun send(message: String)
}