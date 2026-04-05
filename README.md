# MuseoJava

Proyecto academico en Java puro para gestion de museo por consola.

## Estructura

```text
MuseoJava/
  ├── src/main/java/museo/
  │   ├── app/
  │   ├── model/
  │   ├── service/
  │   ├── serviceimpl/
  │   ├── enums/
  │   └── security/
  ├── README.md
  └── .gitignore
```

## Compilar y ejecutar

```bash
javac -d out src/main/java/museo/enums/*.java src/main/java/museo/model/*.java src/main/java/museo/service/*.java src/main/java/museo/serviceimpl/*.java src/main/java/museo/security/*.java src/main/java/museo/app/*.java
java -cp out museo.app.Main
```

## Credenciales de prueba

- encargado_catalogo / 1234
- restaurador_jefe / 1234
- director_museo / 1234
