package client

import event.Event
import event.EventBus
import java.io.DataInputStream
import java.net.Socket

class ClientHandler(socket: Socket) : Runnable, Subscriber(socket.getOutputStream()) {

    private val dataInputStream = DataInputStream(socket.getInputStream())

    private val loggerName = "Socket {${socket.hashCode()}}"

    override fun run() {
        var request: String
        while (true) {
            runCatching {
                request = dataInputStream.readUTF()
                log("input message: $request")
                val choppedRequest = request.split(" ")
                val command = choppedRequest.firstOrNull() ?: throw IllegalStateException("Command must not be null")
                val channelName =
                    choppedRequest.getOrNull(1) ?: throw IllegalStateException("ChannelName must not be null")
                sendEvent(command, channelName, choppedRequest)
            }.onFailure {
                receive(it.message ?: "Error occured")
            }
        }
    }

    private fun sendEvent(
        command: String,
        channelName: String,
        choppedRequest: List<String>
    ) {
        when (command) {
            Constants.Command.SUBSCRIBE ->
                EventBus.send(Event.Subscribe(channelName, this))

            Constants.Command.PUBLISH -> {
                choppedRequest.getOrNull(2) ?: throw IllegalStateException("Message must not be null")
                EventBus.send(
                    Event.Publish(
                        channelName = channelName,
                        message = choppedRequest.subList(2, choppedRequest.size).joinToString(" ")
                    )
                )
            }
        }
    }

    private fun log(message: String) {
        println("[$loggerName] $message")
    }
}