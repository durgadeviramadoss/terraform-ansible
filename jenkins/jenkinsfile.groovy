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
		stage("Execute Terraform Plan") {
			steps{
				sh '''
					sed -i -e 's#/home/karthick/credentials/gcs-e10aec265420.json#'${GCS_CRED}'#' ansible-terraform-exec/verify-resources-tf.yml
					ansible-playbook ansible-terraform-exec/verify-resources-tf.yml
				'''
			}
		}
	}
}
