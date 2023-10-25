terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.22.0"
    }
  }
}
provider "aws" {
  region = var.region
  default_tags {
    tags = {
      Environment = "Production"
      Service     = "Blue-Frogs"
    }
  }
}


