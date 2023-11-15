package event

import channel.ChannelProvider

object EventBus {

    fun send(event: Event) {
        when (event) {
            is Event.Publish -> publish(event)
            is Event.Subscribe -> subscribe(event)
        }
    }

    private fun publish(event: Event.Publish) {
        val channel = ChannelProvider.findOrCreate(event.channelName)
        channel.send(event.message)
    }

    private fun subscribe(event: Event.Subscribe) {
        val channel = ChannelProvider.findOrCreate(event.channelName)
        channel.addSubscriber(event.subscriber)
    }

}