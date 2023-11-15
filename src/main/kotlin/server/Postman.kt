package server

import message.Message
import message.MessageQueue

class Postman : Runnable {

    override fun run() {
        while (true) {
            val message = MessageQueue.dequeue()
            if (message == null) {
                Thread.sleep(100)
                continue
            }
            handleMessage(message)
        }
    }

    private fun handleMessage(message: Message) {
        val actualMessage = message.message
        message.subscribers.forEach { subscriber ->
            subscriber.receive(message = actualMessage)
        }
    }

}