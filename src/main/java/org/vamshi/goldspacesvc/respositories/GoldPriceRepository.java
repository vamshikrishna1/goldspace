package org.vamshi.goldspacesvc.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vamshi.goldspacesvc.models.GoldPrice;

public interface GoldPriceRepository extends JpaRepository<GoldPrice,Long> {
}
