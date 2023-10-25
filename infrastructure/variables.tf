#Define AWS Region
variable "region" {
  description = "Infrastructure region"
  type        = string
  default     = "us-east-1"
}

variable "instance_ami" {
  description = "The AMI type of EC2 instance to launch"
  type        = string
  default     = "ami-0fc5d935ebf8bc3bc"
}
