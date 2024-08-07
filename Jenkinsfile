pipeline {
    agent any

    stages {
        stage("SCM Checkout") {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/puspitasahu/jenkin-cicd-demo.git']]
                )
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    try {
                        bat 'mvn clean install'  // Use 'sh' instead of 'bat' if on Unix-based agents
                    } catch (Exception e) {
                        error "Maven build failed: ${e.message}"
                    }
                }
            }
        }
        stage("Deploy to Container") {
            steps {
                script {
                    try {
                        deploy adapters: [tomcat9(credentialsId: 'tomcat-pwd', path: '', url: 'http://localhost:9090/')],
                                contextPath: 'jenkin-CICD',
                                war: '**/*.war'
                    } catch (Exception e) {
                        error "Deployment failed: ${e.message}"
                    }
                }
            }
        }
    }

    post {
        always {
            emailext (
                attachLog: true,
                body: '''<html>
<body>
<p>Build Status: ${BUILD_STATUS}</p>
<p>Build Number: ${BUILD_NUMBER}</p>
<p>Check <a href="${BUILD_URL}">Console output</a></p>
</body>
</html>''',
                mimeType: 'text/html',
                replyTo: 'puspita5sahu@gmail.com',
                subject: 'Pipeline Status: ${BUILD_NUMBER}',
                to: 'puspita5sahu@gmail.com'
            )
        }
    }
}