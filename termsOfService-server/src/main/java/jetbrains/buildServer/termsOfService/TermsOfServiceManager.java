package jetbrains.buildServer.termsOfService;

import jetbrains.buildServer.users.SUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface TermsOfServiceManager {

    @NotNull
    List<Agreement> getMustAcceptAgreements(@NotNull SUser user);

    @NotNull
    List<Agreement> getAgreements();

    @NotNull
    Optional<Agreement> findAgreement(@NotNull String id);

    @NotNull
    List<ExternalAgreementLink> getExternalAgreements();

    @NotNull
    Optional<GuestNotice> getGuestNotice();

    interface Agreement {

        @NotNull
        String getId();

        @NotNull
        String getVersion();

        @NotNull
        String getShortName();

        @NotNull
        String getFullName();

        @NotNull
        String getHtml();

        @NotNull
        String getLink();

        @NotNull
        List<Consent> getConsents();

        /**
         * Check whether the user have accepted the latest version of the agreement.
         */
        boolean isAccepted(@NotNull SUser user);

        /**
         * Check whether the user have accepted any version of the agreement.
         */
        boolean isAnyVersionAccepted(@NotNull SUser user);

        @Nullable
        String getLastUpdated();

        void accept(@NotNull SUser user, @NotNull HttpServletRequest request);

    }

    interface GuestNotice {

        @NotNull
        String getText();

        @NotNull
        String getHtml();

        @NotNull
        String getCookieName();

        int getCookieDurationDays();
    }

    interface Consent {

        @NotNull
        String getId();

        boolean isCheckedByDefault();

        @NotNull
        String getText();

        boolean isAccepted(@NotNull SUser user);

        void changeAcceptedState(@NotNull SUser user, boolean accepted, @NotNull String acceptedFromIp);
    }

    interface ExternalAgreementLink {
        @NotNull
        String getName();

        @NotNull
        String getUrl();
    }
}
