resource "aws_key_pair" "bluefrogs_kp" {
  key_name   = "bluefrogs_kp"
  public_key = file("bluefrogs_kp.pub")
}
