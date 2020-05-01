 
resource "random_id" "instance_id" {
byte_length = 8
}
 
resource "google_compute_instance" "tf" {
name         = "tf-vm-${random_id.instance_id.hex}"
machine_type = "f1-micro"
zone         = var.region_zone
 
boot_disk {
initialize_params {
image = "ubuntu-os-cloud/ubuntu-1604-lts"
}
}
 
metadata_startup_script = "sudo apt-get -y update; sudo apt-get -y dist-upgrade ; sudo apt-get -y install nginx"
 
network_interface {
network = "default"
 
access_config {
 
}
}
 
metadata = {
sshKeys = file(var.public_key_path)
}
}
