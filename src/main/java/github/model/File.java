package github.model;

public record File(String filename, String type, String language, String raw_url, int size) {
}
