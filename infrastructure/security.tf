resource "aws_iam_policy" "s3-access-policy" {
  name = "s3-access-policy"

  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = [
          "s3:GetObject",
          "s3:ListBucket"
        ],
        Effect = "Allow",
        Resource = [
          aws_s3_bucket.as-s3.arn,
          "${aws_s3_bucket.as-s3.arn}/*"
        ]
      }
    ]
  })
}

resource "aws_iam_role" "ec2-s3-access-role" {
  name = "ec2-s3-access-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = "sts:AssumeRole",
        Effect = "Allow",
        Principal = {
          Service = "ec2.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "s3-policy-attachment" {
  policy_arn = aws_iam_policy.s3-access-policy.arn
  role       = aws_iam_role.ec2-s3-access-role.name
}
