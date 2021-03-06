package net.guizhanss.guizhanlib.updater;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Guizhan Builds Updater is responsible to auto-update the plugin from
 * Guizhan Builds (builds.guizhanss.net).
 * <p>
 * This class can be extended for mirror URLs.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
public abstract class AbstractGuizhanBuildsUpdater {

    @Getter
    private final Plugin plugin;
    @Getter
    private final File file;
    @Getter
    private final String user;
    @Getter
    private final String repo;
    @Getter
    private final String branch;
    @Accessors(fluent = true)
    @Getter
    private final boolean checkOnly;

    private UpdaterLocalization localization;
    @Setter
    private Logger logger;

    /**
     * This constructor sets up the updater.
     *
     * @param plugin    The {@link Plugin} instance
     * @param file      The {@link File} of plugin
     * @param user      GitHub user
     * @param repo      GitHub repository
     * @param branch    GitHub branch
     * @param checkOnly Whether to check the version only, without downloading
     */
    @ParametersAreNonnullByDefault
    public AbstractGuizhanBuildsUpdater(
        Plugin plugin,
        File file,
        String user,
        String repo,
        String branch,
        boolean checkOnly
    ) {
        this.plugin = plugin;
        this.file = file;
        this.user = user;
        this.repo = repo;
        this.branch = branch;
        this.checkOnly = checkOnly;

        this.logger = plugin.getLogger();

        prepareUpdateFolder();
        initLocalization();
    }

    /**
     * This constructor sets up the updater.
     *
     * @param plugin    The {@link Plugin} instance
     * @param file      The {@link File} of plugin
     * @param user      GitHub user
     * @param repo      GitHub repository
     * @param branch    GitHub branch
     * @param checkOnly Whether to check the version only, without downloading
     * @param lang      The language of updater
     *
     * @deprecated The language option is no longer used, since it is now
     * configured under global config file (/plugins/GuizhanBuildsUpdater/config.yml).
     */
    @Deprecated
    @ParametersAreNonnullByDefault
    public AbstractGuizhanBuildsUpdater(
        Plugin plugin,
        File file,
        String user,
        String repo,
        String branch,
        boolean checkOnly,
        String lang
    ) {
        this(plugin, file, user, repo, branch, checkOnly);
    }

    /**
     * Create update folder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void prepareUpdateFolder() {
        File dir = new File("plugins/" + Bukkit.getUpdateFolder());

        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Initialize localization
     */
    private void initLocalization() {
        InputStream stream = AbstractGuizhanBuildsUpdater.class.getResourceAsStream("/updater.json");
        localization = new UpdaterLocalization(stream);
    }

    /**
     * Override this method to specify the builds page's URL.
     * <p>
     * No trailing slash is needed.
     * <p>
     * Example: {@code https://builds.guizhanss.net}
     *
     * @return the URL of builds page
     */
    @Nonnull
    public abstract String getBuildsURL();

    /**
     * Override this method to set the language of updater.
     *
     * @return the language of updater
     */
    @Nonnull
    public abstract String getLanguage();

    /**
     * Run updater task.
     */
    public void start() {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new UpdaterTask(this));
    }

    /**
     * Get the URL of repository list file (repos.json).
     *
     * @return the URL of repository list file
     */
    @Nonnull
    public String getReposFileURL() {
        return getBuildsURL() + "/repos.json";
    }

    /**
     * Get the repository key in repos.json.
     *
     * @return the repository key
     */
    @Nonnull
    public String getRepoKey() {
        return MessageFormat.format("{0}/{1}:{2}", user, repo, branch);
    }

    /**
     * Get the URL of builds information file (builds.json).
     *
     * @param directory Working directory
     *
     * @return the URL of builds information file
     */
    @Nonnull
    public String getBuildsInfo(@Nonnull String directory) {
        return MessageFormat.format("{0}/f/{1}/builds.json", getBuildsURL(), directory);
    }

    /**
     * Call the logger of plugin.
     *
     * @param level   log {@link Level}
     * @param message the message
     * @param args    the arguments
     */
    public void log(Level level, String message, Object... args) {
        logger.log(level, () -> MessageFormat.format(message, args));
    }

    /**
     * Call the logger of plugin.
     *
     * @param level     log {@link Level}
     * @param exception the {@link Exception}
     * @param message   the message
     * @param args      the arguments
     */
    public void log(Level level, Exception exception, String message, Object... args) {
        logger.log(level, exception, () -> MessageFormat.format(message, args));
    }

    /**
     * Get the URL of the build artifact.
     *
     * @param directory Working directory
     * @param target    Target filename
     *
     * @return the URL of the build artifact
     */
    @ParametersAreNonnullByDefault
    @Nonnull
    public String getTargetUrl(String directory, String target) {
        return MessageFormat.format("{0}/f/{1}/{2}", getBuildsURL(), directory, target);
    }

    /**
     * Get localized {@link String}.
     *
     * @param key          The localization key
     * @param defaultValue The default value if localization is not found by key.
     *
     * @return The localized {@link String}.
     */
    @ParametersAreNonnullByDefault
    @Nonnull
    public String getLocalizedString(String key, String defaultValue) {
        Preconditions.checkArgument(key != null, "The localization key cannot be null.");
        Preconditions.checkArgument(defaultValue != null, "The localization key cannot be null.");

        String result = localization.getLocalization(getLanguage(), key);
        return Objects.requireNonNullElse(result, defaultValue);
    }
}
