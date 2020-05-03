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
			steps{
				sh '''
					ansible-playbook ansible-terraform-exec/verify-resources-tf.yml
				'''
			}
		}
	}
}
