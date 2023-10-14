plugins {
    autowire(libs.plugins.android.application)
    autowire(libs.plugins.kotlin.android)
    autowire(libs.plugins.kotlin.ksp)
}

android {
    namespace = property.project.app.packageName
    compileSdk = property.project.android.compileSdk

    defaultConfig {
        applicationId = property.project.app.packageName
        minSdk = property.project.android.minSdk
        targetSdk = property.project.android.targetSdk
        versionName = property.project.app.versionName
        versionCode = property.project.app.versionCode
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    lint { checkReleaseBuilds = false }
}

dependencies {
    compileOnly(de.robv.android.xposed.api)
    implementation(com.highcapable.yukihookapi.api)
    ksp(com.highcapable.yukihookapi.ksp.xposed)
    implementation(androidx.core.core.ktx)
    testImplementation(junit.junit)
}