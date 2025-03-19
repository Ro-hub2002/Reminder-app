ReminderApp ⏰

ReminderApp es una aplicación de recordatorios desarrollada en Spring Boot con Java 17, que permite programar recordatorios con fecha y hora específica. La aplicación muestra notificaciones de forma nativa en Linux, con soporte adicional para Windows y MacOS.


✨ Características

📅 Programación de recordatorios con fecha y hora específicas.

🔔 Notificaciones nativas en Linux, con compatibilidad para Windows y MacOS.

⏳ Ejecución en segundo plano con Scheduling para revisar recordatorios cada minuto.

🛠 Gestión de recordatorios evitando pérdidas en intervalos de ejecución.

🎨 Interfaz gráfica moderna con JavaFX y CSS para estilos personalizados.

💾 Almacenamiento local con SQLite utilizando JPA.

🧩 Arquitectura modular con Programación Orientada a Aspectos (AOP).

✅ Pruebas unitarias con JUnit y Mockito.


📦 Instalación y ejecución

Clona el repositorio:
git clone https://github.com/TU_USUARIO/ReminderApp.git
cd ReminderApp

Compila y ejecuta la aplicación:

mvn clean install
java -jar target/ReminderApp.jar


🛠 Contribución

Si quieres mejorar la aplicación, ¡eres bienvenido! 

💡 Sugerencia: Sería una gran mejora agregar la funcionalidad de que la aplicación pueda ejecutarse en segundo plano dentro del sistema operativo. De esta manera, los recordatorios seguirían funcionando incluso si el usuario cierra o detiene la aplicación principal.

