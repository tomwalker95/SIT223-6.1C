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
                    echo 'fetch the source code from' $DIRECTORY_PATH
                    echo 'compile the code and generate any necessary artifacts'
                }
            }
            stage('Test') {
                steps {
                    echo 'unit tests'
                }
            }
            stage('Code') {
                steps {
                    echo 'Check the quality of the code'
                }
            }
            stage('Deploy') {
                steps {
                    echo 'deploy the application to a testing environment'
                }
            }
            stage('Approval') {
                steps {
                    sleep 10
                }
            }

            stage('Deploy to Production') {
                steps {
                    echo 'Deploy to'$PRODUCTION_ENVIRONMENT
                }
            }
    }
}
