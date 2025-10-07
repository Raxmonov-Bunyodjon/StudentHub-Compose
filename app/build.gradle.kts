plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.studenthub_compose"
    compileSdk = 36 // ⚠️ hozircha 34 barqaror (36 hali preview)

    defaultConfig {
        applicationId = "com.example.studenthub_compose"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // ⚡️ Kotlin 2.0.21 uchun mos Compose Compiler
        kotlinCompilerExtensionVersion = "1.6.11"
    }

}

dependencies {
    implementation(libs.identity.doctypes.jvm)
    implementation(libs.androidx.material3)
    // 📦 Compose BOM - barcha versiyalarni sync qiladi
    val composeBom = platform("androidx.compose:compose-bom:2025.09.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // 🖼️ Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // 🎨 Material 3 Design Components
    implementation("androidx.compose.material3:material3")

    // 📱 Adaptive layouts (foldables, tablets)
    implementation("androidx.compose.material3.adaptive:adaptive")

// ⚡ Compose + Activity integration
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.activity:activity-ktx:1.11.0")

    // 🔄 ViewModel + Lifecycle integration
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

    // 🧭 Navigation (Compose uchun)
    implementation("androidx.navigation:navigation-compose:2.9.5")

    // 🗄️ Room (Local database)
    implementation(libs.androidx.room.runtime)
    ksp("androidx.room:room-compiler:2.8.1")
    implementation("androidx.room:room-ktx:2.8.1")

    // 💉 Hilt (Dependency Injection) + Compose integration
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // 🔄 Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // 📦 DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    implementation("androidx.datastore:datastore:1.1.7")

    // 🖼️ Images (Coil → Compose uchun mos)
    implementation("io.coil-kt:coil-compose:2.7.0")

    // 🎬 Animations (Lottie → Compose uchun mos)
    implementation("com.airbnb.android:lottie-compose:6.6.9")

    // ✅ Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.13.0")

    //Material Icon
    implementation("androidx.compose.material:material-icons-extended")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
}
