# DS03-Sockets
Actividad 3 de Distribuidos


## Preparacion del Entorno de Desarrollo
 - Se debe tener instalado java en su version 8
 - Se debe tener instalado Maven en versiones mayores a 3.1.0
Ejecutar para clonar el repositorio
```console
$ git clone https://github.com/OscarM04/DS03-Sockets.git
```

### Uso
Ejecutar comando para dirigirse a la carpeta del proyecto
```console
$ cd DS03-Sockets
```
Es necesario modificar las variables en el archivo `socket.properties`. Acorde a lo necesitado
 - server_host
 - server_port
 - client_udp_port
 - username
 - show_logs (En caso de que se desee ver la interaccion con el servidor)

###Ejecución:
Ejecutar los siguientes comandos para correr el cliente 
- El ejecutable es para compilar el proyecto y el siguiente comando es para la ejecucion del mismo
```console
$ ./execute-project.sh
$  java -jar ./DS-Socket/target/DS-Socket-1.0-SNAPSHOT.jar
```
