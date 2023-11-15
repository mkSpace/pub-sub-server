package client

import java.io.DataOutputStream
import java.io.OutputStream

open class Subscriber(outputStream: OutputStream) {

    private val writer = DataOutputStream(outputStream)

    fun receive(message: String) {
        writer.writeUTF(message)
        writer.flush()
    }
}