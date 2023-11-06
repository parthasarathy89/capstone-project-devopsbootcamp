node{
    stage('Checkout Git Repo'){
        echo "Checkout the code from GIT"
        git 'https://github.com/parthasarathy89/capstone-project-devopsbootcamp.git'
    }
    stage('Build the packge for Insureme App'){
        echo "Building package for Insureme App"
        dir("insureme-app"){
            sh 'mvn clean package'
        }
    }
    stage('Build the image for InsureMe Application'){
        echo "Build the image for InsureMe Application"
        dir("insureme-app"){
            sh 'docker build -t parthasarathy89/insureme:1.0 .'
        }
    }
    stage('Push the docker Image to Hub'){
        echo "Push the docker Image to Hub"
        withCredentials([string(credentialsId: 'dockerHubPassword', variable: 'dockerHubPassword')]) {
            sh 'docker login -u parthasarathy89 -p ${dockerHubPassword}'
        }
    }
    stage('Deploy the container to the Test Server'){
        echo "Deploying the container in Test server"
        dir("insureme-app"){
        ansiblePlaybook become: true, credentialsId: 'ansible-ssh-connection', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'test-server-deployment.yml', vaultTmpPath: ''
        }
    }
    stage('Building the Testing App to test Insureme App'){
        echo "Building the Testing App to test Insureme App"
        dir("insureme-testing"){
            sh 'mvn clean compile assembly:single'
        }
    }
    stage('Testing the InsureMe App in Test Server before Prod Deployment'){
        echo "Testing the InsureMe App in Test Server before Prod Deployment"
        dir("insureme-testing/target"){
            sh 'java -jar insuremapp-testing.jar'
        }
    }
    
}
