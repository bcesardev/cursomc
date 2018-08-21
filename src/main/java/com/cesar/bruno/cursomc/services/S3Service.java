package com.cesar.bruno.cursomc.services;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private static final Logger log = Logger.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			s3client.putObject(new PutObjectRequest(bucketName, "teste", file));
		} catch (AmazonServiceException e) {
			log.info("AmazonServiceException: " + e.getErrorMessage());
			log.info("Status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			log.info("AmazonClientException: " + e.getMessage());
		}
	}

}
