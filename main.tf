provider "google" {
project     = var.var_project
region      = var.var_project_region
credentials = file(var.credentials_file_path)
}

module "instance" {
  source = "./modules/instance"
} 
