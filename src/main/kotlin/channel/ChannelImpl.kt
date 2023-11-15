package channel

import client.Subscriber
import message.Message
import message.MessageQueue

class ChannelImpl : Channel {

    private val subscribers = ArrayList<Subscriber>()

    override fun addSubscriber(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    override fun send(message: String) {
        MessageQueue.enqueue(Message(subscribers, message))
    }

}