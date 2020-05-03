pipeline {
    agent any
    parameters {
		choice(
            choices: 'ansible-tf-plan\nansible-tf-apply\nansible-tf-destory',
            description: 'Terraform Executiomn type',
            name: 'REQUESTED_ACTION')
    }
	environment {
		GCP_CRED = credentials('gcp_credentials')
		GCS_CRED = credentials('gcs_credentials')
		PROJ_DIR = "${WORKSPACE}"
	}
	stages {
        stage("build") {
            steps {
                cleanWs()
                checkout scm
            }
        }
		stage("shell") {
			steps{
				sh '''
					sed -i 's/
				'''
			}
		}
	}
}
