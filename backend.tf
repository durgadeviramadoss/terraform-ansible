terraform {
  backend "gcs" {
    bucket = "tf-state-store-backend"
    prefix = "tf-state-store"
    credentials = "/home/karthick/credentials/gcs-e10aec265420.json"
  }
}
