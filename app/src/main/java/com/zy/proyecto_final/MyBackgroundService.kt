package com.zy.proyecto_final
import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyBackgroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Lógica para realizar la petición a la API REST
        // Puedes utilizar bibliotecas como Retrofit para realizar la petición
        // Aquí colocarías tu código para la petición HTTP
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detener cualquier tarea en curso, liberar recursos, etc.
    }
}