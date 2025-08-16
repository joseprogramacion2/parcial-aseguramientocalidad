pipeline {
  agent any

  tools {
    jdk   'jdk-21'        // Configúralo en Manage Jenkins > Global Tool Configuration
    maven 'maven-3.9.6'   // O el nombre que tengas para Maven
  }

  options { timestamps() }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/joseprogramacion2/parcial-aseguramiento2.git'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn -B -ntp clean compile'
      }
    }

    stage('Test') {
      steps {
        bat 'mvn -B -ntp test'
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
      }
    }

    // ⛔️ Falla si dividir() no divide (p. ej., si multiplica)
    stage('Sanity Check: dividir 8 / 2 = 4') {
      steps {
        bat '''
        del /f /q sanity.txt 2>NUL
        mvn -B -ntp -q exec:java -Dexec.mainClass=com.mycompany.calculadora.Main -Dexec.args="8 / 2" > sanity.txt
        type sanity.txt
        findstr /C:"Resultado: 4" sanity.txt >nul
        if errorlevel 1 (
          echo ERROR: La salida no contiene "Resultado: 4". Posible bug en dividir().
          exit /b 1
        )
        '''
      }
    }

    stage('Package') {
      steps {
        bat 'mvn -B -ntp -DskipTests package'
      }
    }
  }

  post {
    success {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, allowEmptyArchive: true
      echo '✅ Pipeline OK'
    }
    failure {
      echo '❌ Pipeline falló'
    }
    always {
      echo 'Pipeline finalizado.'
    }
  }
}


