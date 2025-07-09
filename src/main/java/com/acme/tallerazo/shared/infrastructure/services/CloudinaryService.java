package com.acme.tallerazo.shared.infrastructure.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    /**
     * Upload image to Cloudinary
     * @param file MultipartFile to upload
     * @return URL of uploaded image
     * @throws IOException if upload fails
     */
    public String uploadImage(MultipartFile file) throws IOException {
        // 🔧 CONFIGURACIÓN CORREGIDA - SIN TRANSFORMACIONES COMPLEJAS
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "workshops",
                        "resource_type", "image"
                        // 🔧 QUITAR LAS TRANSFORMACIONES POR AHORA
                )
        );
        return uploadResult.get("secure_url").toString();
    }

    /**
     * Upload image with custom public ID
     * @param file MultipartFile to upload
     * @param publicId Custom public ID for the image
     * @return URL of uploaded image
     * @throws IOException if upload fails
     */
    public String uploadImage(MultipartFile file, String publicId) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "public_id", publicId,
                        "folder", "workshops",
                        "resource_type", "image",
                        "overwrite", true
                        // 🔧 QUITAR LAS TRANSFORMACIONES POR AHORA
                )
        );
        return uploadResult.get("secure_url").toString();
    }

    /**
     * Delete image from Cloudinary
     * @param publicId Public ID of image to delete
     * @throws IOException if deletion fails
     */
    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    /**
     * Extract public ID from Cloudinary URL
     * @param imageUrl Cloudinary image URL
     * @return Public ID or null if not a valid Cloudinary URL
     */
    public String extractPublicIdFromUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.contains("cloudinary.com")) {
            return null;
        }

        try {
            String[] parts = imageUrl.split("/");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("workshops") && i + 1 < parts.length) {
                    String fileName = parts[i + 1];
                    return "workshops/" + fileName.substring(0, fileName.lastIndexOf('.'));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
