pipeline {
    agent any
    parameters {
		choice(
            choices: 'ansible-tf-plan\nansible-tf-apply\nansible-tf-destory',
            description: 'Terraform Executiomn type',
            name: 'REQUESTED_ACTION')
    }
	stages {
        stage("build") {
            steps {
                cleanWs()
                checkout scm
            }
        }
		stage("Execute Terraform Plan") {
			when {
                	expression { params.REQUESTED_ACTION == 'ansible-tf-plan' }
            		}
			steps{
				sh 'ansible-playbook ansible-terraform-exec/verify-resources-tf.yml'
			}
		}
		stage("Create Terraform Plan") {
			when {
                	expression { params.REQUESTED_ACTION == 'ansible-tf-apply' }
            		}
			steps{
				sh 'ansible-playbook ansible-terraform-exec/create-resources-tf.yml'
			}
		}
		stage("Destory Terraform Plan") {
			when {
                	expression { params.REQUESTED_ACTION == 'ansible-tf-destory' }
            		}
			steps{
				sh 'ansible-playbook ansible-terraform-exec/destory-resources-tf.yml'
			}
		}
	}
}
