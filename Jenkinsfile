pipeline {
    agent any
    stages {
  stage('maven install') {
    steps {
        withMaven(globalMavenSettingsConfig: 'null', globalMavenSettingsFilePath: 'pom.xml', jdk: 'JAVA', maven: 'mvn', mavenSettingsConfig: 'null', mavenSettingsFilePath: 'pom.xml') {
    sh 'mvn clean test'
}
    }
  }
}
}
