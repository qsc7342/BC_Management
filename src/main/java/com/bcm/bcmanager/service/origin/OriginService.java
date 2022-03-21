package com.bcm.bcmanager.service.origin;

import com.bcm.bcmanager.domain.origin.Origin;
import com.bcm.bcmanager.repository.origin.OriginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginService {

    private final OriginRepository repo;

    public Origin getOrigin(String oid) {
        return repo.getById(Long.parseLong(oid));
    }

    public List<Origin> getOrigins() {
        return repo.findAll();
    }

    public Origin saveOrigin(Origin origin) {
        return repo.save(origin);
    }

    public void deleteOrigin(Origin origin) { repo.delete(origin); }
}
