cd pipeline {
    agent any
    environment {
        DIRECTORY_PATH = 'C/Users/tomwalk/Documents/Uni/SIT223'
        TESTING_ENVIRONMENT = 'Toms PC'
        PRODUCTION_ENVIRONMENT = 'Tom'
    }
    stages {
            stage('Build') {
                steps {
                    echo "fetch the source code from + ${DIRECTORY_PATH}"
                    echo 'compile the code and generate any necessary artifacts'
                }

                post {
                    success {
                        mail to: 'tomwalker458@gmail.com',
                        subject: 'Buid status email',
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
                        mail to: 'tomwalker458@gmail.com',
                        subject: 'unit and integration tests status email',
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
                    echo 'deploy the application to a testing environment'
                }

                post {
                    success {
                        mail to: 'tomwalker458@gmail.com',
                             subject: 'Security Scan Status',
                             body: 'Security Scan Successful'
                    }

                    failure {
                        mail to: 'tomwalker458@gmail.com',
                             subject: 'Security Scan Status',
                             body: 'Security Scan failed'
                    }
            }
            }
            stage('Intergration Tests') {
                steps {
                    sleep 10
                }
            x
            }

            stage('Deploy to Production') {
                steps {
                    echo "Deploy to' + ${PRODUCTION_ENVIRONMENT}"
                }
            }
    }
}
