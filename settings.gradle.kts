pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        // URL of a public Maven repository
        maven(url = "https://artifact.bytedance.com/repository/Volcengine/")
        // URL of BytePlus's public repository
        maven(url = "https://artifact.byteplus.com/repository/public/")
        // URL of a streaming repository
        maven {
            url = uri("https://artifact.bytedance.com/repository/thrall_base/")
            credentials {
                // Username to access the streaming repository. No need to change
                username = "veVOS"

                // Password to access the streaming repository. No need to change
                password = "KUC9TpKrqbryrxHz"
            }
        }


        flatDir {
            // The local directory of the CV SDK file effectAAR-release.aar
            dirs = setOf(File("libs"))
        }
    }

}

rootProject.name = "Live Stream"
include(":app")
 