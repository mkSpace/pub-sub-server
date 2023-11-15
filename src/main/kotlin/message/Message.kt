package message

import client.Subscriber

data class Message(
    val subscribers: List<Subscriber>,
    val message: String
)