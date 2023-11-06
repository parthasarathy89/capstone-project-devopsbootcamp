node{
    stage('Checkout Git Repo'){
        echo "Checkout the code from GIT"
        git 'https://github.com/parthasarathy89/capstone-project-devopsbootcamp.git'
    }
    stage('Build the packge for Insureme App'){
        echo "Building package for Insureme App"
        sh 'mvn clean package'
    }
    stage('Build the image for InsureMe Application'){
        echo "Build the image for InsureMe Application"
        sh 'docker build -t parthasarathy89/insureme:1.0 .'
    }
    stage('Push the docker Image to Hub'){
        echo "Push the docker Image to Hub"
        withCredentials([string(credentialsId: 'dockerHubPassword', variable: 'dockerHubPassword')]) {
            sh 'docker login -u parthasarathy89 -p ${dockerHubPassword}'
        }
    }
    stage('Deploy the container to the Test Server'){
        echo "Deploying the container in Test server"
        ansiblePlaybook become: true, credentialsId: 'ansible-ssh-connection', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'test-server-deployment.yml', vaultTmpPath: ''
    }
    stage('Checkout the Testing App for regression Testing Insureme App'){
        echo "Checkout the Testing App for regression Testing Insureme App"
        git 'https://github.com/parthasarathy89/capstone-project-testing.git'
    }
    stage('Building the Testing App to test Insureme App'){
        echo "Building the Testing App to test Insureme App"
        sh 'mvn clean compile assembly:single'
    }

    stage('Testing the InsureMe App in Test Server before Prod Deployment'){
        echo "Testing the InsureMe App in Test Server before Prod Deployment"
        sh 'java -jar target/insureme-testing.jar'
    }
    stage('Prod Deployment after successful Testing'){
        echo "Prod Deployment after successful Testing"
        ansiblePlaybook become: true, credentialsId: 'ansible-ssh-connection', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'prod-server-deployment.yml', vaultTmpPath: ''
    }
}
