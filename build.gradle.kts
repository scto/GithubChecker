// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
}

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}
