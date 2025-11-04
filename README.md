**TCG Tracker** es una aplicación Android desarrollada en **Kotlin** que permite a los usuarios registrar, gestionar y consultar sus cartas coleccionables (Trading Card Game).  
Diseñada para ser rápida, moderna y fácil de usar, integra buenas prácticas de arquitectura (MVVM) y componentes de Android Jetpack.

---

## 🚀 Características principales

- 📇 **Agregar, editar y eliminar cartas** fácilmente.  
- 🗂️ **Visualización organizada** con filtros y adaptadores personalizados.  
- 💾 **Persistencia local de datos** mediante `Room` o repositorio interno.  
- 🔄 **Arquitectura MVVM** para un código mantenible y escalable.  
- 🎨 **Diseño intuitivo en XML** siguiendo Material Design.  
- ⚡ Compatible con Android API 25+ (Android 7.1 y superiores).

---

## 🏗️ Estructura del proyecto

```
TCG_TRACKER_fixed_ready/
│
├── app/
│   ├── build.gradle
│   ├── src/
│   │   └── main/
│   │       ├── AndroidManifest.xml
│   │       ├── java/com/example/tcgtracker/
│   │       │   ├── MainActivity.kt
│   │       │   ├── AddCardActivity.kt
│   │       │   ├── EditCardActivity.kt
│   │       │   ├── CardLogActivity.kt
│   │       │   ├── adapter/
│   │       │   ├── data/
│   │       │   ├── model/
│   │       │   ├── repository/
│   │       │   └── viewmodel/
│   │       └── res/
│   │           ├── layout/
│   │           └── values/
│   └── ...
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🧩 Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)**:

- **Model:** Define las entidades (`Card`, etc.) y la lógica de datos.  
- **Repository:** Maneja el acceso a datos (local o remoto).  
- **ViewModel:** Expone datos observables para las vistas.  
- **View (Activity/XML):** Interfaz con el usuario.

---

## ⚙️ Requisitos

- **Android Studio Giraffe o superior**  
- **Gradle 8+**  
- **Kotlin 1.9+**  
- **SDK mínimo:** 25  
- **SDK objetivo:** 34  

---

## 🧠 Tecnologías utilizadas

- **Kotlin**  
- **Android Jetpack**  
- **RecyclerView + Adapter personalizados**  
- **Room**  
- **View Binding / Data Binding**  
- **Material Design Components**


## ▶️ Cómo ejecutar el proyecto

1. Clona este repositorio:
2. Abre el proyecto en **Android Studio**.
3. Espera a que Gradle sincronice las dependencias.
4. Conecta un dispositivo o inicia un emulador.
5. Ejecuta la app con **Run ▶️**.
