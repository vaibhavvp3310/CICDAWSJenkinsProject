pipeline {
    agent any

    environment {
        // Define necessary environment variables
        AWS_REGION = 'us-east-1' // Your AWS region
        CODEDEPLOY_APP = 'MyWebApp'
        DEPLOYMENT_GROUP = 'WebAppDeploymentGroup'
        GIT_REPO = 'https://github.com/vaibhavvp3310/CICDAWSJenkinsProject.git'
        GIT_CREDENTIALS_ID = '5173a2a0-99ce-405d-a563-a6caba467ad1'
     
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from GitHub
                git url: "${GIT_REPO}", credentialsId: "${GIT_CREDENTIALS_ID}"
            }
        }

        stage('Build') {
            steps {
                // If you're using Maven or Gradle for build, include this
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                // Run Selenium + Java tests using Maven
                sh 'mvn test'
            }
        }

        stage('Archive Test Results') {
            steps {
                // Archive test reports (JUnit or other)
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Deploy to AWS CodeDeploy') {
            when {
                // Only run this stage if the tests are successful
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                // Trigger AWS CodeDeploy using GitHub repository
                sh '''
                    aws deploy create-deployment \
                        --application-name ${CODEDEPLOY_APP} \
                        --deployment-group-name ${DEPLOYMENT_GROUP} \
                        --github-location repository=${GIT_REPO},commitId=${GIT_COMMIT} \
                        --region ${AWS_REGION}
                '''
            }
        }
    }

    post {
        // Mark the build as failed if something goes wrong
        failure {
            mail to: 'vaibhavvp3310@gmail.com',
                 subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Please check the Jenkins job for more details."
        }
    }
}