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
                script {
                    sh """
                        echo "fetch the source code from ${DIRECTORY_PATH}" >> build.log
                        echo "compile the code and generate any necessary artifacts" >> build.log
                    """
                }
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
                script {
                    sh """
                        echo "Running unit and integration tests..." >> build.log
                    """
                }
            }
            post {
                success {
                    emailext to: 'tomwalker458@gmail.com',
                        subject: 'Unit and Integration Tests status email',
                        body: 'Testing was successful'
                }
  
