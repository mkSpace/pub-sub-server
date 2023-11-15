package message

import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

object MessageQueue {

    private val queue: Queue<Message> = ConcurrentLinkedQueue()

    fun enqueue(message: Message) {
        queue.add(message)
        println("MessageQueue enqueue message: $message")
    }

    fun dequeue(): Message? {
        val message = queue.poll()
        if (message != null) {
            println("MessageQueue dequeue message: $message")
        }
        return message
    }
}