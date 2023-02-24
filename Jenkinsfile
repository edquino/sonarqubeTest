/*def qualityGateValidation(gg) {
    if(gg.status != 'OK'){
        return true
    }
    return false
}*/
pipeline {
    agent any
    tools{
        maven 'mavenjenkins'
    }
    stages {
         stage('Prepare scripts'){
            steps{
                sh 'chmod a+x jenkins/scripts/*.sh'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
            /*post{
                junit 'target/surefire-reports/*.xml'
            }*/
        }

        stage('Send Email') {
            options{
                timeout(time: 1, unit:'MINUTES')
            }
            steps{
                echo "Envios de correos electronicos"
            }
        }

        stage('Get Approval') {
            options{
                timeout(time: 2, unit:'MINUTES')
            }
            steps {
                input message: '¿Desea continuar con el Proceso de Publicación?', ok: 'Yes'
                script{
                    sleep(time: 1, unit:'MINUTES')
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
        
        stage('Sonar Scanner') {
            environment {
                SCANNER_HOME = tool 'sonarScanner'
            }
            steps{
                withSonarQubeEnv('sonarqube'){
                    withCredentials([string(credentialsId: 'TokenSonarqube', variable: 'sonarLogin')]) {
                        sh "${SCANNER_HOME}/bin/sonar-scanner -Dsonar.host.url=http://192.168.56.101:9000 -Dsonar.projectName=SonarqubeTest -Dsonar.projectVersion=${env.BUILD_NUMBER} -Dsonar.projectKey=develop -Dsonar.sources=${env.WORKSPACE}/src -Dsonar.tests=target/jacoco.exec -Dsonar.java.binaries=. -Dsonar.language=java -Dsonar.java.source=11"
                    }
                }
            }
        }
    }
}
