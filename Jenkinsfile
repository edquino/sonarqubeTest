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
        }

        stage('Send Email') {
            options{
                timeout(time: 1, unit:'MINUTES')
            }
            steps{
                echo "------------------------------"
                echo "Envios de correos electronicos"
                echo "------------------------------"
            }
        }

        /*stage('Get Approval') {
            options{
                timeout(time: 1, unit:'MINUTES')
            }
            steps {
                input message: '¿Desea continuar con el Proceso de Publicación?', ok: 'Si'
                script{
                    sleep(time: 1, unit:'MINUTES')
                }
            }
        }*/

        stage('Get Approval') {
            options{
                timeout(time: 1, unit:'MINUTES')
            }
            input { 
                    message 'Seleccionar el ambiente de Publicación'
                    id 'envId'
                    ok 'Submit'
                    submitterParameter 'approverId'
                    parameters {
                        choice choices: ['Desarrollo', 'Producción '], name: 'envType'
                    }
            }
            steps {
                            script {
                if(envType == 'Desarrollo'){
                    echo "---------------------------------"
                    echo "Publicacion DESARROLLO"
                    echo "---------------------------------"
                } else {
                    echo "---------------------------------"
                    echo "Publicacion PRODUCCION"
                    echo "---------------------------------"
                }
            }
            }
        }
        /*
        stage('Deploy') {
            steps {
                echo "---------------------------------"
                echo "Publicacion en Marcha"
                echo "---------------------------------"
                //sh './jenkins/scripts/deliver.sh'
                
            }
        }
        */
        
        /*stage('Sonar Scanner') {
            environment {
                SCANNER_HOME = tool 'sonarScanner'
            }
            steps{
                withSonarQubeEnv('sonarqube'){
                    withCredentials([string(credentialsId: 'TokenSonarqube', variable: 'sonarLogin')]) {
                        sh "${SCANNER_HOME}/bin/sonar-scanner -Dsonar.host.url=http://192.168.56.101:9000 -Dsonar.projectName=SonarqubeTest -Dsonar.projectVersion=${env.BUILD_NUMBER} -Dsonar.projectKey=develop -Dsonar.sources=src/main -Dsonar.tests=target/jacoco.exec -Dsonar.java.binaries=. -Dsonar.language=java -Dsonar.java.source=11 -Dsonar.qualitygate.wait=true"
                    }
                }
            }
        }*/
        
        /*
        stage('Quality Gates'){
            steps{
                timeout(time: 5, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true                    
                }
            }
        }*/
    }

 
    post{
        always{
            emailext body: 'Hola mundo desde Jenkins', 
            subject: 'Test Email - Emailext', 
            to: 'edquino@outlook.es'
        }
        success{
            emailext(
                body: 'Hola mundo desde Jenkins', 
                subject: 'Test Email - Emailext----------', 
                to:'edquino@outlook.es'
            )  
        }
    }
        
}
