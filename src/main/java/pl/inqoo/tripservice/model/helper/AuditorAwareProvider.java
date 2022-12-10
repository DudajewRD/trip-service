package pl.inqoo.tripservice.model.helper;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareProvider implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor(){ return Optional.of("Inqoo patrzy!");}
}
