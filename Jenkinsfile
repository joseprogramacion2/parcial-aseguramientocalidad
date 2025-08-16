pipeline {
  agent any
  tools { jdk 'jdk-21'; maven 'maven-3.9.6' }
  options { timestamps() }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/joseprogramacion2/parcial-aseguramiento2.git'
        bat 'dir /b'
      }
    }

    stage('Build') {
      steps { dir('Calculadora') { bat 'mvn -B -ntp clean compile' } }
    }

    stage('Test') {
      steps { dir('Calculadora') { bat 'mvn -B -ntp test' } }
      post {
        always { junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml' }
      }
    }

    stage('Package') {
      steps { dir('Calculadora') { bat 'mvn -B -ntp package' } }
    }

    stage('Run (opcional)') {
      steps { dir('Calculadora') { bat 'mvn -B -ntp exec:java -Dexec.mainClass=com.mycompany.calculadora.Main' } }
    }
  }

  post {
    success {
      archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, allowEmptyArchive: true
      echo '✅ Pipeline OK'
    }
    failure { echo '❌ Pipeline falló' }
  }
}





