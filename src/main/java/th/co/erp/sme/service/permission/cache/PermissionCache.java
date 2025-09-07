package th.co.erp.sme.service.permission.cache;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import th.co.erp.sme.model.entity.PermissionEntity;
import th.co.erp.sme.repository.PermissionRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionCache {
    private final PermissionRepository permissionRepository;

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "pmCache", allEntries = true) // if using Option B
            }
    )
    public PermissionEntity createRole(PermissionEntity role) {
        return permissionRepository.save(role);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "pmCache", allEntries = true)
    })
    public PermissionEntity updateRole(PermissionEntity role) {
        return permissionRepository.save(role);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "pmCache", allEntries = true)
    })
    public void deleteRole(Integer id) {
        permissionRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "pmCache",
            key = "T(java.lang.String).format('p:%d|s:%d|o:%s', #pageable.pageNumber, #pageable.pageSize, #pageable.sort)")
    public Page<PermissionEntity> findAllByPageAble(Pageable pageable) {
        log.info("findAllByPageAbleFromDB");
        return permissionRepository.findAll(pageable);
    }
}
