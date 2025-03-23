package br.gov.mt.apiseplag.utils;

import java.util.Base64;

public class FileUtils {

    public static String getFileExtensionFromBase64(String base64Image) {
        byte[] fileBytes = Base64.getDecoder().decode(base64Image);

        return getExtensionFromMagicBytes(fileBytes);
    }

    private static String getExtensionFromMagicBytes(byte[] fileBytes) {
        if (fileBytes.length < 4) {
            throw new IllegalArgumentException("Arquivo muito pequeno para ser identificado.");
        }

        // JPEG - 0xFF, 0xD8, 0xFF
        if (fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF) {
            return ".jpg";
        }
        // PNG - 0x89, 0x50, 0x4E, 0x47
        else if (fileBytes[0] == (byte) 0x89 && fileBytes[1] == (byte) 0x50 && fileBytes[2] == (byte) 0x4E && fileBytes[3] == (byte) 0x47) {
            return ".png";
        }
        // GIF - 0x47, 0x49, 0x46, 0x38
        else if (fileBytes[0] == (byte) 0x47 && fileBytes[1] == (byte) 0x49 && fileBytes[2] == (byte) 0x46 && fileBytes[3] == (byte) 0x38) {
            return ".gif";
        }
        // BMP - 0x42, 0x4D
        else if (fileBytes[0] == (byte) 0x42 && fileBytes[1] == (byte) 0x4D) {
            return ".bmp";
        }
        // PDF - 0x25, 0x50, 0x44, 0x46
        else if (fileBytes[0] == (byte) 0x25 && fileBytes[1] == (byte) 0x50 && fileBytes[2] == (byte) 0x44 && fileBytes[3] == (byte) 0x46) {
            return ".pdf";
        }
        return ""; //nao identificado / listado
    }

    public static String getContentType(String base64Image) {
        String extension = getFileExtensionFromBase64(base64Image);

        switch (extension) {
            case ".jpg":
                return "image/jpeg";
            case ".png":
                return "image/png";
            case ".gif":
                return "image/gif";
            case ".bmp":
                return "image/bmp";
            case ".pdf":
                return "application/pdf";
            default:
                return "application/octet-stream";  // Tipo genérico
        }
    }
}
