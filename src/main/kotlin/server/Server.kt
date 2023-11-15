package server

import client.ClientHandler
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

class Server : Runnable {

    companion object {
        private const val PORT = 3600
        private const val MAX_CLIENT_NUM = 10
    }

    private val serverSocket: ServerSocket = ServerSocket(PORT)
    private val executorService = Executors.newFixedThreadPool(MAX_CLIENT_NUM)

    init {
        executorService.execute(Postman())
    }

    override fun run() {
        while (true) {
            runCatching {
                listen(serverSocket.accept())
            }.onFailure { exception ->
                exception.printStackTrace()
                return
            }
        }
    }

    private fun listen(clientSocket: Socket) {
        println("Socket{${clientSocket.hashCode()}} 과 연결되었습니다")
        executorService.execute(ClientHandler(clientSocket))
    }
}