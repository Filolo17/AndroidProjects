plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ru.mtuci.drivenext"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "ru.mtuci.drivenext"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // viewpager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // lifecycle + coroutines (для проверки интернета)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // material
    implementation("com.google.android.material:material:1.12.0")

    // constraintlayout (должно уже быть, но на всякий)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}