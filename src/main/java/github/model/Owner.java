package github.model;

public record Owner(
        String login,
        String id,
        String node_id,
        String avatar_url,
        String url,
        String html_url,
        String followers_url,
        String following_url,
        String gists_url,
        String starred_url,
        String subscriptions,
        String organizations_url,
        String repos_url,
        String events_url,
        String received_events_url,
        String type,
        boolean site_admin) {
}
