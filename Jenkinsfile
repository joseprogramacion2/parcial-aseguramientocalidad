pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9' }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build & Test') {
      steps {
        bat 'java -version'
        bat 'mvn -v'
        bat 'mvn -B -U clean package'   // <-- genera el JAR y corre tests
      }
    }
  }
  post {
    always {
      junit '**/target/surefire-reports/*.xml'
    }
    success {
      archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
  }
}
