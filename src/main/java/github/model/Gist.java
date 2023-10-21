package github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.HashMap;

public record Gist(
        String url,
        String forks_url,
        String commits_url,
        String id,
        String node_id,
        String git_pull_url,
        String git_push_url,
        String html_url,
        HashMap<String, File> files,
        @JsonProperty("public") boolean isPublic,
        Date created_at,
        Date updated_at,
        String description,
        int comments,
        String user,
        String comments_url,
        Owner owner,
        boolean truncated) {}
