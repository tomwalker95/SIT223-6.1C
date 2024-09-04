pipeline {
    agent any
    environment {
        DIRECTORY_PATH = 'C/Users/tomwalk/Documents/Uni/SIT223'
        TESTING_ENVIRONMENT = 'Toms PC'
        PRODUCTION_ENVIRONMENT = 'Tom'
    }
    stages {
        stage('Build') {
            steps {
                echo "fetch the source code from ${DIRECTORY_PATH}"
                echo 'compile the code and generate any necessary artifacts'
            }
            post {
                success {
                    emailext to: 'tomwalker458@gmail.com',
                        subject: 'Build status email',
                        body: 'Build was successful'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'unit and integration tests'
            }
            post {
                success {
                    emailext to: 'tomwalker458@gmail.com',
                        subject: 'Unit and Integration Tests status email',
                        body: 'Testing was successful'
                }
            }
        }

        stage('Code Analysis') {
            steps {
                echo 'Check the quality of the code'
            }
        }

        stage('Security Scan') {
            steps {
                echo 'Run security scans on the code'
            }
            post {
                success {
                    emailext to: 'tomwalker458@gmail.com',
                        subject: 'Security Scan Status',
                        body: 'Security Scan Successful'
                }
                failure {
                    emailext to: 'tomwalker458@gmail.com',
                        subject: 'Security Scan Status',
                        body: 'Security Scan failed'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                sleep 10
            }
        }

        stage('Deploy to Production') {
            steps {
                echo "Deploy to ${PRODUCTION_ENVIRONMENT}"
            }
        }
    }

    post {
        always {
            // Write the console log to a file
            script {
                def logFile = 'build.log'
                writeFile file: logFile, text: currentBuild.getLog().join('\n')
            }

            // Archive the console log
            archiveArtifacts artifacts: 'build.log', allowEmptyArchive: true

            // Send the email with the console log as an attachment
            emailext body: 'Stage Complete. Please find the attached logs.',
                subject: 'Build Logs',
                to: 'tomwalker458@gmail.com',
                attachmentsPattern: 'build.log'
        }
    }
}
