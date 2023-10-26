output "allocated_ip" {
  value = aws_eip.as-eip.public_ip

  depends_on = [aws_eip.as-eip]
}

# Output the RDS database endpoint
output "rds_endpoint" {
  value = aws_db_instance.as-db-instance.address
}

# Output the RDS port
output "rds_port" {
  value = aws_db_instance.as-db-instance.port
}
