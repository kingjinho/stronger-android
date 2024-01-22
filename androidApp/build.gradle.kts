plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.gmsGoogleServices)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.fitness.stronger.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.fitness.stronger.android"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

//    implementation(projects.shared)
//    implementation(project(mapOf("path" to ":shared")))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.fragment.testing)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform(libs.firebase.bom))

//    implementation(libs.compose.ui)
//    implementation(libs.compose.ui.tooling.preview)
//    implementation(libs.compose.ui.viewBinding)
//    implementation(libs.compose.material3)
//    implementation(libs.compose.accompanist.permission)
//    implementation(libs.androidx.constraintlayout.compose)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.activity.compose)
//    debugImplementation(libs.compose.ui.tooling)
//    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    androidTestImplementation(libs.androidx.navigation.testing)

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)






    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
}