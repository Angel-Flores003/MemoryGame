/*import org.gradle.kotlin.dsl.implementation
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            // KTOR?? Yoon
            implementation("io.ktor:ktor-client-okhttp:3.0.0")
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.napier)
            //toRoute
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            //Aleix
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            //Yoon
            implementation(compose.components.resources)
            // KTOR CORE Yoon
            val ktorVersion = "3.0.0"
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            //COIL Yoon
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
        }
        commonTest.dependencies {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            //IA
            implementation(kotlin("test"))
            //Aleix
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            implementation("javazoom:jlayer:1.0.1")
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3) // o material
                implementation(compose.ui)
                implementation(compose.components.resources) // Vital para tus fotos
            }
        }
        androidInstrumentedTest.dependencies {
            implementation(kotlin("test"))
            implementation("junit:junit:4.13.2")
            // VERSIÓN UNIFICADA A 1.10.5
            implementation("androidx.compose.ui:ui-test-junit4-android:1.10.5")
            implementation("androidx.test:runner:1.5.2")
            implementation("androidx.test:rules:1.5.0")
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // CRÍTICO: Indica a Android que use el Runner de JUnit para los tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    debugImplementation(libs.compose.uiTooling)

    testImplementation(libs.androidx.core.testing)
    testImplementation(kotlin("test"))
    //Search bar
    implementation("androidx.compose.material:material-icons-extended:1.7.6")
    //toRoute
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    //test
    testImplementation(libs.androidx.core.testing)
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.1")
    testImplementation(kotlin("test"))
}

compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.project"
            packageVersion = "1.0.0"
        }
    }
}*/








import org.gradle.kotlin.dsl.implementation
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-okhttp:3.0.0")
            // Reubicadas aquí porque son exclusivas de Android
            implementation("androidx.compose.material:material-icons-extended:1.7.6")
            implementation("androidx.navigation:navigation-compose:2.8.0")
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.napier)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            implementation(compose.components.resources)

            val ktorVersion = "3.0.0"
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
        }
        commonTest.dependencies {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("javazoom:jlayer:1.0.1")
        }
        wasmJsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
        }
        androidInstrumentedTest.dependencies {
            implementation(kotlin("test"))
            implementation("junit:junit:4.13.2")
            // VERSIÓN UNIFICADA
            implementation("androidx.compose.ui:ui-test-junit4-android:1.10.5")
            implementation("androidx.test:runner:1.5.2")
            implementation("androidx.test:rules:1.5.0")
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

// Bloque limpio solo con lo estrictamente necesario para el manifest de Android Test
dependencies {
    // VERSIÓN UNIFICADA A 1.10.5
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.10.5")
}

compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.project"
            packageVersion = "1.0.0"
        }
    }
}








/*import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()
    js(IR) { // Se recomienda IR para estabilidad en JS
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.kotlinx.serialization.core)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

            // Ktor & Coil 3 (Multiplatform)
            val ktorVersion = "3.0.0"
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-rc01")
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-okhttp:3.0.0")
            // Estas librerías solo funcionan en Android, está bien que estén aquí
            implementation("androidx.compose.material:material-icons-extended:1.7.6")
            implementation("androidx.navigation:navigation-compose:2.8.0")
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
        }

        // SECCIÓN CRÍTICA: Corregida para UI Test en Android
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                // Usamos versiones estables compatibles con Compose Multiplatform
                implementation("androidx.compose.ui:ui-test-junit4-android:1.6.8")
                implementation("androidx.test:runner:1.5.2")
            }
        }

        wasmJsMain.dependencies {
            // Hereda de commonMain, no necesitas repetir todo,
            // pero si falta algo específico de web, ponlo aquí.
        }
    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // Esto es vital para que el emulador sepa cómo lanzar los tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // Necesario para ver el layout en Android Studio y para que el test encuentre el manifest
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")
    debugImplementation(libs.compose.uiTooling)
}*/