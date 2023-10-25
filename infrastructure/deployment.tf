#1. Create VPC
resource "aws_vpc" "as-vpc" {
  cidr_block = "10.0.0.0/16"
}

#2. Create Internet Gateway
resource "aws_internet_gateway" "as-igw" {
  vpc_id = aws_vpc.as-vpc.id
}

#3. Create Custom Route Table
resource "aws_route_table" "as-route-table" {
  vpc_id = aws_vpc.as-vpc.id

  # Define a default route for IPv4 traffic
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.as-igw.id
  }

  # Define a default route for IPv6 traffic
  route {
    ipv6_cidr_block = "::/0"
    gateway_id      = aws_internet_gateway.as-igw.id
  }
}

#4. Create a Subnet
resource "aws_subnet" "as-public-subnet-1" {
  vpc_id            = aws_vpc.as-vpc.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-east-1a"
}

#5. Associate Subnet with Route table
resource "aws_route_table_association" "as-association" {
  subnet_id      = aws_subnet.as-public-subnet-1.id
  route_table_id = aws_route_table.as-route-table.id
}

#6. Create Security Groups
resource "aws_security_group" "as-security-groups" {
  name        = "allow_web_traffic"
  description = "Allow Web inbound traffic"
  vpc_id      = aws_vpc.as-vpc.id

  ingress {
    description      = "HTTPS"
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  ingress {
    description      = "HTTP"
    from_port        = 80
    to_port          = 80
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  ingress {
    description      = "SSH"
    from_port        = 22
    to_port          = 22
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    description      = "Allow all outbound traffic"
  }
}

#7. Create a network interface with an IP in the subnet that was created in step 4
resource "aws_network_interface" "web-server-nic" {
  subnet_id       = aws_subnet.as-public-subnet-1.id
  private_ips     = ["10.0.1.50"]
  security_groups = [aws_security_group.as-security-groups.id]
}

#8. Assign an elastic IP to the network interface created in step 7.
resource "aws_eip" "as-eip" {
  domain                    = "vpc"
  network_interface         = aws_network_interface.web-server-nic.id
  associate_with_private_ip = "10.0.1.50"
  depends_on                = [aws_internet_gateway.as-igw]
}

#9. Create Ubuntu server and install/enable apache2
resource "aws_instance" "as-ec2" {
  ami                  = var.instance_ami
  instance_type        = "t2.micro"
  key_name             = "bluefrogs_kp"
  availability_zone    = "us-east-1a"
  iam_instance_profile = aws_iam_role.ec2-s3-access-role.name
  user_data            = file("userdata/userdata.tpl")

  network_interface {
    device_index         = 0
    network_interface_id = aws_network_interface.web-server-nic.id

  }
}

#10. Create a S3 bucket
resource "aws_s3_bucket" "as-s3" {
  bucket = "as-blueFrogs-code-repo"
}

#11. Define an RDS database subnet group
resource "aws_db_subnet_group" "as-db-subnet-group" {
  name       = "as-db-subnet-group"
  subnet_ids = [aws_subnet.as-public-subnet-1.id] # Use the existing subnet created in step 4
}

#12. Create an RDS instance
resource "aws_db_instance" "as-db-instance" {
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "mysql"
  engine_version       = "8.0.34"
  instance_class       = "db.t3.micro"
  db_name              = "artisan-marketplace"
  username             = "admin"
  password             = try(string(env("TF_VAR_RDS_key")), "")
  db_subnet_group_name = aws_db_subnet_group.as-db-subnet-group.name
  parameter_group_name = "default.mysql5.7"
  skip_final_snapshot  = true

}

#14. Create a RDS security group
resource "aws_security_group" "as-db-sg" {
  name        = "as-db-sg"
  description = "Allow access to db"
  vpc_id      = aws_vpc.as-vpc.id

  ingress {
    description      = "MySQL/Aurora"
    from_port        = 3306
    to_port          = 3306
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    security_groups  = [aws_security_group.as-security-groups]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
}
