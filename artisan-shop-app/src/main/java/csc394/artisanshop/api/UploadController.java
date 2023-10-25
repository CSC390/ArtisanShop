package csc394.artisanshop.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin
public class UploadController {
    public class FileUploadController {

        private final S3Client s3Client;
        private final String bucketName = "bluefrogs-source";

        public FileUploadController() {
            // Initialize the S3 client
            s3Client = S3Client.builder().region(Region.US_EAST_1).build();
        }

        @PostMapping("upload")
        public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
            if (!file.isEmpty()) {
                try {
                    // Create a unique filename based on the current timestamp and file extension
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    String newFilename = System.currentTimeMillis() + ext;

                    // Upload the file to S3
                    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(newFilename)
                            .acl("public-read")
                            .contentType(file.getContentType())
                            .build();

                    s3Client.putObject(putObjectRequest,
                            RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

                    // Generate the S3 URL for the uploaded file
                    String s3Url = "https://" + bucketName + ".s3.amazonaws.com/" + newFilename;

                    return ResponseEntity.ok("File uploaded successfully. S3 URL: " + s3Url);
                } catch (Exception e) {
                    return ResponseEntity.ok("Error uploading file: " + e.getMessage());
                }
            } else {
                return ResponseEntity.ok("File is empty or missing.");
            }
        }
    }

}
