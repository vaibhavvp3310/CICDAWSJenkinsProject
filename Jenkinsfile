pipeline {
    agent any

    environment {
        // Define necessary environment variables
        AWS_REGION = 'ca-central-1' // Your AWS region
        CODEDEPLOY_APP = 'MyWebApp'
        DEPLOYMENT_GROUP = 'WebAppDeploymentGroup'
        GIT_REPO = 'https://github.com/vaibhavvp3310/CICDAWSJenkinsProject.git'
        // GIT_CREDENTIALS_ID = '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'
        COMMIT_ID = '' // Variable to hold commit ID
     
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    COMMIT_ID = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                }
            }
        }

        stage('Build') {
            steps {
                // If you're using Maven or Gradle for build, include this
                sh 'mvn clean package -f cicd/pom.xml'
            }
        }

        stage('Run Tests') {
            steps {
                // Run Selenium + Java tests using Maven
                sh 'mvn test -f cicd/pom.xml -PRegression'
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
            
                   echo "Deploying to AWS with the following details:"
                echo "Application: ${CODEDEPLOY_APP}"
                echo "Deployment Group: ${DEPLOYMENT_GROUP}"
                echo "Commit ID: ${COMMIT_ID}"
                echo "Repository: ${GIT_REPO}"
                    // Trigger AWS CodeDeploy using GitHub repository
                sh '''
                   aws deploy create-deployment \
                        --application-name ${CODEDEPLOY_APP} \
                        --deployment-group-name ${DEPLOYMENT_GROUP} \
                        --github-location commit=${COMMIT_ID},repository=${GIT_REPO} \
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
